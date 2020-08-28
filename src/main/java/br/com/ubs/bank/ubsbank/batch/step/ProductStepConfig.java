package br.com.ubs.bank.ubsbank.batch.step;


import br.com.ubs.bank.ubsbank.model.Product;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ProductStepConfig {
    @NonNull
    private StepBuilderFactory stepBuilderFactory;

    @Value("${job.chunk.size}")
    private int chunkSize;

    @Bean
    public Step productStep(
            JsonItemReader<Product> productItemReader,
            MongoItemWriter productWriter
    ){
        return stepBuilderFactory.get("productStep")
                .chunk(this.chunkSize)
                .reader(productItemReader)
                .writer(productWriter)
                .build();
    }
}
