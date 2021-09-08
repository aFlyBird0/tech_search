package cn.tcualhp.tech_search.controller;

import cn.tcualhp.tech_search.common.Response;
import cn.tcualhp.tech_search.model.Neo4jNode.PaperNode;
import cn.tcualhp.tech_search.neo4jRepo.PaperNodeRepo;
import cn.tcualhp.tech_search.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author lhp
 * @description TODO 通过各种方搜论文
 * @date 2021/2/24 11:02
 */
@CrossOrigin
@RestController
@RequestMapping("/paper")
public class PaperController {

    @Autowired
    private PaperNodeRepo paperNodeRepo;


    @PostMapping("/year")
    public Response findPaperByYear(@RequestBody HashMap<String, String> map){
        Pageable pageable = PageUtil.getPageableFromMap(map);
        String year = map.getOrDefault("year", "");
        if (year.length() < 1){
            return new Response().failure("year参数缺失或错误");
        }
        Page<PaperNode> paperNodes = paperNodeRepo.findAllByYear(year, pageable);
        return new Response().success(paperNodes);
    }

    @PostMapping("/keyword")
    public Response findPaperByKeyword(@RequestBody HashMap<String, String> map){
        Pageable pageable = PageUtil.getPageableFromMap(map);
        String keyword = map.getOrDefault("keyword", "");
        if (keyword.length() < 1){
            return new Response().failure("keyword参数缺失或错误");
        }
        Page<PaperNode> paperNodes = paperNodeRepo.findAllByKeyword(keyword, pageable);
        return new Response().success(paperNodes);
    }

    @PostMapping("/id")
    public Response findPaperById(@RequestBody HashMap<String, String> map){
//        Pageable pageable = PageUtil.getPageableFromMap(map);
        String id = map.getOrDefault("id", "");
        if (id.length() < 1){
            return new Response().failure("id参数缺失或错误");
        }
        PaperNode paperNode = paperNodeRepo.getPaperNodeByPaperId(id);
        return new Response().success(paperNode);
    }

    @PostMapping("/journal")
    public Response findPaperByJournal(@RequestBody HashMap<String, String> map){
        Pageable pageable = PageUtil.getPageableFromMap(map);
        String journal = map.getOrDefault("journal", "");
        if (journal.length() < 1){
            return new Response().failure("keyword参数缺失或错误");
        }
        Page<PaperNode> paperNodes = paperNodeRepo.findAllByJournalNodeName(journal, pageable);
        return new Response().success(paperNodes);
    }

    @PostMapping("/expertId")
    public Response findPaperByExpertId(@RequestBody HashMap<String, String> map){
        Pageable pageable = PageUtil.getPageableFromMap(map);
        String expertId = map.getOrDefault("expertId", "");
        if (expertId.length() < 1){
            return new Response().failure("expertId参数缺失或错误");
        }
        Page<PaperNode> paperNodes = paperNodeRepo.getPaperNodesByExpertID(expertId, pageable);
        return new Response().success(paperNodes);
    }

    /**
     * @Description 混合搜索，目前是根据关键词、单位、人名
     * @Author lhp
     * @Date 2021/5/17 12:03
     * @param map
     * @return cn.tcualhp.tech_search.common.Response
     **/
    @PostMapping("/mix")
    public Response mixFindPaper(@RequestBody HashMap<String, String> map){
        Pageable pageable = PageUtil.getPageableFromMap(map);
        String keyword = map.getOrDefault("keyword", "");  //这个是搜索关键词，不和论文关键词
        if (keyword.length() < 1){
            return new Response().failure("keyword参数缺失或错误");
        }
        Page<PaperNode> paperNodesByKeyword = paperNodeRepo.getPaperNodeByKeywordContains(keyword, pageable);
        if(paperNodesByKeyword.getTotalElements()>0){
            return new Response().success(paperNodesByKeyword);
        }
        Page<PaperNode> paperNodesByUnit = paperNodeRepo.getPaperNodeByUnitHavePaper(keyword, pageable);
        if (paperNodesByUnit.getTotalElements()>0){
            return new Response().success(paperNodesByUnit);
        }
        Page<PaperNode> paperNodesByExpertName = paperNodeRepo.getPaperNodesByExpertName(keyword, pageable);
        // 最后一次返回有两种情况，要么是通过人名找到的论文列表（不空），要么是空
        // 无论空还是不开，都交由前端判断
        return new Response().success(paperNodesByExpertName);

    }
}
