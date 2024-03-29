package pl.lukasik.shop.admin.product.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import pl.lukasik.shop.admin.common.utils.ExistingFileRenameUtils;
import pl.lukasik.shop.admin.common.utils.UploadedFileNamesUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class AdminProductImageService {
    @Value("${app.dirUpload}")
    private String dirUpload;
    public String uploadImage(String filename, InputStream inputStream) {
        String newFileName = UploadedFileNamesUtils.slugifyFileName(filename);
        newFileName = ExistingFileRenameUtils.renameIfExists(dirUpload, newFileName);
        Path filePath = Paths.get(dirUpload).resolve(newFileName);
        try(OutputStream outputStream = Files.newOutputStream(filePath)) {
            inputStream.transferTo(outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return newFileName;
    }

    public Resource serveFiles(String filename) {
        FileSystemResourceLoader fileSystemResourceLoader = new FileSystemResourceLoader();
        return fileSystemResourceLoader.getResource(dirUpload + filename);
    }
}

