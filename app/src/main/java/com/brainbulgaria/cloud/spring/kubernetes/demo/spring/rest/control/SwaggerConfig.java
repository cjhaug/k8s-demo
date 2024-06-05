package com.brainbulgaria.cloud.spring.kubernetes.demo.spring.rest.control;

import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi greetingV1Api() {
        return GroupedOpenApi
                .builder()
                .group("Greeting API V1")
                .packagesToScan("com.brainbulgaria.cloud.spring.kubernetes.demo.api.greeting.v1.control")
                .pathsToMatch("/**")
                .addOpenApiCustomizer(apiInfoCustomizer("Greeting API", "API for greetings", "/greeting/v1", "1.0.0"))
                .build();
    }

    private OpenApiCustomizer apiInfoCustomizer(final String title, final String description, final String basePathWithVersion, final String version) {
        return openApi -> openApi
                .servers(List.of(new Server().url(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + basePathWithVersion)))
                .paths(openApi.getPaths().entrySet().stream().collect(
                        Paths::new,
                        (map, item) -> map.addPathItem(item.getKey().replaceFirst(basePathWithVersion, StringUtils.EMPTY), item.getValue()),
                        Paths::putAll
                ))
                .info(new Info()
                        .title(title)
                        .description(description)
                        .version(version));
    }

}
