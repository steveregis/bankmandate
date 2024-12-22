package com.mandatesystem.repository;

import com.mandatesystem.domain.Mandate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MandateRepository extends JpaRepository<Mandate, UUID> {
    Optional<Mandate> findByMandateTypeAndEffectiveDate(String mandateType, LocalDate effectiveDate);
}
