package org.walavo.bar.generate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;

import java.awt.image.BufferedImage;

@SpringBootApplication
public class BarGenerateApplication {

    public static void main(String[] args) {
        SpringApplication.run(BarGenerateApplication.class, args);
    }
}
