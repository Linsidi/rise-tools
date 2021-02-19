package com.linsidi.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;

/**
 *@description 描述
 *@author 作者 adi
 *@createTime 创建时间  2021-01-06 17:51
 *@other 修改人和其它信息
 */
@Configuration
public class GeneratorContext implements ApplicationContextAware {

    private static ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        if (this.applicationContext == null) {
            this.applicationContext = applicationContext;
        }
    }

    public static MybatisPlusGenerator getGenerator() {
        MybatisPlusGenerator generator = applicationContext.getBean(MybatisPlusGenerator.class);
        return generator;
    }
}
