package cn.tcualhp.tech_search.neo4jRepo;

import cn.tcualhp.tech_search.model.Neo4jNode.ExpertNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.repository.query.Query;
import cn.tcualhp.tech_search.model.Neo4jNode.PaperNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author lihepeng
 * @description 论文查询neo4j的Repo TODO 分页
 **/
@EnableNeo4jRepositories
public interface PaperNodeRepo extends Neo4jRepository<PaperNode, String> {
    /**
     * 根据论文 name 论文名 获取论文信息
     *
     * @param title 论文名
     * @return 返回论文节点 PaperNode 的 list
     */
    Page<PaperNode> getPaperNodeByTitleContains(@Param("Title") String title, Pageable pageable);

   /**
     * 根据论文 paperId 来获取论文信息
     *
     * @param paperId 论文 paperId
     * @return 返回论文节点 PaperNode 的 list
     */
   PaperNode getPaperNodeByPaperId(@Param("paperId") String paperId);

    /**
     * 根据论文关键词获取论文信息(模糊)
     *
     * @param keyword 论文关键词
     * @return 返回论文节点 PaperNode 的 list
     */
//    @Query("MATCH (p:Paper) - [r:paper_involve_keyword] - (k:Keyword) WHERE k.name =~('.*'+$keyword+'.*') match (p)-[r2]-(b) return p, collect(r2), collect(b)")
//    List<PaperNode> getPaperNodeByKeywordContains(@Param("keyword") String keyword);

   /**
    * 根据论文关键词获取论文信息(模糊)(分页)
    *
    * @param keyword 论文关键词
    * @return 返回论文节点 PaperNode 的 list
    */
    @Query(value = "MATCH (p:Paper) - [r:paper_involve_keyword] - (k:Keyword) WHERE k.name =~('.*'+$keyword+'.*') match (p)-[r2]-(b) return p, collect(r2), collect(b) SKIP $skip LIMIT $limit",
           countQuery = "MATCH (p:Paper) - [r:paper_involve_keyword] - (k:Keyword) WHERE k.name =~('.*'+$keyword+'.*') RETURN COUNT(p)")
    Page<PaperNode> getPaperNodeByKeywordContains(@Param("keyword") String keyword, Pageable pageable);

    /**
     * @Description 根据论文关键词获取论文信息（精准）
     * @Author lhp
     * @Date 2021/2/23 21:41
     * @param keyword
     * @return java.util.List<cn.tcualhp.tech_search.model.Neo4jNode.PaperNode>
     **/
    @Query( value = "MATCH (p:Paper) - [r:paper_involve_keyword] - (k:Keyword) WHERE k.name = $keyword match (p)-[r2]-(b) return p, collect(r2), collect(b) SKIP $skip LIMIT $limit",
    countQuery = "MATCH (p:Paper) - [r:paper_involve_keyword] - (k:Keyword) WHERE k.name = $keyword RETURN COUNT(p)")
    Page<PaperNode> findAllByKeyword(@Param("keyword") String keyword, Pageable pageable);

    /*
     * @Description 根据期刊名字找论文
     * @Author lhp
     * @Date 2021/2/23 22:30
     * @param journalName
     * @return java.util.List<cn.tcualhp.tech_search.model.Neo4jNode.PaperNode>
     **/
    Page<PaperNode> findAllByJournalNodeName(String journalName, Pageable pageable);


    /**
     * 根据论文发表 year 年份获取论文信息
     *
     * @param year 论文发表年
     * @return 返回论文节点 PaperNode 的 list
     */
    Page<PaperNode> findAllByYear(@Param("year") String year, Pageable pageable);

    /**
     * 通过论文发表的  summary 摘要，来获取论文信息
     *
     * @param summary
     * @return 返回论文节点 PaperNode 的 list
     */
    Page<PaperNode> getPaperNodeBySummaryContains(@Param("summary") String summary, Pageable pageable);

    /**
     * 通过论文发表的  summary 摘要，来获取论文信息
     *
     * @param summary
     * @return 返回论文节点 PaperNode 的 list
     */
    Page<PaperNode> getPaperNodeNLPBySummaryContains(@Param("summary") String summary, Pageable pageable);

    /**
     * 通过专家姓名查询专家发表的论文信息
     *
     * @param expertName 专家姓名
     * @return paperNode 的 list
     */
    @Query(value = "match (n:Author)-[r:write]->(p:Paper) where n.name=$expertName match (p)-[r2]-(b) return p, collect(r2), collect(b) SKIP $skip LIMIT $limit",
    countQuery = "match (n:Author)-[r:write]->(p:Paper) where n.name=$expertName return count(p)")
    Page<PaperNode> getPaperNodesByExpertName(@Param("expertName") String expertName, Pageable pageable);


    /**
     * 通过专家id查询专家发表的论文信息
     *
     * @param expertId 专家id
     * @return
     */
    @Query(value = "match (n:Author)-[r:write]->(p:Paper) where n.author_id=$expertId match (p)-[r2]-(b) return p, collect(r2), collect(b) SKIP $skip LIMIT $limit",
    countQuery = "match (n:Author)-[r:write]->(p:Paper) where n.author_id=$expertId return count(p)")
    Page<PaperNode> getPaperNodesByExpertID(@Param("expertId") String expertId, Pageable pageable);


    /**
     * 通过 专家 1 姓名 和 专家 2 姓名，查询论文
     *
     * @param expertName1 专家姓名 1
     * @param expertName2 专家姓名 2
     * @return
     */
    @Query(value = "match(a1:Author)-[r:write]->(p:Paper)<-[r:write]-(a2:Author) where a1.name=$expertName1 and a2.name=$expertName2 match (p)-[r2]-(b) return p, collect(r2), collect(b) SKIP $skip LIMIT $limit",
    countQuery = "match(a1:Author)-[r:write]->(p:Paper)<-[r:write]-(a2:Author) where a1.name=$expertName1 and a2.name=$expertName2 match (p)-[r2]-(b) return count(p)")
    Page<PaperNode> getPaperNodeByCooperation(@Param("expertName1") String expertName1, @Param("expertName2") String expertName2, Pageable pageable);

    /**
     * 通过单位名称获取某单位拥有哪些论文/专利
     * 支持模糊查询。参数部分调用时需要修饰。
     *
     * @param unitName 单位名称
     * @return
     */
    @Query(value = "match(n:Unit)-[r:paper_belong_to_unit]-(p:Paper) where n.name=~('.*'+$unitName+'.*') match (p)-[r2]-(b) return p, collect(r2), collect(b) SKIP $skip LIMIT $limit",
    countQuery = "match(n:Unit)-[r:paper_belong_to_unit]-(p:Paper) where n.name=~('.*'+$unitName+'.*') match (p)-[r2]-(b) return count(p)")
    Page<PaperNode> getPaperNodeByUnitHavePaper(@Param("unitName") String unitName, Pageable pageable);


    /**
     * 通过专家姓名和论文关键词 keywords 查询论文
     * 对应模板 某专家nr 关于 关键字kw 的论文有哪些 ？
     * 支持关键字模糊查询，需要使用时对关键字进修修饰
     *
     * @param expertName 专家名 ，支持模糊查询。使用时，需要进行修饰，将 expertName 变为 .*expertName.* 即可简单模糊查询
     * @param keyword    关键字 ，支持模糊查询。使用时，需要进行修饰，将 keywords 变为 .*keywords.* 即可简单模糊查询
     *                   使用 实例见 PaperController.java 中的 public Response getPapersByExpertNameAndKeywords() 的查询
     * @return paperNode 的 list
     */
    @Query(value = "match (e:Author)-[r:write]-(p:Paper)-[paper_involve_keyword] - (k:Keyword) where k.name=~('.*'+$keyword+'.*') and e.name=$expertName match (p)-[r2]-(b) return p, collect(r2), collect(b) SKIP $skip LIMIT $limit",
    countQuery = "match (e:Author)-[r:write]-(p:Paper)-[paper_involve_keyword] - (k:Keyword) where k.name=~('.*'+$keyword+'.*') and e.name=$expertName match (p)-[r2]-(b) return count(p)")
    Page<PaperNode> getPaperNodeByExpertNameAndKeywords(@Param("keyword") String keyword, @Param("expertName") String expertName, Pageable pageable);

    /**
     * 通过论文的关键词和发表年份查询论文
     *
     * @param keyword 关键词，支持模糊查询。使用时需要进行修饰。
     * @param year     论文发表年份
     * @return
     */
    @Query(value = "match (p:Paper) - [paper_involve_keyword] -(k:Keyword)  where k.name=~('.*'+$keyword+'.*') and p.year=$year match (p)-[r2]-(b) return p, collect(r2), collect(b) SKIP $skip LIMIT $limit",
    countQuery = "match (p:Paper) - [paper_involve_keyword] -(k:Keyword)  where k.name=~('.*'+$keyword+'.*') and p.year=$year return count(p)")
    Page<PaperNode> getPaperNodeByYearAndKeywords(@Param("year") String year, @Param("keyword") String keyword, Pageable pageable);

//    @Query(value = "match (p:Paper) - [paper_involve_keyword] -(k:Keyword)  where k.name=$keyword and p.year=$year return p SKIP $skip LIMIT $limit",
//            countQuery = "match (p:Paper) - [paper_involve_keyword] -(k:Keyword)  where k.name=$keyword and p.year=$year return count(p)")
//    Page<PaperNode> getPaperNodeByYearAndKeywords(@Param("year") String year, @Param("keyword") String keyword, Pageable pageable);





}
