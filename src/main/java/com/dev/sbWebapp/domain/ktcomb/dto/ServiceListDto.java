package com.dev.sbWebapp.domain.ktcomb.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ServiceListDto {

    private List<ServiceDto> services;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ServiceDto {
        private String id;
        private String name;
        private String description;

        @Builder
        public ServiceDto(String id, String name, String description) {
            this.id = id;
            this.name = name;
            this.description = description;
        }
    }

    @Builder
    public ServiceListDto(List<ServiceDto> services) {
        this.services = services;
    }
}
