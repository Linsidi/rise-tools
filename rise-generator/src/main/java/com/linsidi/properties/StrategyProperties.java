package com.linsidi.properties;

import lombok.Data;

/**
 * @author linsidi
 * @date 2021-01-06 15:13
 * @description
 **/
@Data
public class StrategyProperties {

    /**      */
    private String tableName;
    /**     */
    private String tablePrefix;
    /**     */
    private String fieldPrefix;
    /**     */
    private String superEntityClass;
    /**     */
    private String superControllerClass;
    /**
     * 自定义基础的Entity类，公共字段
     */
    private String superEntityColumns;


}
