package com.shiny.backend.swagger.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @author DELL
 */
@Configuration
@EnableSwagger2
@EnableWebMvc //必须存在
@ComponentScan(basePackages = "com.shiny.backend.swagger.controller")
public class SwaggerConfig extends WebMvcConfigurerAdapter implements EnvironmentAware{

    private RelaxedPropertyResolver propertyResolver;

    private static final Logger logger= LogManager.getLogger(SwaggerConfig.class);

    /**
     * 静态资源映射
     *
     * @param registry
     *            静态资源注册器
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }

    /**
     * 从配置文件中读取相关的配置
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver=new RelaxedPropertyResolver(environment,"swagger.");
    }

    @Bean
    public Docket swaggerSpringfoxDocket4KAD(){
        logger.debug("starting Swagger");
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name("Authentication").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        Docket swaggerSpringMvcPlugin=new Docket(DocumentationType.SWAGGER_2)
                .groupName("test")
                .apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.any())
                .paths(regex("/.*"))
                .build().globalOperationParameters(pars);
        stopWatch.stop();
        logger.debug("Started Swagger in {} ms",stopWatch.getTotalTimeMillis());
        return swaggerSpringMvcPlugin;
    }

    /**
     * 生成文档配置
     * @return
     */
    private ApiInfo apiInfo(){
        return new ApiInfo("Backend Api",
                "spring-boot 通用后台api",
                "1.0",
                "www.shinyling.online",
                new Contact("shinyling",
                        "www.shinyling.online",
                        "shiny123400@163.com"),
                "MIT2.0",
                "www.mit2.0.com");
    }
}
