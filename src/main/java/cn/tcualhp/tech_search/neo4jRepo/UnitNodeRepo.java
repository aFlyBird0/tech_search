package cn.tcualhp.tech_search.neo4jRepo;

import cn.tcualhp.tech_search.model.Neo4jNode.PaperNode;
import cn.tcualhp.tech_search.model.Neo4jNode.UnitNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

@EnableNeo4jRepositories
public interface UnitNodeRepo extends Neo4jRepository<UnitNode, String> {
    /**
     * 通过专家姓名查询专家单位
     *
     * @param expertName 专家姓名
     * @return unitNode 的 list
     */
    @Query(value = "match (n:Author)-[r:author_belong_to_unit]-(u:Unit) where n.name=$expertName match (u)-[r2]-(b) return u, collect(r2), collect(b)  SKIP $skip LIMIT $limit",
            countQuery = "match (n:Author)-[r:author_belong_to_unit]-(u:Unit) where n.name=$expertName return count(u)")
    Page<UnitNode> getUnitNodesByExpertName(@Param("expertName") String expertName, Pageable pageable);
}
