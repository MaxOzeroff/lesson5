package ru.ozerov.stepup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ozerov.stepup.entities.Agreement;

import java.util.Optional;

public interface AgreementRepository extends JpaRepository<Agreement, Integer> {
    Optional<Agreement> findByNumber(String number);
}
