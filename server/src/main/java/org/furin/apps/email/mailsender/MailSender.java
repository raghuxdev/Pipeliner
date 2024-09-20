package org.furin.apps.email.mailsender;
import org.furin.spis.app.AppModule;
import org.furin.spis.input.InputConfig;
import java.util.List;

import static java.util.Arrays.asList;


public class MailSender implements AppModule {
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String displayName() {
        return "MailSender";
    }

    @Override
    public String key() {
        return "mailsender";
    }

    @Override
    public String iconUrl() {
        return null;
    }

    @Override
    public String authDocsUrl() {
        return null;
    }

    @Override
    public boolean supportsConnection() {
        return false;
    }

    @Override
    public List<InputConfig> getInputConfigs() {
        return asList(
                new InputConfig.Builder()
                        .key("token")
                        .description("Token Obtained from MailSender Dashboard")
                        .helpText("Token will be used for interacting with MailSender")
                        .name("Access Token")
                        .required(true)
                        .visible(true)
                        .build()
        );
    }

}
