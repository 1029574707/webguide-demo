package com.ceiec.webguide.formal.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * CreateDate: 2018/4/24 <br/>
 * Author: WangHao <br/>
 * Description: 增加静态文件路径
 **/
@Configuration
public class WebStaticFileConfig extends WebMvcConfigurerAdapter {

    private static final String WINDOWS = "windows";

    private static final String WINDOWSSTATICFILE = "file:E:/staticfile/";

    private static final String LINUXSTATICFILE = "file:/usr/webguide/staticfile/";

    private static final String REQUESTURL = "/static/**";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String osname = System.getProperty("os.name").toLowerCase();
        if (osname.contains(WINDOWS)) {
            registry.addResourceHandler(REQUESTURL).addResourceLocations(WINDOWSSTATICFILE);
        } else {
            registry.addResourceHandler(REQUESTURL).addResourceLocations(LINUXSTATICFILE);
        }
    }
}
