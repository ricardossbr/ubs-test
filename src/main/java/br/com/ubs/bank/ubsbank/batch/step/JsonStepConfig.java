package br.com.ubs.bank.ubsbank.batch.step;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JsonStepConfig {
    @NonNull
    private StepBuilderFactory stepBuilderFactory;

    @Value("${job.chunk.size}")
    private int chunkSize;

    @Bean
    public Step jsonStep(
            FlatFileItemReader jsonReader,
            ItemProcessor jsonProcessor,
            MongoItemWriter jsonWriter
    ){
        return stepBuilderFactory.get("jsonStep")
                .chunk(this.chunkSize)
                .reader(jsonReader)
                .processor(jsonProcessor)
                .writer(jsonWriter)
                .build();
    }
}
