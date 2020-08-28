package br.com.ubs.bank.ubsbank.batch.reader;



import br.com.ubs.bank.ubsbank.model.Product;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileUrlResource;

import java.net.MalformedURLException;

@Configuration
public class ProductJsonItemReader {

    @Value("${filepath.location}")
    private String filePath;

    @Bean
    public JsonItemReader<Product> productItemReader() throws MalformedURLException {
        return new JsonItemReaderBuilder<Product>()
                .jsonObjectReader(new ProductJsonObjectReader<>(Product.class))
                .resource(new FileUrlResource(filePath))
                .name("productItemReader")
                .build();
    }

}