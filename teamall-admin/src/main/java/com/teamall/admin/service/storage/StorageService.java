package com.teamall.admin.service.storage;

import com.teamall.admin.domain.TeaStorage;
import com.teamall.admin.service.DtsStorageService;
import com.teamall.common.utils.CharUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * @author DengQiao
 * @date 2023-9-3 0003
 * 提供存储服务类，所有存储服务均由该类对外提供
 */
@Component
public class StorageService {


    @Autowired
    private DtsStorageService dtsStorageService;
    @Autowired
    private Storage storage;

    /**
     * 存储一个文件对象
     *
     * @param inputStream
     *            文件输入流
     * @param contentLength
     *            文件长度
     * @param contentType
     *            文件类型
     * @param fileName
     *            文件索引名
     */
    public String store(InputStream inputStream, long contentLength, String contentType, String fileName) {
        String key = generateKey(fileName);
        storage.store(inputStream, contentLength, contentType, key);

        String url = generateUrl(key);
        TeaStorage storageInfo = new TeaStorage();
        storageInfo.setName(fileName);
        storageInfo.setSize((int) contentLength);
        storageInfo.setType(contentType);
        storageInfo.setFileKey(key);
        storageInfo.setUrl(url);
        dtsStorageService.save(storageInfo);

        return url;
    }

    private String generateKey(String originalFilename) {
        int index = originalFilename.lastIndexOf('.');
        String suffix = originalFilename.substring(index);

        String key = null;
        TeaStorage storageInfo = null;

        do {
            key = CharUtil.getRandomString(20) + suffix;
            storageInfo = dtsStorageService.findByKey(key);
        } while (storageInfo != null);

        return key;
    }
    private String generateUrl(String keyName) {
        return storage.generateUrl(keyName);
    }
}
