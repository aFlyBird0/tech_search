package cn.tcualhp.tech_search.common;

import cn.tcualhp.tech_search.model.Neo4jNode.ExpertNode;
import cn.tcualhp.tech_search.model.Neo4jNode.JournalNode;
import cn.tcualhp.tech_search.model.Neo4jNode.PaperNode;
import cn.tcualhp.tech_search.model.Neo4jNode.UnitNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

/**
 * @author lhp
 * @description 智能问答返回体封装
 * @date 2021/5/13 13:29
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class KbqaResData {
    /*
    类型，例如：论文、人才、字符串，用于前端渲染指示
     */
    private String type;
    private Object data;

    public KbqaResData noAnswer(){
        this.type = "String";
        this.data = "未找到符合内容";
        return this;
    }

    public KbqaResData StringAnswer(Object data){
        this.type = "String";
        this.data = data;
        return this;
    }

    public KbqaResData PaperAnswer(Page<PaperNode> data){
        this.type = "Paper";
        this.data = data;
        return this;
    }

    public KbqaResData ExpertAnswer(Page<ExpertNode> data){
        this.type = "Expert";
        this.data = data;
        return this;
    }

    public KbqaResData UnitAnswer(Page<UnitNode> data){
        this.type = "Unit";
        this.data = data;
        return this;
    }

    public KbqaResData JournalAnswer(Page<JournalNode> data){
        this.type = "Journal";
        this.data = data;
        return this;
    }
}
