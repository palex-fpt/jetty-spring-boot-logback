package me.palex.sample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RestConfiguration {
    @Bean
    SampleController controller() {
        return new SampleController();
    }
}
