package cn.tcualhp.tech_search.model.Neo4jNode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.Objects;

/**
 * @author lhp
 * @description 单位节点 TODO 单位节点图数据库还没建立
 * @date 2021/2/23 23:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Node(labels = "Unit")
public class UnitNode {
    /**
     * 单位名称
     */
    @Id @Property(value = "name")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnitNode unitNode = (UnitNode) o;
        return Objects.equals(name, unitNode.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
