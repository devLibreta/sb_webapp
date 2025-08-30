package com.dev.sbWebapp.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    @NotEmpty
    @Column(name = "address1", nullable = false)
    private String address1;

    @NotEmpty
    @Column(name = "address2", nullable = false)
    private String address2;

    @NotEmpty
    @Column(name = "zipcode", nullable = false)
    private String zipcode;

    @Builder
    public Address(String address1, String address2, String zipcode) {
        this.address1 = address1;
        this.address2 = address2;
        this.zipcode = zipcode;
    }
}