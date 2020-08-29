package br.com.ubs.bank.ubsbank.controller;

import br.com.ubs.bank.ubsbank.model.ResponseHttp;
import br.com.ubs.bank.ubsbank.service.FileJsonService;
import br.com.ubs.bank.ubsbank.service.ProductService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class ProductController {
    @NonNull
    private final ProductService service;

    @NonNull
    private final FileJsonService fileJsonService;

    @GetMapping(value = "/health")
    public ResponseEntity health(){
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping(value = "/getfile")
    public ResponseEntity getFile() throws IOException {
        this.fileJsonService.processFiles();
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @GetMapping(value = "/product")
    public ResponseEntity<ResponseHttp> getProduct(@RequestParam("product") String product, @RequestParam("quantity-store") int quantityStore){
        if(paramsIsOk(product, quantityStore)){
            return service.getProduct(product, quantityStore);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private boolean paramsIsOk(String product, int quantityStore){
        return quantityStore > 0 && !Objects.isNull(product) && !product.trim().isEmpty();
    }

}
