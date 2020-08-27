package br.com.ubs.bank.ubsbank.service;

import br.com.ubs.bank.ubsbank.dto.FileDto;
import br.com.ubs.bank.ubsbank.dto.ProductDto;
import br.com.ubs.bank.ubsbank.model.ResponseHttp;
import br.com.ubs.bank.ubsbank.model.Store;
import br.com.ubs.bank.ubsbank.repository.StockRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductService {
    @Autowired
    private StockRepository stockRepository;

    public List<ProductDto> retirar() throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final FileDto products = mapper.readValue(new File("/Users/ricardossbr/dev/ubs-bank-test/files/data_1.json"), FileDto.class);
        return products.getData();
    }

    public ResponseEntity<ResponseHttp> getProduct(String product, int quantityStore) throws IOException {
        //Todo query in mongo
        final Optional<Store> byId = this.stockRepository.findById(product);
        //if(byId.isPresent()){
            final ResponseHttp responseBody = new ResponseHttp();
            final List<ProductDto> products = this.retirar();
            responseBody.setSumOfQuantity(this.getSumOfQuantity(products));
            responseBody.setSumOfVolume(this.getSumOfVolumeStore(products));
            responseBody.setAvgPrice(this.getAvgPrice(products));



            products.stream()
                    .filter(p -> p.getProduct().equals("EMMS"))//todo retirar
                    .forEach(System.out::println);
            final List<Store> stores = new ArrayList<>();
            for (int i = 0; i < quantityStore; i++) {
                final Store store = new Store();
                final int quantity = getSumOfQuantity(products) / quantityStore;// todo retirar
                store.setName("Loja " + (i+1));
                store.setQuantity(this.getQuantityStore(products, quantityStore));
                store.setPrice(this.getPriceStore(products));
                store.setVolume(this.getSumOfVolumeStore(store));
                store.setSumPrice(this.getSumPriceStore(store));
                store.setSumOfVolume(this.getSumOfQuantityStore(store));
                store.setAvgPrice(this.getAvgPriceStore(store));
                stores.add(store);
            }
            responseBody.setStore(stores);




            return ResponseEntity.ok(responseBody);
        //}
        //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private BigDecimal getAvgPrice(List<ProductDto> products) {
        final int quantity = this.getSumOfQuantity(products);
        final BigDecimal sumOfVolume = this.getSumOfVolumeStore(products);
        final BigDecimal avgPrice = sumOfVolume.divide(BigDecimal.valueOf(quantity), new MathContext(3));
        return avgPrice;
    }

    private BigDecimal getSumOfVolumeStore(List<ProductDto> products) {
        final Optional<BigDecimal> volume = products.stream()
                .filter(p -> p.getProduct().equals("EMMS"))//todo retirar
                .map(p -> {
                    final BigDecimal price = new BigDecimal(p.getPrice().replace("$", ""));
                    final BigDecimal result = BigDecimal.valueOf(p.getQuantity()).multiply(price);
                    return result;
                })
                .reduce(BigDecimal::add);
        return volume.orElseGet(() -> new BigDecimal(0));
    }

    private int getSumOfQuantity(List<ProductDto> products) {
        return products.stream()
                .filter(p -> p.getProduct().equals("EMMS"))//todo retirar
                .map(p -> p.getQuantity())
                .reduce(0, (x, y) -> x + y);
    }

    private List<BigDecimal> getPriceStore(List<ProductDto> products) {
        return products.stream()
                .filter(p -> p.getProduct().equals("EMMS"))//todo retirar
                .map(p -> new BigDecimal(p.getPrice().replace("$", "")))
                .collect(Collectors.toList());

    }

    private List<Integer> getQuantityStore(List<ProductDto> products, int quantityStore) {
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
