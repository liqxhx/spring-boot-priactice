package com.lqh.priactice.spring.security.jmx;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.jmx.access.MBeanProxyFactoryBean;
import org.springframework.jmx.support.MBeanServerConnectionFactoryBean;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.net.MalformedURLException;

/**
 * <p> 类描述: JmxConfiguration
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/11/09 13:57
 * @since 2020/11/09 13:57
 */
public class JmxConfiguration {
//    @Bean
//    public MBeanServerConnectionFactoryBean calloutCfgMBeanServerConnectionFactoryBean() {
//        MBeanServerConnectionFactoryBean mbeanServerConnection =  new MBeanServerConnectionFactoryBean();
//        try {
//            mbeanServerConnection.setServiceUrl("service:jmx:rmi://10.190.45.38/jndi/rmi://10.190.45.38:2099/alarmConnector");
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        mbeanServerConnection.setConnectOnStartup(false);
//        return mbeanServerConnection;
//    }
//
//    @Bean
//    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//    public MBeanProxyFactoryBean mBeanProxyFactoryBean() {
//        MBeanProxyFactoryBean mbean = new MBeanProxyFactoryBean();
//        mbean.setConnectOnStartup(false);
//        try {
//            mbean.setObjectName(ObjectName.getInstance("org.springframework.boot:type=Endpoint,name=Calloutcfg"));
//        } catch (MalformedObjectNameException e) {
//            e.printStackTrace();
//        }
//        mbean.setProxyInterface(org.springframework.boot.actuate.endpoint.jmx.EndpointMBean);
//        return mbean;
//    }

}
