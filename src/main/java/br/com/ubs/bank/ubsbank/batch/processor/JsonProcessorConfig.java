package br.com.ubs.bank.ubsbank.batch.processor;

import br.com.ubs.bank.ubsbank.dto.FileDto;
import br.com.ubs.bank.ubsbank.model.Product;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonProcessorConfig {

    @Bean
    public ItemProcessor<FileDto, Product> jsonProcessor(){
        return new JsonProcessorProduct();
    }
}
