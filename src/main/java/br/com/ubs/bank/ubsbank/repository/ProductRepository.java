package br.com.ubs.bank.ubsbank.repository;

import br.com.ubs.bank.ubsbank.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByProduct(String product);
    List<Product> findAll();
}
