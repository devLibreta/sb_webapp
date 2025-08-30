package com.dev.sbWebapp.domain.ktcomb.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class KtCombToolListDto {

    private List<KtCombToolDto> ktCombToolList;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class KtCombToolDto {
        private String id;
        private String name;
        private String description;

        @Builder
        public KtCombToolDto(String id, String name, String description) {
            this.id = id;
            this.name = name;
            this.description = description;
        }
    }

    @Builder
    public KtCombToolListDto(List<KtCombToolDto> ktCombToolList) {
        this.ktCombToolList = ktCombToolList;
    }
}
