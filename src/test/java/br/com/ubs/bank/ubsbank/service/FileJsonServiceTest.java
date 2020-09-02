package br.com.ubs.bank.ubsbank.service;


import br.com.ubs.bank.ubsbank.model.FileJson;
import br.com.ubs.bank.ubsbank.model.Product;
import br.com.ubs.bank.ubsbank.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.*;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;

public class FileJsonServiceTest {
    @InjectMocks
    private FileJsonService fileJsonService;
    @Spy
    private ObjectMapper mapper;
    @Mock
    private ProductRepository productRepository;

    @Captor
    private ArgumentCaptor<List<Product>> product;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(fileJsonService, "path", "src/test/java/resources");
    }

    @Test
    public void when_process_files_should_process_ok() throws IOException {
        doNothing().when(this.productRepository).deleteAll();
        Mockito.doReturn(this.getListProduct()).when(this.productRepository).saveAll(Mockito.anyIterable());
        this.fileJsonService.processFiles();
        Mockito.verify(this.productRepository, times(3)).saveAll(product.capture());
        Assertions.assertNotNull( product.getValue());
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
