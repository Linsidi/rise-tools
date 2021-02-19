package com.linsidi;

import com.linsidi.utils.GeneratorContext;
import com.linsidi.utils.MybatisPlusGenerator;

/**
 * @author linsidi
 * @date 2021-01-06 17:29
 * @description
 **/
public class GeneratorUtil {

    private static GeneratorUtil instance = new GeneratorUtil();

    public static GeneratorUtil getInstance() {
        return instance;
    }


    public  void genCode(){
        MybatisPlusGenerator generator = GeneratorContext.getGenerator();
        generator.generate();
    }

}
