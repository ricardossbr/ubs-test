package br.com.ubs.bank.ubsbank.batch.processor;

import br.com.ubs.bank.ubsbank.assembler.ProductSourceModelAssembler;
import br.com.ubs.bank.ubsbank.dto.FileDto;
import br.com.ubs.bank.ubsbank.model.Product;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration

public class JsonProcessorProduct implements ItemProcessor<FileDto, Product> {

    @Autowired
    private ProductSourceModelAssembler mapper;

    @Override
    public Product process(FileDto productDto) throws Exception {
        Product pro = this.mapper.toModel(productDto.getData().get(0));
        return pro;
    }
}
