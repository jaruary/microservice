package github.crazydais.utils.file;

public class FileDetail {

    private FileSystemObjectType fso;
    private Integer fileDepth;
    private String name;
    private String parentFolderName;
    private String fileType;
    private long fileSize;

    public FileSystemObjectType getFso () {
        return fso;
    }

    public void setFso (FileSystemObjectType fso) {
        this.fso = fso;
    }

    public Integer getFileDepth () {
        return fileDepth;
    }

    public void setFileDepth (Integer fileDepth) {
        this.fileDepth = fileDepth;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getParentFolderName () {
        return parentFolderName;
    }

    public void setParentFolderName (String parentFolderName) {
        this.parentFolderName = parentFolderName;
    }

    public String getFileType () {
        return fileType;
    }

    public void setFileType (String fileType) {
        this.fileType = fileType;
    }

    public long getFileSize () {
        return fileSize;
    }

    public void setFileSize (long fileSize) {
        this.fileSize = fileSize;
    }

    public String printFileSystemObjectStyleName () {
        String fileSystemObjectName;
        if (this.fso == FileSystemObjectType.FOLDER) {
            fileSystemObjectName = getName() + "/";
        } else {
            fileSystemObjectName = getName();
        }
        return fileSystemObjectName;
    }


}
