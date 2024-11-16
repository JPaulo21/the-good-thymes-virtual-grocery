package com.jp.thymeleaf.thegoodthymesvirtualgrocery.web.controller;

import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.files.File;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.files.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/files")
public class FilesController {

    private FileService fileService;

    @PostMapping
    public ResponseEntity upload(@RequestParam("file") MultipartFile fileDTO, UriComponentsBuilder ucb) throws IOException {
        File file = fileService.store(fileDTO);

        URI location = ucb
                .path("/files/{id}")
                .buildAndExpand(file.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/user/{id}/")
    public ResponseEntity<byte[]> getFile(@PathVariable("id") Integer id) throws FileNotFoundException {
        File file = fileService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\""+ file.getId() + "\"")
                .body(file.getData());
    }

}
