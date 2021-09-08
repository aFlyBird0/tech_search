package cn.tcualhp.tech_search.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;

/**
 * @author lhp
 * @description 分页的工具类
 * @date 2021/2/24 14:05
 */
public class PageUtil {
    /**
     * @Description 从前端传来的json中获取分页参数，返回分页所需的Pageable
     * @Author lhp
     * @Date 2021/2/24 14:10
     * @param map
     * @return org.springframework.data.domain.Pageable
     **/
    public static Pageable getPageableFromMap(HashMap<String, String> map){
        // 获取页号和页面大小，默认一页20条，返回第一页
        int page = Integer.parseInt(map.getOrDefault("page", "0"));
        int size = Integer.parseInt(map.getOrDefault("size", "20"));
        Pageable pageable = PageRequest.of(page, size);
        return pageable;
    }

    public static Pageable getPageableDefault(){
        // 获取页号和页面大小，默认一页20条，返回第一页
        int page = Integer.parseInt("0");
        int size = Integer.parseInt("20");
        Pageable pageable = PageRequest.of(page, size);
        return pageable;
    }
}
