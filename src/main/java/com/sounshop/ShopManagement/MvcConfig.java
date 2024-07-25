package com.sounshop.ShopManagement;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import lombok.NonNull;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
  
  @Override
	public void addViewControllers(@NonNull ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("login");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/home").setViewName("home");
		// registry.addViewController("/author-management").setViewName("authorManagement");
	}
}