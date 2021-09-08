package cn.tcualhp.tech_search.controller;

import cn.tcualhp.tech_search.common.Response;
import cn.tcualhp.tech_search.service.KbqaService;
import cn.tcualhp.tech_search.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lhp
 * @description TODO
 * @date 2021/5/13 13:19
 */
@CrossOrigin
@RestController
@RequestMapping("/kbqa")
public class KBQAController {
    @Autowired
    private KbqaService kbqa_service;

    @PostMapping("/query")
    public Response query(@RequestBody HashMap<String, String> map){
        String question = map.get("question");
        Pageable pageable = PageUtil.getPageableFromMap(map);
        Object answer = kbqa_service.kbqa(question, pageable);
        return new Response().success(answer);
    }
}
