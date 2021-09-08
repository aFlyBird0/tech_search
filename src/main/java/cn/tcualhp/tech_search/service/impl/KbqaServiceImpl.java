package cn.tcualhp.tech_search.service.impl;

import cn.tcualhp.tech_search.common.KbqaResData;
import cn.tcualhp.tech_search.model.Neo4jNode.ExpertNode;
import cn.tcualhp.tech_search.model.Neo4jNode.PaperNode;
import cn.tcualhp.tech_search.model.Neo4jNode.UnitNode;
import cn.tcualhp.tech_search.neo4jRepo.ExpertNodeRepo;
import cn.tcualhp.tech_search.neo4jRepo.JournalNodeRepo;
import cn.tcualhp.tech_search.neo4jRepo.PaperNodeRepo;
import cn.tcualhp.tech_search.neo4jRepo.UnitNodeRepo;
import cn.tcualhp.tech_search.service.KbqaService;
import cn.tcualhp.tech_search.service.Question2ModelString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author lhp
 * @description TODO
 * @date 2021/5/13 12:56
 */
@Service
public class KbqaServiceImpl implements KbqaService {

    @Autowired
    private Question2ModelString question2ModelString;

    @Autowired
    private ExpertNodeRepo expertNodeRepo;

    @Autowired
    private PaperNodeRepo paperNodeRepo;

    @Autowired
    private UnitNodeRepo unitNodeRepo;

    @Autowired
    private JournalNodeRepo journalNodeRepo;

    @Override
    public Object kbqa(String question, Pageable pageable){

        ArrayList<String> reStrings = null;
        try {
            reStrings = question2ModelString.analysisQuery(question);
        } catch (Exception e) {
            e.printStackTrace();
            return "服务器发生错误";
        }
        int modelIndex = Integer.parseInt(reStrings.get(0));



        /**
         * 匹配问题模板
         */
        switch (modelIndex) {
            case 0:
                /**
                 * 抽象问题模板：nr 论文
                 */
                String expertName = reStrings.get(1);
                Page<PaperNode> paperNodes = paperNodeRepo.getPaperNodesByExpertName(expertName, pageable);
                if (paperNodes.getTotalElements() < 1) {
                    return new KbqaResData().noAnswer();
                } else {
                    return new KbqaResData().PaperAnswer(paperNodes);
                }
            case 1:
//                /**
//                 * 抽象问题模板：nr 单位
//                 */
//                expertName = reStrings.get(1);
//                /**
//                 * 注意这里要判断重名
//                 * 现在只是简单取第一个
//                 * 以后要加如下判断
//                 * 如果人多余一个要询问是哪个
//                 * 如果只有一个直接返回论文
//                 */
//                expertNode = expertNodeRepo.getExpertNodesByName(expertName).get(0);
//                unitNodes = expertNode.getUnitNodes();
//                answer = unitNodes.toString();
//                break;
                expertName = reStrings.get(1);
                Page<UnitNode> unitNodes = unitNodeRepo.getUnitNodesByExpertName(expertName, pageable);
                if (unitNodes.getTotalElements() < 1) {
                    return new KbqaResData().noAnswer();
                } else {
                    return new KbqaResData().UnitAnswer(unitNodes);
                }
            case 2:
                /**
                 * 抽象模板详见classify/classifications.json
                 * 这里最好把case都封装成函数，现在太乱了
                 * 比如所有的case的变量不能重复定义，很坑
                 * 这里是 2 nr n2r 合作论文
                 *
                 */
//                answer = paperNodeRepo.getPaperNodeByCooperation(reStrings.get(1), reStrings.get(2)).toString();
//                break;
                expertName = reStrings.get(1);
                String expertName2 = reStrings.get(2);
                Page<PaperNode> paperNodes1 = paperNodeRepo.getPaperNodeByCooperation(expertName, expertName2, pageable);
                if (paperNodes1.getTotalElements() < 1) {
                    return new KbqaResData().noAnswer();
                } else {
                    return new KbqaResData().PaperAnswer(paperNodes1);
                }
            case 3:
                /**
                 * 抽象问题模板：m年发表的某关键词的论文/专利有哪些
                 *
                 */
                String year = reStrings.get(1).replace("年", "");
                String keyword = reStrings.get(2);
//                keywords = ".*" + keywords + ".*";
                Page<PaperNode> paperNodes2 = paperNodeRepo.getPaperNodeByYearAndKeywords(year, keyword, pageable);
                if (paperNodes2.getTotalElements() < 1) {
                    return new KbqaResData().noAnswer();
                } else {
                    return new KbqaResData().PaperAnswer(paperNodes2);
                }
            case 4:
                /**
                 * 某单位的论文/专利有哪些
                 */
                String unitName = reStrings.get(1);
                Page<PaperNode> paperNodes3 = paperNodeRepo.getPaperNodeByUnitHavePaper(unitName, pageable);
                if (paperNodes3.getTotalElements() < 1) {
                    return new KbqaResData().noAnswer();
                } else {
                    return new KbqaResData().PaperAnswer(paperNodes3);
                }
            case 5:
                /**
                 * 某单位 有哪些专家
                 */
                /**
                 * 对先前的数据 StringBuffer 的数据进行清空
                 */
                String unitName2 = reStrings.get(1);
                Page<ExpertNode> expertNodes = expertNodeRepo.getExpertNodesByUnitNameContains(unitName2, pageable);
                if (expertNodes.getTotalElements() < 1) {
                    return new KbqaResData().noAnswer();
                } else {
                    return new KbqaResData().ExpertAnswer(expertNodes);
                }
            case 6:
                /**
                 * 某专家nr 关于 关键字kw 的论文有哪些 ？
                 */
                String expertName3 = reStrings.get(1);
                String keywords2 = reStrings.get(2);
                Page<PaperNode> paperNodes4= paperNodeRepo.getPaperNodeByExpertNameAndKeywords(keywords2, expertName3, pageable);
                if (paperNodes4.getTotalElements() < 1) {
                    return new KbqaResData().noAnswer();
                } else {
                    return new KbqaResData().PaperAnswer(paperNodes4);
                }
            default:
                return new KbqaResData().noAnswer();
        }
    }
}
