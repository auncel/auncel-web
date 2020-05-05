package dev.yidafu.auncel.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @url https://blog.csdn.net/tiangongkaiwu152368/article/details/81099169
 * @url https://segmentfault.com/a/1190000018904390
 */
@Configuration
public class CorsConfig  implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://www.auncel.top", "http://localhost:5000",
                        "http://admin.auncel.top", "http://localhost:8000")
                .allowedMethods("GET", "HEAD", "POST","PUT", "DELETE", "OPTIONS")
                .allowCredentials(true);
    }
}
