package org.zerock.b01.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import groovy.transform.ToString;
import lombok.Data;

@Data
@ToString
public class UploadFileDTO {

    List<MultipartFile> files;
}
