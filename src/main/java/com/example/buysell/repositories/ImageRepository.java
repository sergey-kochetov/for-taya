package com.example.buysell.repositories;

import com.example.buysell.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}