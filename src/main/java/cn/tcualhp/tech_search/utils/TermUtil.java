package cn.tcualhp.tech_search.utils;


import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author jerry
 * @program tech_kg
 * @package_name cn.tcualhp.tech_kg.utils
 * @description Term相关工具库
 * @create 2019/11/22 19:02
 **/

public class TermUtil {
    /**
     * 判断
     * @param t
     * @param s
     * @return
     */
    public  static  Boolean isWordNatureEquals(Term t, String s){
        return !StringUtils.isEmpty(s) && s.equals(t.nature.toString());
    }

    public static void main(String[] args) {
        String s = "我新造一个词叫幻想乡你能识别并标注正确词性吗？";
        List<Term> list = NLPTokenizer.segment(s);
        for (Term term : list) {
            if (isWordNatureEquals(term,"n")) {
                System.out.println( term.word +" yes");
            }
        }
    }
}
