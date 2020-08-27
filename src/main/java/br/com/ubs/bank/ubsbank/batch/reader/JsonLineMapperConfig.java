package br.com.ubs.bank.ubsbank.batch.reader;

import br.com.ubs.bank.ubsbank.dto.ProductDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JsonLineMapperConfig implements LineMapper {

    @NonNull
    final ObjectMapper mapper;

    @Override
    public ProductDto mapLine(String line, int lineNumber) throws Exception {

            final ProductDto pro = mapper.readValue(line, ProductDto.class);
            return pro;

        //return new ProductDto();
    }
}
