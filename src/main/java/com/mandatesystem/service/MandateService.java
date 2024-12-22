package com.mandatesystem.service;

import com.mandatesystem.domain.Mandate;
import com.mandatesystem.repository.MandateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MandateService {

    @Autowired
    private MandateRepository mandateRepository;

    /**
     * Get a mandate by its ID.
     */
    @Transactional(readOnly = true)
    public Optional<Mandate> getMandateById(UUID mandateId) {
        try {
            return mandateRepository.findById(mandateId);
        } catch (DataAccessException ex) {
            // Log and rethrow as a custom exception if needed
            throw new RuntimeException("Failed to fetch mandate by ID", ex);
        }
    }

    /**
     * Create a new mandate with retry logic for transient failures.
     */
    @Retryable(
        value = {DataAccessException.class, ObjectOptimisticLockingFailureException.class},
        maxAttempts = 3,
        backoff = @Backoff(delay = 1000, multiplier = 2)
    )
    @Transactional
    public Mandate createMandate(Mandate mandate) {
        try {
            // Additional validation or preprocessing can go here
            mandate.setVersion(0);
            return mandateRepository.save(mandate);
        } catch (DataAccessException ex) {
            // Log and rethrow as a custom exception if needed
            throw new RuntimeException("Failed to create mandate", ex);
        }
    }

    /**
     * Update an existing mandate with proper error handling.
     */
    @Transactional
    public Mandate updateMandate(Mandate updatedMandate) {
        try {
            // Ensure the mandate exists
            Mandate existingMandate = mandateRepository.findById(updatedMandate.getMandateId())
                .orElseThrow(() -> new RuntimeException("Mandate not found"));

            // Update fields as needed
            existingMandate.setMandateType(updatedMandate.getMandateType());
            existingMandate.setEffectiveDate(updatedMandate.getEffectiveDate());
            existingMandate.setExpiryDate(updatedMandate.getExpiryDate());
            existingMandate.setInstructions(updatedMandate.getInstructions());

            return mandateRepository.save(existingMandate);
        } catch (ObjectOptimisticLockingFailureException ex) {
            throw new RuntimeException("Optimistic locking failure while updating mandate", ex);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Failed to update mandate", ex);
        }
    }

    /**
     * Delete a mandate by ID with proper error handling.
     */
    @Transactional
    public void deleteMandate(UUID mandateId) {
        try {
            if (mandateRepository.existsById(mandateId)) {
                mandateRepository.deleteById(mandateId);
            } else {
                throw new RuntimeException("Mandate not found");
            }
        } catch (DataAccessException ex) {
            throw new RuntimeException("Failed to delete mandate", ex);
        }
    }

    public List<Mandate> getAllMandates() {
        return null;
    }
}
