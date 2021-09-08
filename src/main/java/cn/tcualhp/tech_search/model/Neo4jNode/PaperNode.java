package cn.tcualhp.tech_search.model.Neo4jNode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author lihepeng
 * @description 论文实体类
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Node(labels = "Paper")
public class PaperNode {
    /**
     * 论文id
     */
    @Id
    @Property(name = "paper_id")
    private String paperId;

    /**
     * 论文名
     */
    @Property(name = "title")
    private String title;


//    @Property(name = "keywords")
//    private String keywords;

    /**
     * 注意，此处的 area_code 是 int 类型。此处曾经引发一次惊心动魄的 trouble shot
     */
//    @Property(name = "area_code")
//    private int areaCode;

    /**
     * 论文在知网中的地址 url
     */
    @Property(name = "url")
    private String url;

    /**
     * 论文发表年份
     */
    @Property(name = "year")
    private String year;

    /**
     * 论文概要
     */
    @Property(name = "summary")
    private String summary;

    /**
     * DOI
     */
    @Property(name = "DOI")
    private String DOI;

    /**
     * 分类号
     */
    @Property(name = "cate_code")
    private String cateCode;

    /**
     * 知网中的数据库字段，来区分论文来源，生产环境不用渲染
     */
    @Property(name = "db")
    private String db;

    /**
     *  单位，正常来说应该用关系形式来表示，但现在没有人的信息，单位提取不出来，暂时用论文页的单位充当
     *  todo 将单位从属性改成关系形式：①从人的详情中抽取单位信息②建库做关系连接③修改本段代码
     */
    @Property(name = "unit")
    private String unit;

    /**
     * 专辑
     */
    @Property(name = "special")
    private String special;

    /**
     * 论文类型，这里主要区分期刊和博硕
     */
    @Property(name = "type")
    private String type;


    /**
     * @description 发表入方向，即作者
     **/
    @Relationship(type = "write", direction = Direction.INCOMING)
    private Set<ExpertNode> expertNodes;

    /**
     * 发表出方向，所属论文
     */
    @Relationship(type = "paper_belong_to_journal", direction = Direction.OUTGOING)
    private JournalNode journalNode;

    /**
    关键词
     */
    @Relationship(type = "paper_involve_keyword", direction = Direction.OUTGOING)
    private Set<KeywordNode> keywordNodes;

    /**
    学科领域（专题）
     */
    @Relationship(type = "paper_involve_subject", direction = Direction.OUTGOING)
    private Set<SubjectNode> subjectNodes;

    @Relationship(type = "paper_belong_to_unit", direction = Direction.OUTGOING)
    private Set<UnitNode> unitNodes;

    public void addExpertNodes(ExpertNode expertNode) {
        // 如果专家节点为空
        if (expertNodes == null) {
            expertNodes = new HashSet<>();
        }
        expertNodes.add(expertNode);
    }

    public Set<ExpertNode> getExpertNodes() {
        return expertNodes;
    }

    @Override
    public String toString() {
        return "PaperNode{" +
                "paperId='" + paperId + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", year='" + year + '\'' +
                ", summary='" + summary + '\'' +
                ", DOI='" + DOI + '\'' +
                ", cateCode='" + cateCode + '\'' +
                ", db='" + db + '\'' +
                ", unit='" + unit + '\'' +
                ", special='" + special + '\'' +
                ", type='" + type + '\'' +
                ", expertNodes=" + expertNodes +
                ", journalNode=" + journalNode +
                ", keywordNodes=" + keywordNodes +
                ", subjectNodes=" + subjectNodes +
                ", unitNodes=" + unitNodes +
                '}';
    }

    /*
     * @Description equals 函数，只需要判断paper_id是否一样就行
     * @Author lhp
     * @Date 2021/2/23 17:42
     * @param o
     * @return boolean
     **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaperNode paperNode = (PaperNode) o;
        return Objects.equals(paperId, paperNode.paperId);
    }

    /*
     * @Description hashcode，只需要哈希paper_id
     * @Author lhp
     * @Date 2021/2/23 17:43
     * @param
     * @return int
     **/
    @Override
    public int hashCode() {
        return Objects.hash(paperId);
    }
}
