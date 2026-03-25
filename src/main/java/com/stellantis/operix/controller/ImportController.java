package com.stellantis.operix.controller;


import com.stellantis.operix.dto.importsap.ImportResultDto;
import com.stellantis.operix.service.importsap.ImportService;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/imports")
@RequiredArgsConstructor
public class ImportController {

    private final List importServices;

    @PostMapping("/upload")
    public ResponseEntity<ImportResultDto> upload(
            @RequestParam MultipartFile file) throws IOException {
        String type = file.getOriginalFilename()
                .endsWith(".csv") ? "csv" : "excel";
        ImportService svc = importServices.stream()
                .filter(s -> s.supports(type))
                .findFirst().orElseThrow();
        return ResponseEntity.ok(svc.importer(
                file.getInputStream(),
                file.getOriginalFilename()));
    }
}