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

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author lhp
 * @description 关键词节点
 * @date 2021/2/23 17:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Node(labels = "Keyword")
public class KeywordNode {
    /**
     * 关键词名
     */
    @Id @Property(value = "name")
    private String name;

    /**
     * 关键词相关的论文
     */
//    @JsonIgnore
//    @JsonBackReference
//    @Relationship(type = "paper_involve_keyword", direction = Relationship.Direction.INCOMING)
//    private Set<PaperNode> paperNodes;

    @Override
    public String toString() {
        return "KeywordNode{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeywordNode that = (KeywordNode) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
