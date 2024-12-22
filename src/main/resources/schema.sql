CREATE TABLE Mandate (
    mandate_id UUID PRIMARY KEY,
    mandate_type VARCHAR(255),
    effective_date DATE,
    expiry_date DATE,
    instructions TEXT,
    version INT DEFAULT 0,
    last_modified_by VARCHAR(255),
    last_modified_date TIMESTAMP
);
