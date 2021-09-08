package cn.tcualhp.tech_search.config;

import com.hankcs.hanlp.corpus.io.IIOAdapter;
import org.springframework.core.io.ClassPathResource;

import java.io.*;

/**
 * @author lhp
 * @description TODO
 * @date 2021/3/12 10:49
 */
public class HanlpFileIoAdapter implements IIOAdapter {
    @Override
    public InputStream open(String filePath) throws IOException {
//        ClassPathResource resource = new ClassPathResource(filePath);
//        return new FileInputStream(resource.getFile());

        InputStream input = new ClassPathResource(filePath).getInputStream();
        return input;
    }

    @Override
    public OutputStream create(String filePath) throws IOException {
        ClassPathResource resource = new ClassPathResource(filePath);
        return new FileOutputStream(resource.getFile());
    }
}
