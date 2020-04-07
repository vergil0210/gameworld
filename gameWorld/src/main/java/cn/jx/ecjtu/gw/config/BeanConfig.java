package cn.jx.ecjtu.gw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class BeanConfig {
    @Bean(name = "Random")
    public Random generate() {
        return new Random();
    }
}