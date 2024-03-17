package ru.ozerov.stepup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ozerov.stepup.entities.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByNumber(String number);
}
