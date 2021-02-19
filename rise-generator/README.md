#mybatis-plus 代码自动生成工具类使用说明文档

##1、加入pom依赖
```xml
        <dependency>
            <groupId>com.one.tools</groupId>
            <artifactId>one-generator</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>

```

##2、yml配置

```yaml
generator-mp:
  #数据源配置
  datasource:
    driver-class-name: org.postgresql.Driver
    #配置成要生成的数据源
    url: jdbc:postgresql://127.0.0.1:5432/whsi_sszh_dev?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&yearIsDateType=false
    username: postgres
    password: 123456
  #输出自定模板开关 
  output-config:
    controller: true
    service: true
  #全局策略配置
  global-config:
    author: generator
    #项目模块名
    module:
  #包配置
  package-config:
    #业务模块名(包名)
    biz-module: sys
    #父目录路径
    parent-path: com.onesports.whds.sszh
  #数据库表配置
  strategy-config:
    #去除表前缀 多个以，分割
    table-prefix: sszh
    #去除字段前缀 多个以，分割
    filed-prefix:
    #实体继承父类完整路径
    super-entity-class: com.onesports.whds.common.domain.entity.BaseEntity
    #controller继承父类完整路径 先决条件 output-config: controller: true
    super-controller-class: com.onesports.whds.common.controller.BaseController
    #自定义基础的Entity类，公共字段 生成的entity跳过这些字段
    super-entity-columns: id,created_by,created_date,last_modified_by,last_modified_date,deleted
    #要生成的表名，多个表以逗号分隔
    table-name: sys_role
    #,biz_schedule_daily,biz_medal_daily
```

##3、启动类添加注解 @SpringBootApplication(scanBasePackages = {"com.one.tools.generator"})

##4、测试类使用

GeneratorUtil.getInstance().genCode()

##5、生成完即可注释依赖，后续需要再添加回来


##6、根据每个项目不同 自定义自己想要生成模板 
controller -> resources/templates/controller.java.btl
entity  -> resources/templates/entity.java.btl
mapper -> resources/templates/mapper.java.btl
service -> resources/templates/service.java.btl
