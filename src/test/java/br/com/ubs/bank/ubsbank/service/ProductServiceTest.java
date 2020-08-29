package br.com.ubs.bank.ubsbank.service;

import br.com.ubs.bank.ubsbank.model.Product;
import br.com.ubs.bank.ubsbank.model.ResponseHttp;
import br.com.ubs.bank.ubsbank.repository.ProductRepository;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void when_get_product_should_return_response_body_ok(){
        Mockito.when(productRepository.findByProduct(Mockito.anyString())).thenReturn(this.getListProduct());
        final ResponseEntity<ResponseHttp> prod = productService.getProduct("EMMS", 10);
        Assertions.assertNotNull(prod);
        Assertions.assertNotNull(prod.getBody().getStore());
    }

    @Test
    public void when_get_product_should_return_response_body_not_found(){
        Mockito.when(productRepository.findByProduct(Mockito.anyString())).thenReturn(this.getListProductEmpty());
        final ResponseEntity<ResponseHttp> prod = productService.getProduct("EMMS", 10);
        Assertions.assertEquals( 404, prod.getStatusCodeValue());
    }


    private List<Product> getListProduct(){
        final List<Product> products = new ArrayList<>();
        final Product pro = new Product();
            pro.setIndustry("Disney");
            pro.setOrigin("SP");
            pro.setPrice("$7.02");
            pro.setProduct("EMMS");
            pro.setQuantity(10);
            pro.setType("2XL");
            products.add(pro);
            products.add(pro);
            products.add(pro);
            products.add(pro);
        return products;
    }

    private List<Product> getListProductEmpty(){
        return new ArrayList<>();
    }
}
