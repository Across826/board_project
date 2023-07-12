package com.boardproject._core.utils;

import com.boardproject._core.errors.exception.Exception500;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileHandler {
    private static final String LOCAL_FILES_PATH = "src/main/resources/static/files";
    public static final String PROJECT_ROOT_PATH = System.getProperty("user.dir");
    public static final char SEPARATOR_CHAR = File.separatorChar;   // 운영체제에 맞추기 위함
    public static final String FILES_PATH = PROJECT_ROOT_PATH + SEPARATOR_CHAR + LOCAL_FILES_PATH.replace('/', SEPARATOR_CHAR);

    public static String save(MultipartFile file)  {
        // 랜덤 식별자
        UUID uuid = UUID.randomUUID();

        // 식별자_파일이름 -> 저장될 파일 이름
        String fileName = uuid + "_" + file.getOriginalFilename();

        // 새 파일명과 경로를 지정하여 파일 생성
        File saveFile = new File(FILES_PATH, fileName);

        try {
            file.transferTo(saveFile);

            return saveFile.getCanonicalPath();
        } catch (IOException e) {
            throw new Exception500("서버: 파일 저장 오류!");
        }
    }
}
