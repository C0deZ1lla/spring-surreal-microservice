package net.irules.productservice.model;

import com.surrealdb.driver.SyncSurrealDriver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAll() {
        return productService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createNew(@RequestBody NewProductDTO newProductDTO) {
        if (newProductDTO.getName() == null || newProductDTO.getName().isEmpty()) {
            log.info("Name is required");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name is required");
        }

        if (newProductDTO.getDescription() == null || newProductDTO.getDescription().isEmpty()) {
            log.info("Description is required");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Description is required");
        }

        if (newProductDTO.getPrice() == null) {
            log.info("Price is required");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Price is required");
        }

        log.info("Creating new product: {}", newProductDTO);
        return productService.createNew(newProductDTO);
    }
}
