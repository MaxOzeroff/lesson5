package ru.ozerov.stepup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ozerov.stepup.entities.RefProductRegisterType;

import java.util.Optional;

public interface RefProductRegisterTypeRepository extends JpaRepository<RefProductRegisterType, Integer> {
    Optional<RefProductRegisterType> findByValue(String value);
}
