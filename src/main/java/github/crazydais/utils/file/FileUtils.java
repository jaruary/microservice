package github.crazydais.utils.file;

import com.google.common.io.Files;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

    private static final Log LOGGER = LogFactory.getLog(FileUtils.class);

    public static void saveFileDataToFileSystem(byte[] fileData,
        String filename, String fileTypeExtension) {

        File tmpFile = Files.createTempDir();
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            is = new ByteArrayInputStream(fileData);
            fos = new FileOutputStream(
                new File(tmpFile + "/" + filename + "." + fileTypeExtension));
            IOUtils.copy(is, fos);
        }
        catch (IOException e) {
            LOGGER.error("Could not save zip file to file system - " + e);
        }
        finally {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(fos);
        }

    }

    public static String getParentFolderForCurrentEntry(String filePath) {

        String[] splitPath = StringUtils.split(filePath, File.separator);
        return (splitPath != null && splitPath.length >= 2) ?
            splitPath[splitPath.length - 2] :
            "";
    }

    public static String getBasenameFromPath(String path) {
        // This methods will get the basename for folders and files.
        // ie - /Folder01/Folder02                  will result as 'Folder02'
        // ie - /Folder01/Folder02/myTxtFile.txt    will result as 'myTxtFile'      <- the
        // extension is not included.
        String name = null;
        try {
            if (path.length() == (path.lastIndexOf("/") + 1)) {
                path = path.substring(0, path.length() - 1);
            }
            name = path.substring(path.lastIndexOf("/") + 1, path.length());

            if (name.contains(".")) {
                name = name.substring(0, name.lastIndexOf("."));
            }
        }
        catch (Exception e) {
            LOGGER.error("Could not get name - " + e);
        }
        return (name != null) ? name : "";
    }

    public static String getFileExtensionFromFilePath(String filePath) {

        String fileExtension = null;
        try {
            fileExtension = FilenameUtils.getExtension(filePath);
        }
        catch (Exception e) {
            LOGGER.error("Could not get file extension - " + e);
        }
        return fileExtension;
    }

    public static String getFilenameFromFilePath(String filePath) {

        return getBasenameFromPath(filePath) + "."
            + getFileExtensionFromFilePath(filePath);
    }

    public static Integer getFileSystemObjectDepthFromPath(String path) {

        Integer depth = null;
        try {
            if (path.endsWith("/")) {
                path = path.substring(0, path.length() - 1);
            }
            String[] levels = path.split("/");

            if (levels == null || levels.length == 0) {
                depth = 0;
            } else {
                depth = levels.length - 1;
            }

        }
        catch (Exception e) {
            LOGGER.error("Could not get file system object depth - " + e);
        }
        return depth;
    }


}
