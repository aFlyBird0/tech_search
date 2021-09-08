package cn.tcualhp.tech_search.service;

import org.springframework.data.domain.Pageable;

/**
 * @author lhp
 * @description TODO
 * @date 2021/5/13 12:55
 */
public interface KbqaService {
    Object kbqa(String question, Pageable pageable);
}
