package br.com.ubs.bank.ubsbank.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductDto {
    private String product;
    private int quantity;
    private String price;
    private String type;
    private String industry;
    private String origin;
}
