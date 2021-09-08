package cn.tcualhp.tech_search.process;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author jerry
 * @program tech_kg
 * @package_name cn.tcualhp.tech_kg.process
 * @description 词语库
 **/
@Data
@NoArgsConstructor
public class Vocabulary {
    /**
     * 注意这个id没用，没用，没用
     * 因为会有重复
     * 并且后面的id会重新指定
     * 因为id关系到数组长度
     */
    private long id;

    /**
     * 词的值，唯一，去重依据
     */
    private String value;

    /**
     * 词的词性
     */
    private String type;

    /**
     * 返回指定词汇 json 文件下的词汇的 list
     *
     * @param filename 词汇文件名
     * @return list 全部词汇的 list
     * @throws IOException
     */
//    public Set<Vocabulary> getVocabularySet(String filename) throws IOException {
//        JSONObject jsonObject = LoadJsonUtil.getJsonObject(filename);
//        JSONArray vocabulary = jsonObject.getJSONArray("vocabulary");
//        Set<Vocabulary> vocabularies = new HashSet<>();
//        List<Vocabulary> vocabularyList = new ArrayList<>();
//        for (int i = 0; i < vocabulary.size(); i++) {
//            Vocabulary v = new Vocabulary();
//            v.id = i + 1;
//            v.value = vocabulary.getJSONObject(i).getString("value");
//            v.type = vocabulary.getJSONObject(i).getString("type");
//            vocabularies.add(v);
//        }
//        return vocabularies;
//    }

    /**
     *
     * @param filename
     * @return
     */
    public Set<String> getVocabularySet(String filename) {
        String filepath = "src/main/resources/vocabulary/";
        Set<String> vocabularies = new HashSet<>();
        try {
            File file = new File(filepath + filename);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
                vocabularies.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vocabularies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vocabulary that = (Vocabulary) o;
        return id == that.id &&
                Objects.equals(value, that.value) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, type);
    }

    public static void main(String[] args) {
        Vocabulary vocabulary = new Vocabulary();
        Set<String> vocabularies = vocabulary.getVocabularySet("vocabulary.txt");
        int count = 0;
        for (String v : vocabularies) {
            System.out.println(v);
            count++;
        }
        System.out.println(count);
    }

}
