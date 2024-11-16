package com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.files;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileService {

    private FileRepository fileRepository;

    public File store(MultipartFile newFile) throws IOException {
        String filename = StringUtils.cleanPath(newFile.getOriginalFilename());
        File file = fileRepository.save(File.builder().name(filename).data(newFile.getBytes()).build());

        return file;
    }

    public File getFile(Integer id) throws FileNotFoundException{
        return fileRepository.findById(id).orElseThrow(() -> new FileNotFoundException("File id = "+id+" not found"));
    }
}
