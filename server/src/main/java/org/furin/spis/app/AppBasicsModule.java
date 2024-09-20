package org.furin.spis.app;

import org.furin.spis.input.InputConfig;

import java.util.List;

public interface AppBasicsModule {
    boolean isEnabled();

    String displayName();


    String key();

    String iconUrl();

    String authDocsUrl();

    boolean supportsConnection();

    List<InputConfig> getInputConfigs();
}
