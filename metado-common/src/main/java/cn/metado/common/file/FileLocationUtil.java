package cn.metado.common.file;

import java.io.File;

public class FileLocationUtil {

    public static String getUserHome() {
        return System.getProperty("user.dir");
    }

    public static String getUserFileName(String... file){
        StringBuilder serverPath = new StringBuilder(getUserHome());
        // 拼接文件路径
        for (String s : file) {
            serverPath.append(File.separator).append(s);
        }
        return serverPath.toString();
    }
}
