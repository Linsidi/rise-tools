package com.linsidi.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 *@description 描述 yml配置类
 *@author 作者 adi
 *@createTime 创建时间  2021-01-07 09:49
 *@other 修改人和其它信息
 */
@ConfigurationProperties(prefix = "generator-mp")
@Configuration
@Data
public class GeneratorProperties {

    DataSourceProperties datasource = new DataSourceProperties();

    GlobalProperties globalConfig = new GlobalProperties();

    PackageProperties packageConfig = new PackageProperties();

    StrategyProperties strategyConfig = new StrategyProperties();

    OutputProperties outputConfig = new OutputProperties();


}
