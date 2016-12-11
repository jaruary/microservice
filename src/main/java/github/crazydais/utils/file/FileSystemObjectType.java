package github.crazydais.utils.file;

public enum FileSystemObjectType {
    FOLDER(0, "Folder"),
    FILE(1, "File"),;

    private final int typeId;
    private final String displayName;

    FileSystemObjectType(int id, String name) {

        this.typeId = id;
        this.displayName = name;
    }

    public int getFileSystemTypeId() {

        return typeId;
    }

    public String getFileSystemName() {

        return displayName;
    }


}
