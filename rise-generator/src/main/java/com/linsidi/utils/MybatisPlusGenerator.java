package com.linsidi.utils;


import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.BeetlTemplateEngine;
import com.linsidi.properties.GeneratorProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *@description 描述 mybatisplus代码生成器
 *@author 作者 adi
 *@createTime 创建时间  2021-01-06 11:55
 *@other 修改人和其它信息
 */
@Component
public class MybatisPlusGenerator {


    @Resource
    private GeneratorProperties generatorProperties;


    public void generate() {

     String dataSourceUrl = generatorProperties.getDatasource().getUrl();
     String dataSourceDriver = generatorProperties.getDatasource().getDriverClassName();
     String dataSourceUsername = generatorProperties.getDatasource().getUsername();
     String dataSourcePwd = generatorProperties.getDatasource().getPassword();
     String author = generatorProperties.getGlobalConfig().getAuthor();
     String module = generatorProperties.getGlobalConfig().getModule();
     String bizModuleName = generatorProperties.getPackageConfig().getBizModule();
     String parentPath = generatorProperties.getPackageConfig().getParentPath();
     String tableName = generatorProperties.getStrategyConfig().getTableName();
     String superEntityClass = generatorProperties.getStrategyConfig().getSuperEntityClass();
     String superControllerClass = generatorProperties.getStrategyConfig().getSuperControllerClass();
     String superEntityColumns = generatorProperties.getStrategyConfig().getSuperEntityColumns();
     String tablePrefix = generatorProperties.getStrategyConfig().getTablePrefix();
     String fieldPrefix = generatorProperties.getStrategyConfig().getFieldPrefix();
     Boolean controllerFlag = (generatorProperties.getOutputConfig().getController() != null) ? Boolean.valueOf(generatorProperties.getOutputConfig().getController()) : false;
     Boolean serviceFlag = (generatorProperties.getOutputConfig().getService() != null) ? Boolean.valueOf(generatorProperties.getOutputConfig().getService()) : false;


        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();



        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        //生成到那个业务模块
        String outputDir = projectPath.replaceAll("", module);
        //根据需求开关   数据库json属性转换自定义sql需要引用
        gc.setBaseResultMap(false);
        gc.setOutputDir(outputDir + "/src/main/java");
        gc.setAuthor(author);
        gc.setOpen(false);
        gc.setSwagger2(true); //实体属性 Swagger2 注解
        gc.setFileOverride(true);
        gc.setEnableCache(false); //开启二级缓存
//        gc.setDateType(DateType.TIME_PACK);
        gc.setServiceImplName("%sService");
//        gc.setControllerName("");
//        gc.setEntityName("");
//        gc.setXmlName("");
//        gc.setMapperName("");
        mpg.setGlobalConfig(gc);



        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(dataSourceUrl);
        // dsc.setSchemaName("public");
        dsc.setDriverName(dataSourceDriver);
        dsc.setUsername(dataSourceUsername);
        dsc.setPassword(dataSourcePwd);
        mpg.setDataSource(dsc);



        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("");
        pc.setParent(parentPath);
        pc.setEntity("domain.entity.".concat(bizModuleName));
        pc.setMapper("mapper.".concat(bizModuleName));
        pc.setController("controller.".concat(bizModuleName));
        pc.setService("");
        pc.setServiceImpl("service.".concat(bizModuleName));
        mpg.setPackageInfo(pc);

        // 注入自定义参数
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                //TODO  自定义模板变量 例: ${cfg.abc}
                Map<String, Object> map = new HashMap<>();
                map.put("baseControllerPackage", superControllerClass);
                map.put("baseController",superControllerClass.substring(superControllerClass.lastIndexOf(".") + 1));
                this.setMap(map);
            }
        };

        // 如果模板引擎是 freemarker
        // String templatePath = "/templates/mapper.xml.ftl";
        //模板引擎beetl
        String templatePath = "/templates/mapper.xml.btl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                //                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName() + "/"
                // 修改成要生成的业务模块名 如: base,sys,shedule

                return outputDir + "/src/main/resources/mapper/" + bizModuleName + "/"
                        + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);



        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        templateConfig.setMapper("templates/mapper.java");
        templateConfig.setEntity("templates/entity.java");
        templateConfig.setService("");
        if (serviceFlag){
            templateConfig.setServiceImpl("templates/service.java");
        }else {
            templateConfig.setServiceImpl("");
        }
        if (controllerFlag){
            templateConfig.setController("templates/controller.java");
        }else {
            templateConfig.setController("");
        }
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);



        // 数据库表策略配置
        StrategyConfig strategy = new StrategyConfig();

        //去除表前缀
        if (tablePrefix!=null && !"".equals(tablePrefix)){
            strategy.setTablePrefix(tablePrefix.split(","));
        }
        //去除字段前缀
        if (fieldPrefix!=null && !"".equals(fieldPrefix)){
            strategy.setFieldPrefix(fieldPrefix.split(","));
        }
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass(superEntityClass);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类 你自己的父类控制器,没有就不用设置! 只有controller标识true时，配置生效 自定模板 不生效
//        if (controllerFlag){
//            strategy.setSuperControllerClass(superControllerClass);
//        }
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns(superEntityColumns.split(","));
//        strategy.setSuperEntityColumns("id", "create_by", "create_date", "last_modified_by", "last_modified_date", "deleted");
        strategy.setInclude(tableName.split(","));
        strategy.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new BeetlTemplateEngine());
        mpg.execute();
    }
}
