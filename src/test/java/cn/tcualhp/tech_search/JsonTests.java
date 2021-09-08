package cn.tcualhp.tech_search;

import cn.tcualhp.tech_search.process.QuestionList;
import cn.tcualhp.tech_search.utils.FileUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lhp
 * @description TODO
 * @date 2021/3/12 14:18
 */
public class JsonTests extends TechSearchApplicationTests{

    @Test
    public void testQuestionListParser() throws JsonProcessingException, IOException {
        QuestionList questionList = FileUtil.getQuestionLists("questions/00questionPublish.json");
        System.out.println(questionList);
    }
}
