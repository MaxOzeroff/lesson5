package ru.ozerov.stepup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ozerov.stepup.entities.RefProductClass;

import java.util.Optional;

public interface RefProductClassRepository extends JpaRepository<RefProductClass, Integer> {
    Optional<RefProductClass> findByValue(String value);
}
