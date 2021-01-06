package se.gu.smart.storage;

import static java.util.Objects.requireNonNull;

import java.util.EnumMap;
import java.util.Map;

public final class StorageProviderFactory {

    private static final Map<StorageProviderType, StorageProvider> STORAGE_PROVIDERS = new EnumMap<>(StorageProviderType.class);

    static {
        STORAGE_PROVIDERS.put(StorageProviderType.FILE, new FileStorageProvider());
    }

    private StorageProviderFactory() {
    }

    public static StorageProvider getStorageProvider(StorageProviderType storageProviderType) {
        requireNonNull(storageProviderType);

        return STORAGE_PROVIDERS.get(storageProviderType);
    }
}
