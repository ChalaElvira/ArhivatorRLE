package arhivator.hierarchy;

import arhivator.Arhivator;
import arhivator.hierarchy.Node;

import java.io.*;

public class Hierarchy implements Serializable {
    public Node getNode() {
        return node;
    }

    Node node = new Node();

    public void scannerDirectory(File path) {
        node.fileName = path;
        node.isDirectory = path.isDirectory();
        if (path.isDirectory()) {
            File[] file = path.listFiles();
            assert file != null;
            node.nodes = scannerDirectory(file);
        } else {
            node.data = getArhivateData(path);
        }
    }

    private Node[] scannerDirectory(File[] paths) {
        Node[] result = new Node[paths.length];

        for (int i = 0; i < paths.length; i++) {
            result[i] = new Node(paths[i]);
            if (result[i].isDirectory) {
                File[] files = paths[i].listFiles();
                assert files != null;
                result[i].nodes = scannerDirectory(files);
            } else {
                result[i].data = getArhivateData(paths[i]);
            }
        }
        return result;
    }

    private String getArhivateData(File file) {
        StringBuilder stringBuilder = new StringBuilder();

        try (FileReader fileReader = new FileReader(file);
             BufferedReader br = new BufferedReader(fileReader)) {

            String line = br.readLine();

            while (line != null) {
                stringBuilder.append(line).append("\n");
                line = br.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Arhivator.arhivate(stringBuilder.toString());
    }

}
