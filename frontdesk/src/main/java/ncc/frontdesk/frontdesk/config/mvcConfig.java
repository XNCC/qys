package ncc.frontdesk.frontdesk.config;


import ncc.frontdesk.frontdesk.Interceptor.myInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class mvcConfig implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //其他静态资源，与本文关系不大
        //需要配置1：--需要告知系统，这是要被当成静态文件的！
        //第一个方法设置访问路径前缀，第二个方法设置资源路径
        //这是windows的配置方式，你可以声明一个变量在系统机器上调试时只需要修改变量名即可机器
        //registry.addResourceHandler("/temp-Cover/**").addResourceLocations("file:E:/Cover/");
        registry.addResourceHandler("/temp-Cover/**").addResourceLocations("views/*");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //   super.addResourceHandlers(registry);
    }

    //实现拦截器 要拦截的路径以及不拦截的路径
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(new myInterceptor()).addPathPatterns("/**").excludePathPatterns("/", "/login");
    }


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}