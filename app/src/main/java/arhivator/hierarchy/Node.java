package arhivator.hierarchy;

import java.io.File;
import java.io.Serializable;

public class Node implements Serializable {
    File fileName;
    Boolean isDirectory;
    Node[] nodes;
    String data;

    public File getFileName() {
        return fileName;
    }

    public Boolean getIsDirectory() {
        return isDirectory;
    }

    public Node[] getNodes() {
        return nodes;
    }

    public String getData() {
        return data;
    }

    public Node(File fileName) {
        this.fileName = fileName;
        isDirectory = this.fileName.isDirectory();
    }

    public Node() {
    }
}
