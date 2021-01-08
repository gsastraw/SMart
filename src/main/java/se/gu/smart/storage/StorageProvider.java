package se.gu.smart.storage;

import java.io.IOException;

public interface StorageProvider {

    void save() throws IOException;

    void load() throws IOException;

    void reset() throws IOException;
}
