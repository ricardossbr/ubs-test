package br.com.ubs.bank.ubsbank.dto;

import lombok.Data;

import java.util.List;

@Data
public class FileDto {
    private List<ProductDto> data;
}
