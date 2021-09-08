package cn.tcualhp.tech_search.neo4jRepo;

import cn.tcualhp.tech_search.model.Neo4jNode.ExpertNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author lhp
 * @description TODO 查询专家的neo4jRepo
 * @date 2021/2/23 23:20
 */
@EnableNeo4jRepositories
public interface ExpertNodeRepo extends Neo4jRepository<ExpertNode, String> {

    /**
     * @Description 根据专家id找人
     * @Author lhp
     * @Date 2021/2/23 23:23
     * @param expertId
     * @return cn.tcualhp.tech_search.model.Neo4jNode.ExpertNode
     **/
    ExpertNode getExpertNodeByExpertId(@Param("expertId") String expertId);

    /**
     * @Description 根据专家名字找人
     * @Author lhp
     * @Date 2021/2/23 23:25
     * @param name
     * @return java.util.List<cn.tcualhp.tech_search.model.Neo4jNode.ExpertNode>
     **/
    Page<ExpertNode> getExpertNodesByName(@Param("name") String name, Pageable pageable);

    /**
     * @Description 根据单位名称找人
     * @Author lhp
     * @Date 2021/2/23 23:27
     * @param unit
     * @return java.util.List<cn.tcualhp.tech_search.model.Neo4jNode.ExpertNode>
     **/
    @Query(value = "Match (a:Author) - [author_belong_to_unit] - (u:Unit) where u.name = $unit match (a)-[r2]-(b) return a, collect(r2), collect(b) SKIP $skip LIMIT $limit",
            countQuery = "Match (a:Author) - [author_belong_to_unit] - (u:Unit) where u.name = $unit return count(a)")
    Page<ExpertNode> getExpertNodesByUnit(@Param("unit") String unit, Pageable pageable);

    /**
     * 根据单位名称获取该单位有哪些专家
     * 支持模糊查询。但是需要用户在调用时，自行修改参数 unitName 的值
     * 例如模糊查询 “ 杭州电子 ” ,需要用户对参数进行修改，修改为 “  .*杭州电子.*  ” ，即可完成模糊查询。
     * 使用样例见  ExpertController.java 中 “ public Response getExpertsByUnitName() ”
     * @param unitName
     * @return expertNode 的 list ， 最多返回 30 个结果
     */
    @Query(value = "match (a:Author)-[r:author_belong_to_unit]->(u:Unit) where u.name=~$unitName match (a)-[r2]-(b) return a, collect(r2), collect(b)  SKIP $skip LIMIT $limit",
            countQuery = "match (a:Author)-[r:author_belong_to_unit]->(u:Unit) where u.name=~$unitName return count(a)")
    Page<ExpertNode> getExpertNodesByUnitNameContains(@Param("unitName") String unitName, Pageable pageable);

    /**
     * @Description 根据学科领域找人
     * @Author lhp
     * @Date 2021/2/23 23:42
     * @param subject
     *
     * @return java.util.List<cn.tcualhp.tech_search.model.Neo4jNode.ExpertNode>
     **/
    @Query(value = "Match (a:Author) - [author_involve_subject] - (s:Subject) where s.name = $subject match (a)-[r2]-(b) return a, collect(r2), collect(b)  SKIP $skip LIMIT $limit",
            countQuery = "Match (a:Author) - [author_involve_subject] - (s:Subject) where s.name = $subject return count(a)")
    List<ExpertNode> getExpertNodesBySubject(@Param("subject") String subject, Pageable pageable);
}
