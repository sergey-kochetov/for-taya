package com.example.buysell.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity(name = "products")
@AllArgsConstructor
@Transactional(propagation = Propagation.MANDATORY)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String title;
    private String description;
    private int price;
    private String city;
    private String author;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();

    @Column(name = "preview_image_id")
    private Long previewImageId;

    @Column(name = "date_of_created", nullable = false)
    private Instant dateOfCreated;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    public void init() {
        dateOfCreated = Instant.now();
    }

    public Product addImage(Image image) {
        image.setProduct(this);
        if (image.isPreviewImage()) {
            this.previewImageId = image.getId();
        }
        images.add(image);
        return this;
    }
}
