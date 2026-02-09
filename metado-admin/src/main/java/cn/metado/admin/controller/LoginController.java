package cn.metado.admin.controller;

import cn.metado.common.file.FileLocationUtil;
import cn.metado.common.utils.DownloadUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/login")
public class LoginController {
    @RequestMapping("/login")
    @ResponseBody
    public String index() {
        return "login";
    }

    // @GetMapping("/stream")
    // public ResponseEntity<StreamingResponseBody> stream() {
    //     return ResponseEntity.ok(outputStream -> {
    //         outputStream.write("hello".getBytes());
    //     });
    // }

    @GetMapping("/stream")
    public StreamingResponseBody stream() {
        return outputStream -> {
            for (int i = 0; i < 5; i++) {
                outputStream.write(("chunk-" + i + "\n").getBytes());
                outputStream.flush();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    @GetMapping("/download")
    public ResponseEntity<StreamingResponseBody> download() throws FileNotFoundException {
        String fileName = FileLocationUtil.getUserFileName("static","file", "1.docx");
        // 基于 InputStream 的文件下载
        Path path = Paths.get(fileName);
        File file = path.toFile();
        FileInputStream fileInputStream = new FileInputStream(file);
        return DownloadUtil.download(fileInputStream, "1.docx", null);
    }
}
