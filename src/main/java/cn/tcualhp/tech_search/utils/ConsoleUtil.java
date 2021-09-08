package cn.tcualhp.tech_search.utils;

/**
 * @author jerry
 * @program tech_kg
 * @package_name cn.tcualhp.tech_kg.utils
 * @description 控制台输出format库
 * @create 2019/11/29 11:34
 **/

public class ConsoleUtil {

    /**
     * 打印原始问句
     * @param queryString 用户输入的问题语句
     */
    public static void printOriginSentence(String queryString) {
        System.out.println("原始句子：" + queryString);
        System.out.println("========HanLP开始分词========");
    }

    public static void printSegmentFinishInfo() {
        System.out.println("========HanLP分词结束========");
    }
}
