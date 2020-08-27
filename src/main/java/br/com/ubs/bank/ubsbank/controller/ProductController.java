package br.com.ubs.bank.ubsbank.controller;

import br.com.ubs.bank.ubsbank.dto.FileDto;
import br.com.ubs.bank.ubsbank.model.ResponseHttp;
import br.com.ubs.bank.ubsbank.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ProductController {
    @NonNull
    private final JobLauncher jobLauncher;
    @NonNull
    private final Job startJob;
    @NonNull
    private final ProductService service;

    @GetMapping(value = "/health")
    public ResponseEntity<ResponseHttp> health(@RequestParam("product") String product, @RequestParam("quantity-store") int quantityStore) throws IOException {

        if(paramsIsOk(product, quantityStore)){
            try{
            jobLauncher.run(startJob, new JobParametersBuilder().toJobParameters());
            }catch (Exception e){
                e.printStackTrace();
            }

            return service.getProduct(product, quantityStore);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private boolean paramsIsOk(String product, int quantityStore){
        return quantityStore > 1 && !Objects.isNull(product) && !product.trim().isEmpty();
    }
}
