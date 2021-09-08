package cn.tcualhp.tech_search.model.Neo4jNode;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

/**
 * @author lhp
 * @description TODO 成果节点
 * @date 2021/3/4 15:42
 */
public class AchievementNode {
    /**
     * Id
     */
    @Id
    @Property(name = "achievement_id")
    private String achivementId;


    /**
     *
     */
    @Property(name = "book_code")
    private String bookCode;

    /**
     *
     */
    @Property(name = "category")
    private String category;

    /**
     *
     */
    @Property(name = "evaluate")
    private String evaluate;

    /**
     *
     */
    @Property(name = "in_time")
    private String inTime;

    /**
     *
     */
    @Property(name = "level")
    private String level;

    /**
     * 单位,todo 修改成关系方式
     */
    @Property(name = "organ")
    private String unit;

    /**
     *
     */
    @Property(name = "pass_time")
    private String passTime;

    /**
     *
     */
    @Property(name = "subject_code")
    private String subjectCode;

    /**
     *
     */
    @Property(name = "summary")
    private String summary;

    /**
     *
     */
    @Property(name = "title")
    private String title;

    /**
     *
     */
    @Property(name = "type")
    private String type;

    /**
     *
     */
    @Property(name = "uid")
    private String uid;

    /**
     *
     */
    @Property(name = "url")
    private String url;

    /**
     *
     */
    @Property(name = "year")
    private String year;

    /**
     * 作者
     */
    @Relationship(type = "write", direction = Relationship.Direction.INCOMING)
    private List<ExpertNode> expertNodes;

    /**
     * 关键词
     */
    @Relationship(type = "achivement_involve_keyword", direction = Relationship.Direction.OUTGOING)
    private List<KeywordNode> keywordNodes;
}
