package com.dev.sbWebapp.domain.ktcomb.controller;

import com.dev.sbWebapp.domain.ktcomb.dto.ServiceListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/ktcomb")
@RequiredArgsConstructor
public class KtCombController {

    @GetMapping("/home")
    public String init(Model model) {

        ServiceListDto.ServiceDto service1 = ServiceListDto.ServiceDto.builder()
                .id("s001")
                .name("Service One")
                .description("첫 번째 서비스")
                .build();

        ServiceListDto.ServiceDto service2 = ServiceListDto.ServiceDto.builder()
                .id("s002")
                .name("Service Two")
                .description("두 번째 서비스!")
                .build();
        model.addAttribute("serviceList", ServiceListDto.builder()
                .services(List.of(service1, service2))
                .build());

        return "home";
    }
}
