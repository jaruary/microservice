package github.crazydais.utils.file;

import com.google.common.io.Files;
import java.awt.Frame;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FileUtils {

    private static final Log LOGGER = LogFactory.getLog(FileUtils.class);
    private static final int BUFFER_SIZE = 4096;
    private static final long MAX_ZIP_SIZE_BYTES = 100000000;   // 100MB

    public static File pickZipFile () {
        File returnFile = null;

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

        int result = fileChooser.showOpenDialog(new JFrame());
        if (result == JFileChooser.APPROVE_OPTION) {
            returnFile = fileChooser.getSelectedFile();
        }

        if (Frame.getFrames().length > 0) {
            for (Frame frame : Frame.getFrames()) {
                frame.dispose();
            }
        }

        return returnFile;
    }

    public static boolean checkForPotentialZipBomb (File zipFile) {
        boolean isZipBomb = false;
        long size = 0;
        List<ZipEntryDetail> enteries = getFileStructureFromZip(zipFile);
        for (ZipEntryDetail detail : enteries) {
            if (detail.getFso() == FileSystemObjectType.FILE) {
                if (detail.getFileType().equalsIgnoreCase(".zip")) {
                    isZipBomb = true;
                    break;
                }
                size = size + detail.getFileSize();
                if (size > MAX_ZIP_SIZE_BYTES) {
                    isZipBomb = true;
                    break;
                }
            }
        }
        return isZipBomb;
    }

    public static List<ZipEntryDetail> getFileStructureFromZip (File zipFile) {
        List<ZipEntryDetail> structure = new ArrayList<>();
        ZipFile zip;
        try {
            zip = new ZipFile(zipFile);
            for (Enumeration e = zip.entries(); e.hasMoreElements();) {
                ZipEntry entry = (ZipEntry) e.nextElement();
                ZipEntryDetail entryDetails = new ZipEntryDetail();
                entryDetails.setFso((entry.isDirectory()) ? FileSystemObjectType.FOLDER : FileSystemObjectType.FILE);
                entryDetails.setFileDepth(getFileSystemObjectDepthFromPath(entry.getName()));
                entryDetails.setName(getBaseFolderFileNameFromPath(entry.getName()));
                entryDetails.setFileType(getFileExtensionFromFilePath(entry.getName()));
                entryDetails.setFileSize(entry.getSize());
                structure.add(entryDetails);

            }
        } catch (IOException e) {
            LOGGER.error("Could not unzip file - " + e);
        }
        return structure;
    }

    public static void unZipAll (File zipFile) {
        ZipFile zip;
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            zip = new ZipFile(zipFile);
            for (Enumeration e = zip.entries(); e.hasMoreElements();) {
                ZipEntry entry = (ZipEntry) e.nextElement();
                is = zip.getInputStream(entry);
                //fos = new FileOutputStream(new File(zipFile.getAbsoluteFile() + "/" + ZipUtil.getNameFromFilePath(entry.getName())));
                fos = new FileOutputStream(new File(zipFile.getAbsolutePath()));
                int read;
                byte[] bytes = new byte[BUFFER_SIZE];
                while ((read = is.read(bytes)) != -1) {
                    fos.write(bytes, 0, read);
                }
            }
        } catch (IOException e) {
            LOGGER.error("Could not unzip file - " + e);
        } finally {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(fos);
        }
    }

    public static List<File> unZipAllDocumentFiles (File zipFile) {
        List<File> files = new ArrayList<>();
        InputStream is = null;
        FileOutputStream fos = null;
        File tmpFile = Files.createTempDir();
        try {
            ZipFile zip = new ZipFile(zipFile);
            for (Enumeration e = zip.entries(); e.hasMoreElements();) {
                ZipEntry entry = (ZipEntry) e.nextElement();
                if (!entry.isDirectory() && !getFileExtensionFromFilePath(entry.getName()).equalsIgnoreCase("zip")) {
                    is = zip.getInputStream(entry);
                    fos = new FileOutputStream(new File(tmpFile + "/" + FileUtils.getBaseFolderFileNameFromPath(entry.getName())));
                    IOUtils.copy(is, fos);
                    files.add(tmpFile);
                }
            }
        } catch (IOException e) {
            LOGGER.error("Could not unzip file - " + e);
        } finally {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(fos);
            boolean didDelete = tmpFile.delete();
            LOGGER.info((didDelete) ? "Was able" : "Was not able " + "to delete folder " + tmpFile.getAbsolutePath());
        }

        return files;
    }

    public static List<byte[]> unZipAllDocumentFilesAsData (File zipFile) {
        List<byte[]> files = new ArrayList<>();
        InputStream is = null;
        try {
            ZipFile zip = new ZipFile(zipFile);
            for (Enumeration e = zip.entries(); e.hasMoreElements();) {
                ZipEntry entry = (ZipEntry) e.nextElement();
                if (!entry.isDirectory() && !getFileExtensionFromFilePath(entry.getName()).equalsIgnoreCase("zip")) {
                    is = zip.getInputStream(entry);
                    files.add(IOUtils.toByteArray(is));
                }
            }
        } catch (IOException e) {
            LOGGER.error("Could not unzip file - " + e);
        } finally {
            IOUtils.closeQuietly(is);
        }

        return files;
    }

    public static void saveFileDataToFileSystem (byte[] zipData, String filename, String fileTypeExtension) {
        File tmpFile = Files.createTempDir();
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            is = new ByteArrayInputStream(zipData);
            fos = new FileOutputStream(new File(tmpFile + "/" + filename + "." + fileTypeExtension));
            IOUtils.copy(is, fos);
        } catch (IOException e) {
            LOGGER.error("Could not save zip file to file system - " + e);
        } finally {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(fos);
        }

    }

    public static String getFileExtensionFromFilePath (String filePath) {
        String fileExtension = null;
        try {
            fileExtension = FilenameUtils.getExtension(filePath);
        } catch (Exception e) {
            LOGGER.error("Could not get file extension - " + e);
        }
        return fileExtension;
    }

    public static String getFileBaseNameFromFilePath (String filePath) {
        String fileBasename = null;
        try {
            fileBasename = FilenameUtils.getBaseName(filePath);
        } catch (Exception e) {
            LOGGER.error("Could not get file base name - " + e);
        }
        return fileBasename;
    }

    public static String getBaseFolderFileNameFromPath (String folderPath) {
        String folderName = null;
        try {
            if (folderPath.length() == (folderPath.lastIndexOf("/") + 1)) {
                folderPath = folderPath.substring(0, folderPath.length() - 1);
            }
            folderName = folderPath.substring(folderPath.lastIndexOf("/") + 1);
        } catch (Exception e) {
            LOGGER.error("Could not get name - " + e);
        }
        return (folderName != null) ? folderName : "";
    }

    public static Integer getFileSystemObjectDepthFromPath (String path) {
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

        } catch (Exception e) {
            LOGGER.error("Could not get file system object depth - " + e);
        }
        return depth;
    }

    public static void printZipStructure (List<ZipEntryDetail> structure) {
        String print = "\n\n";
        for (ZipEntryDetail zed : structure) {
            Integer depth = zed.getFileDepth();
            String name = zed.printFileSystemObjectStyleName();

            for (int i = 0; i < depth; ++i) {
                print = print + "\t";
            }
            print = print + name + "\n";
        }
        LOGGER.info(print);
    }

}
