package br.com.ubs.bank.ubsbank.model;

import lombok.Data;
import java.util.List;

@Data
public class FileJson {
    private List<Product> data;
}
