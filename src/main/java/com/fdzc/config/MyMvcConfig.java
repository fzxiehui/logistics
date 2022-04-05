package com.fdzc.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@Transactional//自动管理事务
@EnableSwagger2 //开启Swagger2
public class MyMvcConfig implements WebMvcConfigurer {

    // 文件保存在真实目录/upload/下，
    // 访问的时候使用虚路径/upload，比如文件名为1.png，就直接/upload/1.png就ok了。
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:"+System.getProperty("user.dir")+"/upload/");
    }


    //配置了Swagger的bean实例
    @Bean
    public Docket docket(Environment enviroment){
        /*******下********附加在不同的dev环境下用不同的配置***************/
        //设置要显示的swagger环境dev或者test 或者其他自己配置的环境
        Profiles profiles = Profiles.of("dev");
        //获取项目的生产环境(通过enviroment.acceptsProfiles判断是否处在自己设计的环境当中)
        boolean flag = enviroment.acceptsProfiles(profiles);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(flag)//控制swagger是否开启  false在浏览器中不开启  flag=true表示开启  false不开启
                .groupName("java开发Editor")
                .select()
                //RequestHandlerSelectors，配置要扫描接口的方式
                //.basePackage:指定要扫描的包basePackage("com.kuang.swagger.controller")
                //.any()扫描全部
                //.none()都不扫描
                //.withClassAnnotation()扫描类上的注解  需要的参数是一个注解的反射对象
                //.withMethodAnnotation(GetMapping.class)扫描方法上的注解
                //.withClassAnnotation(RestController.class)现在只会扫描有RestController注解的这个类
                .apis(RequestHandlerSelectors.basePackage("com.engineerb21.controller"))
                .paths(PathSelectors.ant("/**"))//过滤什么路径  /kuang/**只扫描狂下的接口*/
                .build();
    }
    //配置Swagger信息=apiInfo
    private ApiInfo apiInfo(){
        //作者信息
        Contact contact = new Contact("闽a青玉柳", "test","2915399557@qq.com");
        return new ApiInfo(
                "SwaggerAPI文档",
                "即使再小的帆也能远航",
                "V1.0",
                "localhost:8080/",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
    //配置多个Docket分组（实例）  分工合作
    @Bean
    public Docket docket1(Environment enviroment){
        /*******下********附加在不同的dev环境下用不同的配置***************/
        //设置要显示的swagger环境dev或者test 或者其他自己配置的环境
        Profiles profiles = Profiles.of("dev");
        //获取项目的生产环境(通过enviroment.acceptsProfiles判断是否处在自己设计的环境当中)
        boolean flag = enviroment.acceptsProfiles(profiles);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(flag).groupName("A")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.engineerb21.pojo"))
                .build();
        //控制swagger是否开启  false在浏览器中不开启  flag=true表示开启  false不开启
    }


    //注册乐观锁插件
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    //分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

    //逻辑删除组件
    @Bean
    public ISqlInjector sqlInjector(){
        return new LogicSqlInjector();
    }

    /*sql执行效率插件*/
    @Bean
    @Profile({"dev"})  //设置dev test 环境开启,保证我们的效率
    public PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        //在工作种不允许用户等待,可以把超过100ms的sql进行拦截
        //只要超过规定时间就会报异常
        performanceInterceptor.setMaxTime(1000);//100ms 设置sql能够执行的最大时间，如果超过了就不执行
        performanceInterceptor.setFormat(true);//是否开启生成sql代码
        return performanceInterceptor;
    }
}