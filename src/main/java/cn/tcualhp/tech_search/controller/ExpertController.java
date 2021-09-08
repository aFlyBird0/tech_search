package cn.tcualhp.tech_search.controller;

import cn.tcualhp.tech_search.common.Response;
import cn.tcualhp.tech_search.model.Neo4jNode.ExpertNode;
import cn.tcualhp.tech_search.neo4jRepo.ExpertNodeRepo;
import cn.tcualhp.tech_search.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author lhp
 * @description TODO
 * @date 2021/2/24 14:01
 */
@CrossOrigin
@RestController
@RequestMapping("/expert")
public class ExpertController {

    @Autowired
    private ExpertNodeRepo expertNodeRepo;

    @RequestMapping("/all")
    public Response getAllExperts(@RequestBody HashMap<String, String> map){
        Pageable pageable = PageUtil.getPageableFromMap(map);
        Page<ExpertNode> expertNodes = expertNodeRepo.findAll(pageable);
        return new Response().success(expertNodes);
    }

    @PostMapping("/id")
    public Response getExpertById(@RequestBody HashMap<String, String> map){
        String expertId = map.getOrDefault("id", "");
        if (expertId.length() < 1){
            return new Response().failure("expertId参数缺失或错误");
        }
        ExpertNode expertNode = expertNodeRepo.getExpertNodeByExpertId(expertId);
        return new Response().success(expertNode);

    }
}
