package com.boardproject.File;

import com.boardproject._core.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/file")
@RequiredArgsConstructor
@RestController
public class FileController {
    private final FileService fileService;
    @GetMapping("/load")
    public ResponseEntity<?> load(@RequestParam String url){
        FileResponse.loadDTO loadDTO = fileService.load(url);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, loadDTO.getContentType());

        Resource resource = loadDTO.getResource();

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
