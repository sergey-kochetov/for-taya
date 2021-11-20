package com.example.buysell.services;

import com.example.buysell.models.Product;
import com.example.buysell.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @PersistenceContext
    private EntityManager em;


    public void saveProduct(Product product) {
        em.persist(product);
    }

    public void deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            em.remove(product);
        }
    }

    public List<Product> getProductByTitle(String title) {
        return title == null || title.isEmpty() ? productRepository.findAll() : productRepository.findByTitle(title);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
