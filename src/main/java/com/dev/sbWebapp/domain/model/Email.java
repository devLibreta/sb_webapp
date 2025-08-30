package com.dev.sbWebapp.domain.model;

import lombok.*;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"value"})
public class Email {

    @jakarta.validation.constraints.Email
    @Column(name = "email", length = 50)
    @NotEmpty
    private String value;

    @Builder
    private Email(final String value) {
        this.value = value;
    }

    public static Email of(final String value) {
        return new Email(value);
    }

    public String getId() {
        final int index = value.indexOf("@");
        return index == -1 ? null : value.substring(0, index);
    }

    public String getHost() {
        final int index = value.indexOf("@");
        return index == -1 ? null : value.substring(index + 1);
    }
}
