package br.com.ubs.bank.ubsbank.repository;

import br.com.ubs.bank.ubsbank.model.Store;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StockRepository extends MongoRepository<Store, String> {
}
