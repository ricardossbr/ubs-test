package br.com.ubs.bank.ubsbank.batch.writer;


import br.com.ubs.bank.ubsbank.model.Product;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class ProductWriterConfig {

    @Bean
    public MongoItemWriter productWriter(MongoTemplate mongoTemplate){
        return new MongoItemWriterBuilder<Product>()
                .collection("product")
                .template(mongoTemplate)
                .build();
    }
}
