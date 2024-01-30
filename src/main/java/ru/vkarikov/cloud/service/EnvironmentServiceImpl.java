package ru.vkarikov.cloud.service;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RefreshScope
public class EnvironmentServiceImpl implements EnvironmentService {

    private String prop;
    private final Environment environment;

    public EnvironmentServiceImpl(Environment environment) {
        this.environment = environment;
    }

    @Override
    public String getEnvironment() {
        return prop;
    }

    @PostConstruct
    private void postConstruct() {
        this.prop = "prop=" + environment.getProperty("my.prop") + " prop2=" + environment.getProperty("my.prop2") + " postConstruct";
        System.out.println("PostConstruct");
    }
}
