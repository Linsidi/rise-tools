package com.linsidi.properties;

import lombok.Data;

/**
 * @author linsidi 全局变量
 * @date 2021-01-06 14:49
 * @description
 **/
@Data
public class DataSourceProperties {

    private String url;

    private String driverClassName;

    private String username;

    private String password;
}
