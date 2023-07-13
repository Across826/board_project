package com.boardproject.File;

import com.boardproject._core.errors.exception.Exception500;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileService {
    public static final char SEPARATOR_CHAR = File.separatorChar;   // 운영체제에 맞추기 위함
    public static final String PROJECT_ROOT_PATH = System.getProperty("user.dir");
    private static final String DEV_FILES_PATH = "src/main/resources/static/files";
    public static final String OS_FILES_PATH = DEV_FILES_PATH.replace('/', SEPARATOR_CHAR);
    public static final String SAVE_SERVER_PATH = PROJECT_ROOT_PATH + SEPARATOR_CHAR + OS_FILES_PATH;

    @Transactional
    public String save(MultipartFile file)  {
        // 랜덤 식별자
        UUID uuid = UUID.randomUUID();

        // 식별자_파일이름 -> 저장될 파일 이름
        String fileName = uuid + "_" + file.getOriginalFilename();

        // 새 파일명과 경로를 지정하여 파일 생성
        File saveFile = new File(SAVE_SERVER_PATH, fileName);

        try {
            file.transferTo(saveFile);
            return saveFile.getPath();
        } catch (IOException e) {
            throw new Exception500("서버: 파일 저장 오류!");
        }
    }

    @Transactional
    public FileResponse.loadDTO load(String url){
        try {
            Path path = Paths.get(url);

            String contentType = Files.probeContentType(path);
            Resource resource = new InputStreamResource(Files.newInputStream(path));

            return new FileResponse.loadDTO(resource,contentType);
        } catch (IOException e) {
            throw new Exception500("서버: 파일 로드 오류!");
        }
    }
}
