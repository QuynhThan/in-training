package com.ms.training.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageForm {
    private List<MultipartFile> files;
//    private List<ImagesDTO> dtos;
    private String productCode;
}
