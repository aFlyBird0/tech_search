package cn.tcualhp.tech_search.HanlpTest;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;

import java.util.List;

/**
 * @author lhp
 * @description TODO
 * @date 2021/3/11 16:45
 */
public class SegTest {
    public static void main(String[] args) {
        SegTest segTest = new SegTest();

//        segTest.testNLPtokenizer();

        String querySentence1 = "杭州电子科技大学挤出成型工艺真强啊";
        String q2 = "给我一篇张三写的论文";
        segTest.testSegment(querySentence1);
        segTest.testSegment(q2);


    }

    public void testSegment(String querySentence){

        Segment segment = HanLP.newSegment().enableCustomDictionary(true).enableNameRecognize(true);
        List<Term> terms = segment.seg(querySentence);
        System.out.println(terms);
    }

//    private void testNLPtokenizer(){
//        System.out.println(NLPTokenizer.segment("我新造一个词叫幻想乡你能识别并标注正确词性吗？"));
//        // 注意观察下面两个“希望”的词性、两个“晚霞”的词性
//        System.out.println(NLPTokenizer.analyze("我的希望是希望张晚霞的背影被晚霞映红").translateLabels());
//        System.out.println(NLPTokenizer.analyze("支援臺灣正體香港繁體：微软公司於1975年由比爾·蓋茲和保羅·艾倫創立。"));
//
//        System.out.println(NLPTokenizer.segment("我新造一个词叫幻想乡你能识别并标注正确词性吗？"));
//    }
}
