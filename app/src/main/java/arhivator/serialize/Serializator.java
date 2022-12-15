package arhivator.serialize;

import arhivator.hierarchy.Hierarchy;

import java.io.*;
import java.nio.file.Path;

public class Serializator {
    public boolean serialization(Hierarchy hierarchy, String pathForSaving) {
        boolean serializate;
        File file = new File(pathForSaving);
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(hierarchy);
            serializate = true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return serializate;
    }

    public Hierarchy desSerialitztion(String pathForWrite) {
        Path path = Path.of(pathForWrite);
        File file = new File(path.toString());
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (Hierarchy) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
