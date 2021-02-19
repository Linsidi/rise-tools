package com.linsidi;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperRunManager;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.Locale;
import java.util.Map;

/**
 * @author linsidi
 * pdf工具包
 */
public class PdfGenUtils {

    public PdfGenUtils() {
    }

    /**
     * 拉模式 直接拉取报表模板，将sql写在模板内，方便随时修改（适用于场馆成绩）
     * @param sourFile 输入文件流
     * @param sosRef 输出文件流
     * @param parameters 参数（对应.jrxml文件的 Parameters属性）
     * @param connection 数据库连接池
     * */
    public static void gen(InputStream sourFile, Map<String, Object> parameters, OutputStream sosRef, Connection connection) throws Exception {
        InputStream inputStream = null;
        parameters.put("REPORT_LOCALE", Locale.ENGLISH);

        try {
            ByteOutputStream outputStream = new ByteOutputStream();
            JasperCompileManager.compileReportToStream(sourFile, outputStream);
            inputStream = new ByteArrayInputStream(outputStream.getBytes());
            JasperRunManager.runReportToPdfStream(inputStream, sosRef, parameters, connection);
        } catch (Exception var9) {
            throw new JRException("report gen error", var9);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    /**
     * 推模式 将数据查询组装，以bean的形式与报表交互（适用于数据复杂，报表模板无需经常改动的系统）
     * @param parameters 参数（对应.jrxml文件的 Parameters属性）
     * @param sosRef 输出文件流
     * @param sourFile 输入文件流
     * @param dataSource 组装的数据参数（对应.jrxml文件的 Fields属性）
     * */
    public static void genForBean(InputStream sourFile, Map<String, Object> parameters, OutputStream sosRef, JRDataSource dataSource) throws Exception {
        InputStream inputStream = null;
        parameters.put("REPORT_LOCALE", Locale.ENGLISH);

        try {
            ByteOutputStream outputStream = new ByteOutputStream();
            JasperCompileManager.compileReportToStream(sourFile, outputStream);
            inputStream = new ByteArrayInputStream(outputStream.getBytes());
            JasperRunManager.runReportToPdfStream(inputStream, sosRef, parameters,dataSource);
        } catch (Exception var9) {
            throw new JRException("report gen error", var9);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    /**
     * 拉模式 直接拉取报表模板，将sql写在模板内，方便随时修改（适用于场馆成绩）
     * @param sourFile 输入文件流
     * @param fileName 浏览器预览文件名称
     * @param parameters 参数（对应.jrxml文件的 Parameters属性）
     * @param connection 数据库连接池
     * */
    public static ResponseEntity genPreview(InputStream sourFile, Map<String, Object> parameters, Connection connection, String fileName) throws Exception{
        ByteArrayOutputStream sosRef = new ByteArrayOutputStream();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_PDF);
        httpHeaders.set("Content-Disposition","inline;filename=" + fileName);
        try {
            gen(sourFile,parameters,sosRef,connection);
            return new ResponseEntity(sosRef.toByteArray(),
                    httpHeaders,
                    HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("report gen error",e);
        }
    }

    /**
     * 推模式 将数据查询组装，以bean的形式与报表交互（适用于数据复杂，报表模板无需经常改动的系统）
     * @param parameters 参数（对应.jrxml文件的 Parameters属性）
     * @param fileName 浏览器预览文件名称
     * @param sourFile 输入文件流
     * @param dataSource 组装的数据参数（对应.jrxml文件的 Fields属性）
     * */
    public static ResponseEntity genForBeanPreview(InputStream sourFile, Map<String, Object> parameters, JRDataSource dataSource, String fileName) throws Exception {
        ByteArrayOutputStream sosRef = new ByteArrayOutputStream();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_PDF);
        httpHeaders.set("Content-Disposition","inline;filename=" + fileName);
        try {
            genForBean(sourFile,parameters,sosRef,dataSource);
            return new ResponseEntity(sosRef.toByteArray(),
                    httpHeaders,
                    HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("report gen error",e);
        }
    }




}
