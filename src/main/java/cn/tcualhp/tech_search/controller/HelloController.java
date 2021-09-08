package cn.tcualhp.tech_search.controller;

import cn.tcualhp.tech_search.HanlpTest.SegTest;
import cn.tcualhp.tech_search.common.Response;
import cn.tcualhp.tech_search.model.Neo4jNode.ExpertNode;
import cn.tcualhp.tech_search.model.Neo4jNode.PaperNode;
import cn.tcualhp.tech_search.neo4jRepo.ExpertNodeRepo;
import cn.tcualhp.tech_search.neo4jRepo.PaperNodeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
public class HelloController {

    @Autowired
    private PaperNodeRepo paperNodeRepo;

    @Autowired
    private ExpertNodeRepo expertNodeRepo;

    @RequestMapping("/hello")
    public Response hello(){
        return new Response().success("遇见");
    }

    @RequestMapping("/test")
    public Response test(@RequestParam("s") String s){
        Pageable pageable = PageRequest.of(1, 20);
        Page<PaperNode> paperNodes = paperNodeRepo.getPaperNodeByKeywordContains(s, pageable);
        System.out.println("here!");

        return new Response().success(paperNodes);
    }

    @RequestMapping("/hanlp")
    public Response testHanlp(){
        SegTest segTest = new SegTest();

//        segTest.testNLPtokenizer();

        String querySentence1 = "杭州电子科技大学挤出成型工艺真强啊";
        String q2 = "给我一篇张三写的论文";
        segTest.testSegment(querySentence1);
        segTest.testSegment(q2);
        return new Response().success();
    }

}
