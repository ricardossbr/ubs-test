package br.com.ubs.bank.ubsbank.model;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;

@Data
public class FileJson {
    private List<Product> data;
}
