package br.com.ubs.bank.ubsbank.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Store {
    private String name;
    private List<Integer> quantity;
    private List<BigDecimal> price;
    private List<BigDecimal> volume;
    private int sumOfVolume;
    private BigDecimal avgPrice;
    private BigDecimal sumPrice;
}
