package com.example.buysell.repositories;

import com.example.buysell.models.Product;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends Repository<Product, Long> {
    List<Product> findAll();
    List<Product> findByTitle(String title);

    Optional<Product> findById(Long id);

}