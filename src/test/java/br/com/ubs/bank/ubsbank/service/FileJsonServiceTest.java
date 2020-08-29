package br.com.ubs.bank.ubsbank.service;


import br.com.ubs.bank.ubsbank.model.FileJson;
import br.com.ubs.bank.ubsbank.model.Product;
import br.com.ubs.bank.ubsbank.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.property.access.spi.Setter;
import org.junit.Before;
import org.junit.Test;

import org.junit.jupiter.api.Assertions;
import org.mockito.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;

public class FileJsonServiceTest {
    @InjectMocks
    private FileJsonService fileJsonService;
    @Mock
    private ObjectMapper mapper;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private String path = "/Users/ricardossbr/dev/ubs-test/src/test/java/resources";

    @Captor
    private ArgumentCaptor<List<Product>> product;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void when_process_files_should_process_ok() throws IOException {
       /* Mockito.doReturn(this.getFileJson()).when(this.mapper).readValue(Mockito.anyString(), eq(FileJson.class));
        doNothing().when(this.productRepository).deleteAll();
        Mockito.doReturn(this.getListProduct()).when(this.productRepository).saveAll(Mockito.anyIterable());
        this.fileJsonService.processFiles();
        Mockito.verify(this.productRepository).saveAll(product.capture());
        Assertions.assertEquals( "EMSS", product.getValue());*/
    }


    private FileJson getFileJson(){
        final FileJson file = new FileJson();
        final Product pro = new Product();
        pro.setIndustry("Disney");
        pro.setOrigin("SP");
        pro.setPrice("$7.02");
        pro.setProduct("EMMS");
        pro.setQuantity(10);
        pro.setType("2XL");
        file.setData(Arrays.asList(pro, pro, pro));
        return file;
    }

    private List<Product> getListProduct(){
        final Product pro = new Product();
        pro.setIndustry("Disney");
        pro.setOrigin("SP");
        pro.setPrice("$7.02");
        pro.setProduct("EMMS");
        pro.setQuantity(10);
        pro.setType("2XL");
        return Arrays.asList(pro, pro, pro);
    }
}
