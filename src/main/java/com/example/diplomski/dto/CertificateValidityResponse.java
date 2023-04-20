package com.example.diplomski.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
public class CertificateValidityResponse {
    private boolean valid;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String reason;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date revokedAt;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date validAfter;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date validUntil;

    public CertificateValidityResponse(Date validAfter, Date validUntil) {
        this.valid = true;
        this.validAfter = validAfter;
        this.validUntil = validUntil;
    }

    public CertificateValidityResponse(String reason, Date validAfter, Date validUntil) {
        this.valid = false;
        this.reason = reason;
        this.validAfter = validAfter;
        this.validUntil = validUntil;
    }

    public CertificateValidityResponse(String reason, Date revokedAt) {
        this.valid = false;
        this.reason = reason;
        this.revokedAt = revokedAt;
    }

    public CertificateValidityResponse(String message) {
        this.message = message;
    }
}
