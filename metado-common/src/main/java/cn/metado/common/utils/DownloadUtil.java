package cn.metado.common.utils;

import org.springframework.http.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.io.InputStream;

public class DownloadUtil {

    /**
     * 基于 InputStream 的文件下载
     */
    public static ResponseEntity<StreamingResponseBody> download(InputStream inputStream, String fileName, String contentType) {
        StreamingResponseBody body = outputStream -> {
            try (InputStream in = inputStream) {
                in.transferTo(outputStream);
                outputStream.flush();
            }
        };

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename(fileName, StandardCharsets.UTF_8).build());
        headers.setContentType(MediaType.parseMediaType(Optional.ofNullable(contentType).orElse(MediaType.APPLICATION_OCTET_STREAM_VALUE)));

        return new ResponseEntity<>(body, headers, HttpStatus.OK);
    }
}
