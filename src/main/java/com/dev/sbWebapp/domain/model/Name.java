package com.dev.sbWebapp.domain.model;

import lombok.*;
import org.springframework.util.StringUtils;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"first", "middle", "last"})
public class Name {

    @NotEmpty
    @Column(name = "first_name", nullable = false, length = 50)
    private String first;

    @Column(name = "middle_name", length = 50)
    private String middle;

    @NotEmpty
    @Column(name = "last_name", nullable = false, length = 50)
    private String last;

    @Builder
    public Name(final String first, final String middle, String last) {
        this.first = first;
        this.middle = StringUtils.hasText(middle) ? middle : null;
        this.last = last;
    }

    // 전체 이름 반환 (중간 이름 없으면 제외)
    public String getFullName() {
        if (this.middle == null) {
            return String.format("%s %s", this.first, this.last);
        }
        return String.format("%s %s %s", this.first, this.middle, this.last);
    }
}