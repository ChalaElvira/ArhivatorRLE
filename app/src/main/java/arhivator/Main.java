package arhivator;

import arhivator.hierarchy.Hierarchy;
import arhivator.hierarchy.Node;
import arhivator.serialize.Serializator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class Main {
    static Node tempNode;
    static Path newPath;

    public static void readHierarchy(Hierarchy hierarchy, File newPath) {
        tempNode = hierarchy.getNode();
        Main.newPath = newPath.toPath();
        String newP = Main.newPath.toAbsolutePath() + "/" + tempNode.getFileName().getName();
        if (tempNode.getIsDirectory()) {
            new File(newP).mkdir();
            readHierarchy(tempNode.getNodes());
        } else {
            createFile(tempNode, newP);
        }
    }

    public static void readHierarchy(Node[] nodes) {
        for (Node node : nodes) {
            String newP = newPath.toAbsolutePath() + "/" + tempNode.getFileName().getName() + "/" + tempNode.getFileName().toPath().relativize(node.getFileName().toPath());
            if (node.getIsDirectory()) {
                new File(newP).mkdir();
                readHierarchy(node.getNodes());
            } else {
                createFile(node, newP);
            }
        }
    }

    private static void createFile(Node node, String newName) {
        try (FileWriter fw = new FileWriter(newName);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(Arhivator.dearhivate(node.getData()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            return;
        }
        String pathFromArgs = args[0];
        Serializator serializator = new Serializator();
        Hierarchy hierarchy;
        if (pathFromArgs.endsWith(".elya")) {
            if (args.length < 2) {
                return; //якщо не вказано куди розпаковувати архів, то виходимо
            }
            hierarchy = serializator.desSerialitztion(pathFromArgs);
            readHierarchy(hierarchy, new File(args[1]));
        } else {
            File f = new File(pathFromArgs);
            hierarchy = new Hierarchy();
            hierarchy.scannerDirectory(f);
            String path = pathFromArgs + ".elya";
            serializator.serialization(hierarchy, path);
        }
    }
}