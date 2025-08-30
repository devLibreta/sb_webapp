package com.dev.sbWebapp.domain.ktcomb.controller;

import com.dev.sbWebapp.domain.ktcomb.service.KtCombToolRetvService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ktcomb")
@RequiredArgsConstructor
public class KtCombToolController {

    private final KtCombToolRetvService ktCombToolRetvService;

    @GetMapping("/home")
    public String init(Model model) {
        model.addAttribute("ktCombToolList", ktCombToolRetvService.ktCombToolListRetv());

        return "home";
    }
}
