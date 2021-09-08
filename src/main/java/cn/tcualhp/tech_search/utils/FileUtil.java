package cn.tcualhp.tech_search.utils;

import cn.tcualhp.tech_search.process.QuestionList;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author lhp
 * @description TODO
 * @date 2021/3/12 13:41
 */
public class FileUtil {
    /**
     * 读取resources下的文件，所有文件读取都调用这个方法
     * @param filePath
     * @return
     * @throws IOException
     */
    public static InputStream getResourceFile(String filePath) throws IOException {
        InputStream input = new ClassPathResource(filePath).getInputStream();
        return input;
    }

    public static JSONObject getJsonObject(String filePath) throws IOException{
        InputStream inputStream = FileUtil.getResourceFile(filePath);
        String text = IOUtils.toString(inputStream, "UTF-8");
        return JSON.parseObject(text);
    }
    public static QuestionList getQuestionLists(String filePath) throws IOException{
        InputStream inputStream = FileUtil.getResourceFile(filePath);
        String text = IOUtils.toString(inputStream, "UTF-8");
        QuestionList questionList = JSON.parseObject(text, QuestionList.class);
        return questionList;
    }
}
