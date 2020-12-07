package se.gu.smart.ui.util;

import static java.util.Objects.requireNonNull;

import java.io.InputStream;
import java.net.URL;

public final class Resources {

    private Resources() {
    }

    public static URL getResource(String resource) {
        requireNonNull(resource);

        var classLoader = FXMLUtil.class.getClassLoader();
        return classLoader.getResource(resource);
    }

    public static InputStream getResourceAsStream(String resource) {
        requireNonNull(resource);

        var classLoader = FXMLUtil.class.getClassLoader();
        return classLoader.getResourceAsStream(resource);
    }
}
