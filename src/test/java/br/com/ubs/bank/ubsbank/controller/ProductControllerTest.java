package br.com.ubs.bank.ubsbank.controller;

import br.com.ubs.bank.ubsbank.model.ResponseHttp;
import br.com.ubs.bank.ubsbank.model.Store;
import br.com.ubs.bank.ubsbank.service.FileJsonService;
import br.com.ubs.bank.ubsbank.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.*;
import org.springframework.http.ResponseEntity;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;

public class ProductControllerTest {
    @InjectMocks
    private ProductController controller;

    @Mock
    private ProductService service;

    @Mock
    private FileJsonService fileJsonService;

    @Captor
    private ArgumentCaptor<String> product;

    @Captor
    private ArgumentCaptor<Integer> quantity;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void when_call_getfile_should_return_ok() throws IOException {
        final ResponseEntity response = this.controller.getFile();
        Assertions.assertEquals( 200 , response.getStatusCodeValue());
    }

    @Test
    public void when_call_product_should_return_ok() throws IOException {
        Mockito.doReturn(this.getReponseEntity()).when(service).getProduct(Mockito.anyString(), Mockito.anyByte());
        final ResponseEntity response = this.controller.getProduct("EMSS", 1);
        Mockito.verify(this.service).getProduct(product.capture(), quantity.capture());
        Assertions.assertEquals( "EMSS", product.getValue());
        Assertions.assertEquals( 1 , quantity.getValue());
    }

    @Test
    public void when_call_product_should_return_bad_request() throws IOException {
        final ResponseEntity response = this.controller.getProduct("EMSS", 0);
        Assertions.assertEquals( 400, response.getStatusCodeValue());
    }

    private ResponseHttp getResponse(){
        final ResponseHttp responseBody = new ResponseHttp();
        responseBody.setSumOfQuantity(1);
        responseBody.setSumOfVolume(new BigDecimal(10));
        responseBody.setAvgPrice(new BigDecimal(10));
        responseBody.setStore(Arrays.asList(new Store()));
        return responseBody;
    }

    private ResponseEntity<ResponseHttp> getReponseEntity(){
        return ResponseEntity.ok(this.getResponse());
    }
}
