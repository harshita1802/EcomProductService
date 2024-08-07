package dev.harshita.EcomProductService.EcomProductService.repository;

import dev.harshita.EcomProductService.EcomProductService.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RatingRepository extends JpaRepository<Rating, UUID> {
}
