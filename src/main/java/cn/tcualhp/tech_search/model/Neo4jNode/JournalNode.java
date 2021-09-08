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

import java.util.Objects;
import java.util.Set;

/**
 * @author jerry
 * @program tech_kg
 * @package_name cn.tcualhp.tech_kg.model.Neo4jNode
 * @description 期刊节点
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Node(labels = "Journal")
public class JournalNode {

    @Id
    @Property(name = "name")
    private String name;

//    @Property(name = "journal_quality")
//    private String journalQuality;

//    @Property(name = "journal_url")
//    private String journalUrl;


    @Override
    public String toString() {
        return "JournalNode{" +
                "name='" + name + '\'' +
                '}';
    }

    /**
     * 期刊下的论文
     */
//    @JsonIgnore
//    @Relationship(type = "paper_belong_to_journal", direction = Direction.INCOMING)
//    private Set<PaperNode> paperNodes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JournalNode that = (JournalNode) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
