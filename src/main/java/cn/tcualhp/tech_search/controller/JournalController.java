package cn.tcualhp.tech_search.controller;

import cn.tcualhp.tech_search.common.Response;
import cn.tcualhp.tech_search.model.Neo4jNode.JournalNode;
import cn.tcualhp.tech_search.neo4jRepo.JournalNodeRepo;
import cn.tcualhp.tech_search.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author lhp
 * @description TODO
 * @date 2021/2/24 13:19
 */
@CrossOrigin
@RestController
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    private JournalNodeRepo journalNodeRepo;

    @RequestMapping("/all")
    public Response getAll(@RequestBody HashMap<String, String> map){
        Pageable pageable = PageUtil.getPageableFromMap(map);
        Page<JournalNode> journalNodes = journalNodeRepo.findAll(pageable);
        return new Response().success(journalNodes);
    }
}
