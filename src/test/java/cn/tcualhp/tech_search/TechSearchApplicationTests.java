package cn.tcualhp.tech_search;

import cn.tcualhp.tech_search.common.Response;
import cn.tcualhp.tech_search.model.Neo4jNode.PaperNode;
import cn.tcualhp.tech_search.neo4jRepo.PaperNodeRepo;
import cn.tcualhp.tech_search.utils.PageUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class TechSearchApplicationTests {

    protected Logger log = LoggerFactory.getLogger(this.getClass());

    private Long time;

    @Before
    public void setUp() {
        this.time = System.currentTimeMillis();
        log.info("==> 测试开始执行 <==");
    }

    @After
    public void tearDown() {
        log.info("==> 测试执行完成，耗时：{} ms <==", System.currentTimeMillis() - this.time);
    }

    @Test
    void contextLoads() {
    }

}
