package com.mandatesystem.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
public class Mandate {

    @Id
    @GeneratedValue
    private UUID mandateId; // Unique ID for each mandate

    @Column(nullable = false)
    private String mandateType;

    @Column(nullable = false)
    private LocalDate effectiveDate;

    @Column(nullable = false)
    private LocalDate expiryDate;

    /*@ElementCollection
    @CollectionTable(name = "mandate_approvers", joinColumns = @JoinColumn(name = "mandate_id"))
    @Column(name = "approver")
    private List<String> approvers; // List of approvers*/

    @Column(columnDefinition = "TEXT")
    private String instructions;

    @Version
    private int version=0; // Optimistic locking version field

    // Optional: Field for tracking who last updated the entity
    @LastModifiedBy
    private String lastModifiedBy;

    // Optional: Field for tracking when the entity was last updated
    @LastModifiedDate
    private LocalDate lastModifiedDate;

    // Getters and Setters
    public UUID getMandateId() {
        return mandateId;
    }

    public void setMandateId(UUID mandateId) {
        this.mandateId = mandateId;
    }

    public String getMandateType() {
        return mandateType;
    }

    public void setMandateType(String mandateType) {
        this.mandateType = mandateType;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
/* 
    public List<String> getApprovers() {
        return approvers;
    }

    public void setApprovers(List<String> approvers) {
        this.approvers = approvers;
    }
*/
    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public LocalDate getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDate lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime now) {
    }

    public void setAttachments(List<String> attachments) {
    }
}
