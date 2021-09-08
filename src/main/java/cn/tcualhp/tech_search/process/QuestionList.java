package cn.tcualhp.tech_search.process;

import cn.tcualhp.tech_search.utils.LoadJsonUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @author lhp
 * @description 定义的问题模板
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionList {
    private String fileName;
    private String comments;
    private String questionDescription;
    private int questionType;
    private Filter filter;
    private List<String> questions;

    public QuestionList(String fileName) {
        this.fileName = fileName;

        if (this.fileName == null) {
            return;
        }
//        JSONObject jsonObject = null;
//        try {
//            jsonObject = LoadJsonUtil.getJsonObject(this.fileName);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return;
//        }

        String jsonString = null;
        try {
            jsonString = LoadJsonUtil.getJsonString(this.fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        QuestionList questionList = JSONObject.parseObject(jsonString, QuestionList.class);
        this.fileName = questionList.fileName;
        this.comments = questionList.comments;
        this.questionDescription = questionList.questionDescription;
        this.questionType = questionList.questionType;
        this.filter = questionList.filter;
        this.questions = questionList.questions;
    }
}

