package br.com.ubs.bank.ubsbank.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ResponseHttp {
    private int sumOfQuantity;
    private BigDecimal sumOfVolume;
    private BigDecimal avgPrice;
    private List<Store> store;
}
