package pl.lukasik.shop.admin.common.utils;

import com.github.slugify.Slugify;
import org.apache.commons.io.FilenameUtils;

public class UploadedFileNamesUtils {
    public static String slugifyFileName(String filename) {
            String name = FilenameUtils.getBaseName(filename);
            Slugify slugify = Slugify.builder().build();
            String nameAfterSlugify = slugify.slugify(name);

            return nameAfterSlugify + "." + FilenameUtils.getExtension(filename);


    }
}
