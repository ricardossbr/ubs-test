package br.com.ubs.bank.ubsbank.service;


import br.com.ubs.bank.ubsbank.model.Product;
import br.com.ubs.bank.ubsbank.model.ResponseHttp;
import br.com.ubs.bank.ubsbank.model.Store;
import br.com.ubs.bank.ubsbank.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<ResponseHttp> getProduct(String product, int quantityStore){
        final List<Product> byProduct = this.productRepository.findByProduct(product);
        if(!byProduct.isEmpty()){
            final ResponseHttp responseBody = new ResponseHttp();
            responseBody.setSumOfQuantity(this.getSumOfQuantity(byProduct));
            responseBody.setSumOfVolume(this.getSumOfVolumeStore(byProduct));
            responseBody.setAvgPrice(this.getAvgPrice(byProduct));
            responseBody.setStore(this.getStores(quantityStore, byProduct));
            return ResponseEntity.ok(responseBody);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private List<Store> getStores(int quantityStore, List<Product> byProduct) {
        final List<Store> stores = new ArrayList<>();
        for (int i = 0; i < quantityStore; i++) {
            final Store store = new Store();
            store.setName("Loja " + (i+1));
            store.setQuantity(this.getQuantityStore(byProduct, quantityStore));
            store.setPrice(this.getPriceStore(byProduct));
            store.setVolume(this.getSumOfVolumeStore(store));
            store.setSumPrice(this.getSumPriceStore(store));
            store.setSumOfVolume(this.getSumOfQuantityStore(store));
            store.setAvgPrice(this.getAvgPriceStore(store));
            stores.add(store);
        }
        return stores;
    }

    private BigDecimal getAvgPrice(List<Product> products) {
        final int quantity = this.getSumOfQuantity(products);
        final BigDecimal sumOfVolume = this.getSumOfVolumeStore(products);
        final BigDecimal avgPrice = sumOfVolume.divide(BigDecimal.valueOf(quantity), new MathContext(3));
        return avgPrice;
    }

    private BigDecimal getSumOfVolumeStore(List<Product> products) {
        final Optional<BigDecimal> volume = products.stream()
                .map(p -> {
                    final BigDecimal price = new BigDecimal(p.getPrice().replace("$", ""));
                    final BigDecimal result = BigDecimal.valueOf(p.getQuantity()).multiply(price);
                    return result;
                })
                .reduce(BigDecimal::add);
        return volume.orElseGet(() -> new BigDecimal(0));
    }

    private int getSumOfQuantity(List<Product> products) {
        return products.stream()
                .map(p -> p.getQuantity())
                .reduce(0, (x, y) -> x + y);
    }

    private List<BigDecimal> getPriceStore(List<Product> products) {
        return products.stream()
                .filter(p -> p.getProduct().equals("EMMS"))//todo retirar
                .map(p -> new BigDecimal(p.getPrice().replace("$", "")))
                .collect(Collectors.toList());

    }

    private List<Integer> getQuantityStore(List<Product> products, int quantityStore) {
        return products.stream()
                .filter(p -> p.getProduct().equals("EMMS"))//todo retirar
                .map(p -> Math.round(p.getQuantity() / quantityStore))
                .collect(Collectors.toList());
    }

    private List<BigDecimal> getSumOfVolumeStore(Store store) {
        final List<BigDecimal> sumOfVolume = new ArrayList<>();
        for (int i = 0; i < store.getQuantity().size(); i++) {
            sumOfVolume.add(BigDecimal.valueOf(store.getQuantity().get(i)).multiply(store.getPrice().get(i)));
        }
        return sumOfVolume;
    }

    private BigDecimal getSumPriceStore(Store store) {
        final Optional<BigDecimal> reduce = store.getVolume().stream().map(p -> p).reduce(BigDecimal::add);
        return reduce.orElseGet(() -> new BigDecimal(0));
    }

    private int getSumOfQuantityStore(Store store) {
        return store.getQuantity().stream()
                .reduce(0, (x, y) -> x + y);

    }

   private BigDecimal getAvgPriceStore(Store store) {
        final int volume = store.getSumOfVolume();
        final BigDecimal price = store.getSumPrice();
        final BigDecimal avgPrice = price.divide(BigDecimal.valueOf(volume), new MathContext(3));
        return avgPrice;
    }

}
