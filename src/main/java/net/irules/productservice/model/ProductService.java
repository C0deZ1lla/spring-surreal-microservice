package net.irules.productservice.model;

import com.surrealdb.driver.SyncSurrealDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private SyncSurrealDriver surrealDriver;

    public List<Product> getAll() {
        return surrealDriver.select("product", Product.class);
    }

    public Product createNew(NewProductDTO newProductDTO) {
        Product product = new Product();
        product.setName(newProductDTO.getName());
        product.setDescription(newProductDTO.getDescription());
        product.setPrice(newProductDTO.getPrice());

        surrealDriver.create("product", product);

        return product;
    }
}
