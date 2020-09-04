package br.com.ubs.bank.ubsbank.service;

import br.com.ubs.bank.ubsbank.model.FileJson;
import br.com.ubs.bank.ubsbank.model.Product;
import br.com.ubs.bank.ubsbank.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileJsonService {
    @Value("${filepath.location}")
    private String path;
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ProductRepository productRepository;

    public void processFiles() throws IOException {
        final File file = new File(path);
        final File[] arquivos = file.listFiles();
        this.deleteProducts();
        this.readFileAndSave(arquivos);
    }

    private void readFileAndSave(File[] arquivos) throws IOException {
        for (int i=0; i< arquivos.length; i++) {
            final FileJson products = mapper.readValue(new File(String.valueOf(arquivos[i])), FileJson.class);
            Arrays.asList(products.getData().stream().distinct().collect(Collectors.toList()))
                    .parallelStream().forEach(f -> this.saveProducts(f));
        }
    }

    private List<Product> saveProducts(List<Product> products){
        return this.productRepository.saveAll(products);
    }

    private void deleteProducts(){
        this.productRepository.deleteAll();
    }

}
