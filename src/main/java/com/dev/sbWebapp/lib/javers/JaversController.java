package com.dev.sbWebapp.lib.javers;

import com.dev.sbWebapp.domain.ktcomb.service.KtCombToolRetvService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/javers")
@RequiredArgsConstructor
public class JaversController {

    private final JaversService javersService;

    @PostMapping("/init")
    public String init() {
        javersService.init();
        return "true";
    }
}
