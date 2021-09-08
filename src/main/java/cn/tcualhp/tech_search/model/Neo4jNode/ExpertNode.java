package cn.tcualhp.tech_search.model.Neo4jNode;

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
 * @description 专家节点
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Node("Author")
public class ExpertNode {

    @Id
    @Property(name = "author_id")
    private String expertId;

    /**
     * 专家姓名
     */
    @Property(name = "name")
    private String name;

    /**
     * 发表出方向
     */
    /**
     * 防止序列化循环
     */
//    @JsonIgnore
//    @Relationship(type = "write", direction = Direction.OUTGOING)
//    private Set<PaperNode> paperNodes;

    @Relationship(type = "author_belong_to_unit", direction = Direction.OUTGOING)
    private Set<UnitNode> unitNodes;

    @Relationship(type = "author_involve_subject",direction = Direction.OUTGOING)
    private Set<SubjectNode> subjectNodes;

    @Override
    public String toString() {
        return "ExpertNode{" +
                "author_id='" + expertId + '\'' +
                ", name='" + name + '\'' +
//                ", paperNodes=" + paperNodes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpertNode that = (ExpertNode) o;
        return Objects.equals(expertId, that.expertId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expertId);
    }
}
