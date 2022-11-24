package generator.config;

import generator.interceptor.DeveloperInterceptor;
import generator.interceptor.LoginInterceptor;
import generator.interceptor.OperationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    LoginInterceptor loginInterceptor;
    @Autowired
    OperationInterceptor operationInterceptor;
    @Autowired
    DeveloperInterceptor developerInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] generalApi =
                {"/**/login/**/",
                "/**/user/register/**/",
                "/**/app/download/**/",
                "/**/app/list/**/",
                "/**/app/getIcon/**/",
                "/**/app/getImg/**/",
                "/**/app/findByName/**/",
                "/**/app/findByType/**/"
                };
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(generalApi);

        String[] developerApi = {   "/**/app/update/**/",
                                    "/**/app/upload/**/",
                                    "/**/app/addApp/**/",
                                    "/**/app/uploadImg/**/",
                                    "/**/app/deleteImg/**/",
                                    "/**/app/uploadIcon/**/",
                                    "/**/app/updateInfo/**/"
                                };
        registry.addInterceptor(developerInterceptor).addPathPatterns(developerApi);
        WebMvcConfigurer.super.addInterceptors(registry);


        String[] importantApi = {"/**/app/update/**/","/**/user/managerUpdate/**/","/**/user/list/**/","/**/user/delete/**/","/**/user/getLog/**/"};
        registry.addInterceptor(operationInterceptor).addPathPatterns(importantApi);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
