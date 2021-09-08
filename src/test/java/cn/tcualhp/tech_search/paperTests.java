package cn.tcualhp.tech_search;

import cn.tcualhp.tech_search.model.Neo4jNode.PaperNode;
import cn.tcualhp.tech_search.neo4jRepo.PaperNodeRepo;
import cn.tcualhp.tech_search.utils.PageUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.HashMap;

/**
 * @author lhp
 * @description TODO
 * @date 2021/3/12 11:09
 */
public class paperTests extends TechSearchApplicationTests{
    @Autowired
    private PaperNodeRepo paperNodeRepo;

    @Test
    public void testFindPaperByTitle(){
        Pageable pageable = PageUtil.getPageableFromMap(new HashMap<>());
        Page<PaperNode> paperNodes= paperNodeRepo.getPaperNodeByTitleContains("3D打印", pageable);
        System.out.println(paperNodes);
    }

    @Test
    public void testGetPaperNodeByKeywordContains(){
        Pageable pageable = PageRequest.of(0, 20, Sort.DEFAULT_DIRECTION, "paperId");
        Page<PaperNode> paperNodes = paperNodeRepo.getPaperNodeByKeywordContains("数学", pageable);
        System.out.println(paperNodes);
    }

    @Test
    public void testGetPaperNodeByKeyword(){
        Pageable pageable = PageUtil.getPageableFromMap(new HashMap<>());
        Page<PaperNode> paperNodes = paperNodeRepo.findAllByKeyword("数学", pageable);
        System.out.println(paperNodes);
    }

    @Test
    public void testGetPaperNodesByJournalNode_Name(){
        Pageable pageable = PageUtil.getPageableFromMap(new HashMap<>());
        Page<PaperNode> paperNodes = paperNodeRepo.findAllByJournalNodeName("同济大学学报(自然科学版)", pageable);
        System.out.println(paperNodes);
    }
}
