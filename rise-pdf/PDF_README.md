#1、加入pom依赖pdf-tools
`       <!--pdf工具包-->
        <dependency>
            <groupId>com.onesports.fjds</groupId>
            <artifactId>pdf-tools</artifactId>
            <version>1.0.0</version>
        </dependency>`

#2、推模式
（1）核心方法 PdfGenUtils.genForBean(sourFile, parameters, outputStream, data);
    sourFile 输入文件流
    parameters 参数（对应.jrxml文件的 Parameters属性）
    outputStream 输出文件流
    data 组装的数据参数（对应.jrxml文件的 Fields属性）
（2）实例参考PdfGenController类 的 pushGenPdf 方法
（3）注意点：
    1.JRBeanCollectionDataSource数据源需要用List集合承接。
    2.使用ireport绘制报表模板文件.jrxml，使用遍历数据源的集合数据时，设置Fields时Fields需要使用
    net.sf.jasperreports.engine.data.JRBeanCollectionDataSource。

#3、拉模式
（1）核心方法 PdfGenUtils.gen(sourFile, parameters, outputStream, connection);
    sourFile 输入文件流
    parameters 参数（对应.jrxml文件的 Parameters属性）
    outputStream 输出文件流
    connection 数据库连接池
（2）实例参考PdfGenController类 的 pullGenPdf 方法
    
#4、报表预览模式 推模式
（1）核心方法 PdfGenUtils.genForBeanPreview(sourFile, parameters, data, fileName);
    sourFile 输入文件流
    parameters 参数（对应.jrxml文件的 Parameters属性）
    data 组装的数据参数（对应.jrxml文件的 Fields属性）
    fileName 浏览器输出文件的名称

（2）实例参考PdfGenController类 的 pushGenPreview 方法
（3）注意点：
    1.JRBeanCollectionDataSource数据源需要用List集合承接。
    2.使用ireport绘制报表模板文件.jrxml，使用遍历数据源的集合数据时，设置Fields时Fields需要使用
    net.sf.jasperreports.engine.data.JRBeanCollectionDataSource。

#5、报表预览模式 拉模式
（1）核心方法 PdfGenUtils.genPreview(sourFile, parameters, connection, fileName);
    sourFile 输入文件流
    parameters 参数（对应.jrxml文件的 Parameters属性）
    connection 数据库连接池
    fileName 浏览器输出文件的名称

（2）实例参考PdfGenController类 的 pullGenPreview 方法
 




