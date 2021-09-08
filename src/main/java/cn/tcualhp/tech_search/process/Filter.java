package cn.tcualhp.tech_search.process;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author lhp
 * @description TODO
 * @date 2021/5/13 10:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Filter{
    List<String> mustContain;
    List<String> canNotContain;
}
