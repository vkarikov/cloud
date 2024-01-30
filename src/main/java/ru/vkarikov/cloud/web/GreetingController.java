package ru.vkarikov.cloud.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vkarikov.cloud.service.EnvironmentService;
import ru.vkarikov.cloud.service.RefreshContextService;

@RestController
@RequestMapping("/")
public class GreetingController {
    private final EnvironmentService environmentService;
    private final RefreshContextService refreshContextService;

    public GreetingController(EnvironmentService environmentService,
                              RefreshContextService refreshContextService) {
        this.environmentService = environmentService;
        this.refreshContextService = refreshContextService;
    }

    @GetMapping("/hello")
    public void hello() {
        System.out.println("hello " + environmentService.getEnvironment());
    }

    @PostMapping("/refresh")
    public void refresh() {
        refreshContextService.refresh();
        System.out.println("refresh context");
    }

}
