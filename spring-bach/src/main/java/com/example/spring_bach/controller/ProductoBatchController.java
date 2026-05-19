package com.example.spring_bach.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/productos/batch")
@RequiredArgsConstructor
public class ProductoBatchController {

    private final JobLauncher jobLauncher;
    private final Job importProductJob;

    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<String> uploadCsv(
            @RequestParam("file") MultipartFile file
    ) {

        try {

            Path path = Paths.get(
                    "src/main/resources/producto.csv"
            );

            Files.write(path, file.getBytes());

            JobParameters jobParameters =
                    new JobParametersBuilder()
                            .addLong(
                                    "startAt",
                                    System.currentTimeMillis()
                            )
                            .toJobParameters();

            jobLauncher.run(
                    importProductJob,
                    jobParameters
            );

            return ResponseEntity.ok(
                    "Archivo procesado correctamente"
            );

        } catch (Exception e) {

            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }
    }
}
