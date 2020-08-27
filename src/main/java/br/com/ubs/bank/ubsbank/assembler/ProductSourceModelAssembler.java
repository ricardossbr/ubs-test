package br.com.ubs.bank.ubsbank.assembler;

import br.com.ubs.bank.ubsbank.dto.ProductDto;
import br.com.ubs.bank.ubsbank.model.Product;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductSourceModelAssembler {
    @NonNull
    private ModelMapper modelMapper;

    public Product toModel(ProductDto productDto){
        return this.modelMapper.map(productDto, Product.class);
    }
}
