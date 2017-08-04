package com.prohire.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "com.prohire", exclude = {HibernateJpaAutoConfiguration.class,
        DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class})
@EnableSwagger2
@ComponentScan(basePackages = {"com.prohire"},
        excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.prohire.security.*")
)
public class RestApplication {

    public static void main(String[] args){
        SpringApplication.run(RestApplication.class,args);}
}
