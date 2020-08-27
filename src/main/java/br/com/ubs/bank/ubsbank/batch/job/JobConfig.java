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
public class JobConfig {
    @NonNull
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job startJob(Step jsonStep){
        return jobBuilderFactory.get("startJob")
                .start(jsonStep).build();
    }


}
