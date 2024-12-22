package com.mandatesystem.graphql;

import com.mandatesystem.domain.Mandate;
import com.mandatesystem.service.MandateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.UUID;

@Controller
public class MandateGraphQLController {

    @Autowired
    private MandateService mandateService;

    /**
     * Create a new mandate.
     */
    @MutationMapping
    public Mandate createMandate(
        @Argument String mandateType,
        @Argument LocalDate effectiveDate,
        @Argument LocalDate expiryDate,
        @Argument String instructions
    ) {
        Mandate mandate = new Mandate();
        mandate.setMandateId(UUID.randomUUID());
        mandate.setMandateType(mandateType);
        mandate.setEffectiveDate(effectiveDate);
        mandate.setExpiryDate(expiryDate);
        mandate.setInstructions(instructions);

        return mandateService.createMandate(mandate);
    }

    /**
     * Update an existing mandate by ID.
     */
    @MutationMapping
    public Mandate updateMandate(
        @Argument UUID mandateId,
        @Argument String mandateType,
        @Argument LocalDate effectiveDate,
        @Argument LocalDate expiryDate,
        @Argument String instructions
    ) {
        Mandate updatedMandate = new Mandate();
        updatedMandate.setMandateId(mandateId);
        updatedMandate.setMandateType(mandateType);
        updatedMandate.setEffectiveDate(effectiveDate);
        updatedMandate.setExpiryDate(expiryDate);
        updatedMandate.setInstructions(instructions);

        return mandateService.updateMandate(updatedMandate);
    }

    /**
     * Get all mandates.
     */
    @QueryMapping
    public Iterable<Mandate> getMandates() {
        return mandateService.getAllMandates();
    }

    /**
     * Get a specific mandate by ID.
     */
    @QueryMapping
    public Mandate getMandateById(@Argument UUID mandateId) {
        return mandateService.getMandateById(mandateId).orElse(null);
    }

    /**
     * Delete a mandate by ID.
     */
    @MutationMapping
    public boolean deleteMandate(@Argument UUID mandateId) {
        mandateService.deleteMandate(mandateId);
        return true;
    }
}
