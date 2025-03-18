package org.zerock.b01.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.b01.dto.UploadFileDTO;
import org.zerock.b01.dto.UploadResultDTO;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;

@RestController
@Slf4j
public class UpDownController {

    @Value("${org.zerock.upload.path}")
    private String uploadPath;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<UploadResultDTO> upload(UploadFileDTO uploadFileDTO) {
        log.debug("uploadFileDTO = {}", uploadFileDTO);

        if (uploadFileDTO.getFiles() != null) {
            final List<UploadResultDTO> list = new ArrayList<>();

            uploadFileDTO.getFiles().forEach(multiPartFile -> {
                String originalName = multiPartFile.getOriginalFilename();
                log.debug("fileName = {}", originalName);

                String uuid = UUID.randomUUID().toString();
                Path savePath = Paths.get(uploadPath, uuid + "_" + originalName); // Path(파일을 저장할 경로를 나타내기 위해 사용)
                boolean image = false;

                try {
                    multiPartFile.transferTo(savePath);

                    // 이미지 여부 체크
                    if (Files.probeContentType(savePath).startsWith("image")) {
                        image = true;

                        File thumbFile = new File(uploadPath, "s_" + uuid + "_" + originalName); // File(Thumbnailator라이브러리 사용을 위해 File로 변환)

                        Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 200, 200);
                    }
                } catch (IllegalStateException | IOException e) {
                    e.printStackTrace();
                }

                list.add(UploadResultDTO.builder().uuid(uuid).fileName(originalName).img(image).build());

            });
            return list;
        }
        return null;
    }

    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> viewFileGET(@PathVariable("fileName") String fileName) {
        //확장자도 같이 너어야 한다
        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);

        /*
         * if (!resource.exists()) { return ResponseEntity.notFound().build(); }
         */

        String resourceName = resource.getFilename();

        HttpHeaders headers = new HttpHeaders();
        try {
            headers.add(HttpHeaders.CONTENT_TYPE, Files.probeContentType(resource.getFile().toPath()));

            // 다운로드 설정
            // String downloadFileName = resource.getFilename().split("_")[1];
            // String encodedFileName = URLEncoder.encode(downloadFileName, StandardCharsets.UTF_8.toString());
            // headers.add(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment().filename(encodedFileName).build().toString());
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().headers(headers).body(resource);
    }

    @DeleteMapping("/remove/{fileName}")
    public Map<String, Boolean> removeFile(@PathVariable("fileName") String fileName){
        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);

        /*
         * if (!resource.exists()) { return ResponseEntity.notFound().build(); }
         */

        String resourceName = resource.getFilename();

        Map<String, Boolean> resultMap = new HashMap<>();

        boolean removed = false;

        try {
            String contentType = Files.probeContentType(resource.getFile().toPath());

            removed = resource.getFile().delete();

            if(contentType.startsWith("image")) {
                File thumbnailFile = new File(uploadPath +  File.separator + "s_"+fileName);

                thumbnailFile.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultMap.put("result", removed);

        return resultMap;
    }

}
