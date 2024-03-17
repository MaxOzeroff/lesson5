package ru.ozerov.stepup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ozerov.stepup.entities.ProductRegister;

import java.util.Optional;

public interface ProductRegisterRepository extends JpaRepository<ProductRegister, Integer> {
    Optional<ProductRegister> findByIdAndType(Integer id, String type);
}
