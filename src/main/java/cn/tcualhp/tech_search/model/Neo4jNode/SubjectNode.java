package cn.tcualhp.tech_search.model.Neo4jNode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;

/**
 * @author lhp
 * @description 学科领域（专题）论文
 * @date 2021/2/23 17:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Node(labels = "Subject")
public class SubjectNode {

    /*
    学科领域名
     */
    @Id @Property(value = "name")
    private String name;

    /*
    相关论文
     */
//    @JsonIgnore
//    @Relationship(type = "paper_involve_subject", direction = Relationship.Direction.INCOMING)
//    private Set<PaperNode> paperNodes;

}
