package cn.tcualhp.tech_search.neo4jRepo;

import cn.tcualhp.tech_search.model.Neo4jNode.JournalNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

/**
 * @author lhp
 * @description TODO 返回期刊的查询
 * @date 2021/2/24 13:16
 */
@EnableNeo4jRepositories
public interface JournalNodeRepo extends Neo4jRepository<JournalNode, String> {

    @Override
    Page<JournalNode> findAll(Pageable pageable);
}
