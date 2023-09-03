package com.teamall.common.config;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Sets;
import com.spring4all.swagger.EnableSwagger2Doc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.Operation;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spi.service.contexts.DocumentationContext;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author DengQiao
 * @date 2023-9-2 0002
 */
@Configuration
@EnableSwagger2Doc
@EnableWebMvc
@Slf4j
public class SwaggerConfig implements ApplicationListener<WebServerInitializedEvent> , ApiListingScannerPlugin {

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        try {
            // 获取IP
            String hostAddress = Inet4Address.getLocalHost().getHostAddress();
            // 获取端口号
            int port = event.getWebServer().getPort();
            // 获取应用名
            String applicationName = event.getApplicationContext().getApplicationName();
            // 打印 swagger 文档地址
            log.info("\n项目启动启动成功！swagger 接口文档地址: http://" + hostAddress + ":" + port + applicationName + "/swagger-ui.html"+
                    "\n项目启动启动成功！swagger2 接口文档地址: http://" + hostAddress + ":" + port + applicationName + "/doc.html");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    /**
     * Implement this method to manually add ApiDescriptions
     * 实现此方法可手动添加ApiDescriptions
     *
     * @param context - Documentation context that can be used infer documentation context
     * @return List of {@link ApiDescription}
     * @see ApiDescription
     */
    @Override
    public List<ApiDescription> apply(DocumentationContext context) {
        Operation usernamePasswordOperation = new OperationBuilder(new CachingOperationNameGenerator())
                .method(HttpMethod.POST)
                .summary("用户名密码登录")
                .notes("username/password登录")
                .consumes(Sets.newHashSet(MediaType.APPLICATION_FORM_URLENCODED_VALUE)) // 接收参数格式
                .produces(Sets.newHashSet(MediaType.APPLICATION_JSON_VALUE)) // 返回参数格式
                .tags(Sets.newHashSet("登录"))
                .parameters(Arrays.asList(
                        new ParameterBuilder()
                                .description("用户名")
                                .type(new TypeResolver().resolve(String.class))
                                .name("username")
                                .defaultValue("admin")
                                .parameterType("query")
                                .parameterAccess("access")
                                .required(true)
                                .modelRef(new ModelRef("string"))
                                .build(),
                        new ParameterBuilder()
                                .description("密码")
                                .type(new TypeResolver().resolve(String.class))
                                .name("password")
                                .defaultValue("admin")
                                .parameterType("query")
                                .parameterAccess("access")
                                .required(true)
                                .modelRef(new ModelRef("string"))
                                .build()
                        //new ParameterBuilder()
                        //        .description("验证码唯一标识")
                        //        .type(new TypeResolver().resolve(String.class))
                        //        .name("randomKey")
                        //        .defaultValue("666666")
                        //        .parameterType("query")
                        //        .parameterAccess("access")
                        //        .required(true)
                        //        .modelRef(new ModelRef("string"))
                        //        .build(),
                        //new ParameterBuilder()
                        //        .description("验证码")
                        //        .type(new TypeResolver().resolve(String.class))
                        //        .name("code")
                        //        .parameterType("query")
                        //        .parameterAccess("access")
                        //        .required(true)
                        //        .modelRef(new ModelRef("string"))
                        //        .build()
                ))
                .responseMessages(Collections.singleton(
                        new ResponseMessageBuilder().code(200).message("请求成功")
                                .responseModel(new ModelRef(
                                        "xyz.gits.boot.common.core.response.RestResponse")
                                ).build()))
                .build();

        ApiDescription loginApiDescription = new ApiDescription("login", "/login", "登录接口",
                Collections.singletonList(usernamePasswordOperation), false);

        return Collections.singletonList(loginApiDescription);
    }

    /**
     * 是否使用此插件
     *
     * @param documentationType swagger文档类型
     * @return true 启用
     */
    @Override
    public boolean supports(DocumentationType documentationType) {
        return DocumentationType.SWAGGER_2.equals(documentationType);
    }
}
