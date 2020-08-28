package br.com.ubs.bank.ubsbank.batch.job;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class ProductJobConfig {
    @NonNull
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job productJob(Step productStep){
        return jobBuilderFactory.get("productJob")
                .start(productStep).build();
    }


}
