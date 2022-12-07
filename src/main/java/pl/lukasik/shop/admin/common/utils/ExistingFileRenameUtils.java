package pl.lukasik.shop.admin.common.utils;

import org.apache.commons.io.FilenameUtils;

import java.nio.file.Files;
import java.nio.file.Paths;

public class ExistingFileRenameUtils {


    public static String renameIfExists(String dirUpload, String fileName) {

        if(Files.exists(Paths.get(dirUpload,fileName))) {
            return renameAndCheck(dirUpload, fileName);
        }

        return fileName;
    }

    private static String renameAndCheck(String dirUpload, String fileName) {
        String newFileName = renameFile(fileName);
        if(Files.exists(Paths.get(dirUpload,newFileName))) {
            newFileName = renameAndCheck(dirUpload, newFileName);
        }
        return newFileName;
    }

    private static String renameFile(String fileName) {
        String baseName = FilenameUtils.getBaseName(fileName);
        String[] split = baseName.split("-(?=[0-9]+$)");

        int counter = split.length > 1 ? Integer.parseInt(split[1]) + 1 : 1;
        return split[0] + "-" + counter + "." + FilenameUtils.getExtension(fileName);
    }
}
