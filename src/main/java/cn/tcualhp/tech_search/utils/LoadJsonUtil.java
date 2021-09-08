package cn.tcualhp.tech_search.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author jerry
 * @program tech_kg
 * @package_name cn.tcualhp.tech_kg.utils
 * @description 加载JSON文件的工具类
 * @create 2019/11/27 14:48
 **/

public class LoadJsonUtil {
    public static JSONObject getJsonObject(String filename) throws IOException {
        File file = new File("src/main/resources/" + filename);
        InputStream inputStream = new FileInputStream(file);
        String text = IOUtils.toString(inputStream, "UTF-8");
        return JSON.parseObject(text);
    }

    public static String getJsonString(String filename) throws IOException {
        File file = new File("src/main/resources/" + filename);
        InputStream inputStream = new FileInputStream(file);
        String text = IOUtils.toString(inputStream, "UTF-8");
        return text;
    }
}
