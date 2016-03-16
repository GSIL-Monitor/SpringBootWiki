/*
 * Copyright (c) 2010-2016.  by Moses   All rights reserved.
 *
 */

package net.liuxuan;

import net.liuxuan.wiki.db.DBSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.FilterRegistration;

/**
 * Created by Moses on 2016/2/3.
 */


/*其中base-package为需要扫描的包（含所有子包）
     @Service用于标注业务层组件，
     @Controller用于标注控制层组件（如struts中的action）,
     @Repository用于标注数据访问组件，即DAO组件，
     @Component泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注。
*/

//SpringApplication是Spring Boot框架中描述Spring应用的类，它的run()方法会创建一个Spring应用上下文（Application Context）。另一方面它会扫描当前应用类路径上的依赖，例如本例中发现spring-webmvc（由 spring-boot-starter-web传递引入）在类路径中，那么Spring Boot会判断这是一个Web应用，并启动一个内嵌的Servlet容器（默认是Tomcat）用于处理HTTP请求。
// 注解“@RestController”和”@RequestMapping”由 spring MVC 提供，用来创建 REST 服务。
// 这两个注解和 spring boot 本身并没有关系。
//“@EnableAutoConfiguration”注解的作用在于
// 让 spring boot 根据应用所声明的依赖来对 spring 框架进行自动配置，这就减少了开发人员的工作量。

//@Configuration
//@ComponentScan
//@EnableAutoConfiguration
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication //等同于 @Configuration @EnableAutoConfiguration @ComponentScan
//@ImportResource({ "classpath:config/webSecurityConfig.xml" })
//@EnableConfigurationProperties({DBSettings.class})
@EnableJpaRepositories(basePackages = "net.liuxuan.SprKi.repository")
//@EnableWebMvc
@EnableTransactionManagement
@EnableAspectJAutoProxy
//@EnableScheduling
public class ApplicationMain extends SpringBootServletInitializer {

    private static Logger log  = LoggerFactory.getLogger(ApplicationMain.class);


    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(ApplicationMain.class);
        //app.setBannerMode(Banner.Mode.OFF);
        ApplicationContext ctx = app.run(args);


//        String beanNames[] = ctx.getBeanDefinitionNames();
//        Arrays.sort(beanNames);
//        for (String beanName : beanNames) {
//            System.out.println(beanName);
//        }

//        SpringApplication.run(ApplicationMain.class, args);

        log.info("\r\n==============\r\nApplication Started\n==============");

        mainTest();
//





    }

    private static void mainTest() {

        log.trace("======trace");
        log.debug("======debug");
        log.info("======info");
        log.warn("======warn");
        log.error("======error");


//        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
//        StatusPrinter.print(lc);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApplicationMain.class);
//        return super.configure(application);
    }




}
