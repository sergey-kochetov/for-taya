package com.example.buysell.services;

import com.example.buysell.models.Image;
import com.example.buysell.models.Product;
import com.example.buysell.repositories.ImageRepository;
import com.example.buysell.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ImageService imageService;
    private final ImageRepository imageRepository;

    @PersistenceContext
    private EntityManager em;


    public void saveProduct(Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        Image image1;
        Image image2;
        Image image3;
        if (file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);

            product.addImage(imageRepository.save(image1));
        }
        if (file2.getSize() != 0) {
            image2 = toImageEntity(file2);
            product.addImage(imageRepository.save(image2));
        }
        if (file3.getSize() != 0) {
            image3 = toImageEntity(file3);
            product.addImage(imageRepository.save(image3));
        }
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

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }
}
