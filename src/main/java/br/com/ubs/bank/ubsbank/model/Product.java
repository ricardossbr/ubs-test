package br.com.ubs.bank.ubsbank.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class Product {
    private String product;
    private int quantity;
    private String price;
    private String type;
    private String industry;
    private String origin;
}
