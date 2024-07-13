package dev.harshita.EcomProductService.EcomProductService.repository;

import dev.harshita.EcomProductService.EcomProductService.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Category findByName(String name);

}
