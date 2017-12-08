package com.shiny.backend.disconf.config;

import com.baidu.disconf.client.DisconfMgrBean;
import com.baidu.disconf.client.DisconfMgrBeanSecond;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


/**
 * @author shiny
 **/
@Configuration
public class DisconfConfiguration implements EnvironmentAware{

    @Bean(destroyMethod = "destroy")
    public DisconfMgrBean disconfMgrBean(){
        DisconfMgrBean disconfMgrBean=new DisconfMgrBean();
        disconfMgrBean.setScanPackage("com.shiny.backend");
        return disconfMgrBean;
    }

    @Bean(destroyMethod = "destroy",initMethod = "init")
    public DisconfMgrBeanSecond disconfMgrBeanSecond(){
        return new DisconfMgrBeanSecond();
    }

    @Override
    public void setEnvironment(Environment environment) {
        String env = environment.getProperty("disconf");
        if(env != null) {
            System.setProperty("disconf.env", env);
        }
    }
}
