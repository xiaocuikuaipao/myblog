package com.usst.myblog.config;

//import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.usst.myblog.Interceptor.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/index").setViewName("index");
//        registry.addViewController("/index.html").setViewName("index");
//        registry.addViewController("/").setViewName("index");
//        registry.addViewController("/blog/toindex").setViewName("index");
//        registry.addViewController("/").setViewName("index");

    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/","/admin/login","/admin");

    }

    // 分页插件
//    @Bean
//    public PaginationInterceptor paginationInterceptor() { return new PaginationInterceptor(); }
}
