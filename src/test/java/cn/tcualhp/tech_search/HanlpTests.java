package cn.tcualhp.tech_search;

import cn.tcualhp.tech_search.HanlpTest.SegTest;
import org.junit.jupiter.api.Test;

/**
 * @author lhp
 * @description TODO
 * @date 2021/3/12 11:11
 */
public class HanlpTests {

    @Test
    public void testSegment(){
        SegTest segTest = new SegTest();

//        segTest.testNLPtokenizer();

//        String querySentence1 = "杭州电子科技大学挤出成型工艺真强啊";
//        String q2 = "给我一篇张三写的论文";
//        segTest.testSegment(querySentence1);
//        segTest.testSegment(q2);
        String q2 = "杭州电子科技大学关于挤出成型工艺的论文有哪些";
        System.out.println("问题："+q2);
//        System.out.print("分词结果:");
//        System.out.println("分词结果:[杭州电子科技大学/ntu, 关于/p, 挤出成型工艺/kw, 的/uj, 论文/n, 有/v, 哪些/r]");
        String q3 = "何燕珍在2020年写的关于创新模式的论文";
        segTest.testSegment(q2);
        segTest.testSegment(q3);
    }
}
