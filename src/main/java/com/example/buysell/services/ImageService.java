package com.example.buysell.services;

import com.example.buysell.models.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@AllArgsConstructor
public class ImageService {

    @PersistenceContext
    private EntityManager em;

    public void addImage(Product product, MultipartFile file1) {

    }
}
