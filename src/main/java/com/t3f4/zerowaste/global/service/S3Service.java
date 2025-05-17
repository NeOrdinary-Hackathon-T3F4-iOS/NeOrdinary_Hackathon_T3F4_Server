package com.t3f4.zerowaste.global.service;

import com.t3f4.zerowaste.apipayload.code.status.ErrorStatus;
import com.t3f4.zerowaste.apipayload.exception.GeneralException;
import io.awspring.cloud.s3.S3Exception;
import io.awspring.cloud.s3.S3Resource;
import io.awspring.cloud.s3.S3Template;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Template s3Template;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucketName;

    /**
     * S3에 이미지 업로드 하기
     */
    public String upload(MultipartFile file) {
        if (file.isEmpty()) {
            throw new GeneralException(ErrorStatus._BAD_REQUEST);
        }
        try (InputStream inputStream = file.getInputStream()) {
            String originalFilename = file.getOriginalFilename();
            String s3FileName = UUID.randomUUID().toString().substring(0, 10) + originalFilename;
            S3Resource upload = s3Template.upload(bucketName, s3FileName, inputStream);
            return upload.getURL().toString();
        } catch (IOException | S3Exception e) {
            throw new GeneralException(ErrorStatus._INTERNAL_SERVER_ERROR);
        }
    }

    public void delete(String fileName) {
        try {
            s3Template.deleteObject(bucketName, fileName);
        } catch (S3Exception e) {
            throw new GeneralException(ErrorStatus._INTERNAL_SERVER_ERROR);
        }
    }
}