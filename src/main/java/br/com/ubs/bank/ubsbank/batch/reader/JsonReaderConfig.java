package br.com.ubs.bank.ubsbank.batch.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileUrlResource;

import java.net.MalformedURLException;

@Configuration
public class JsonReaderConfig {

    @Bean
    public FlatFileItemReader jsonReader(
            LineMapper lineMapper
    ) throws MalformedURLException {
        return new FlatFileItemReaderBuilder()
                .name("jsonReader")
                .resource(new FileUrlResource("/Users/ricardossbr/dev/ubs-bank-test/files/data_1.json"))
                .lineMapper(lineMapper)
                .build();
    }
}
