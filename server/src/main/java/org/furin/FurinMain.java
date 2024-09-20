package org.furin;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ServiceLoader;
import java.util.logging.Logger;

@QuarkusMain
public class FurinMain implements QuarkusApplication {
    private static final Logger logger=Logger.getLogger("Main");


    @Override
    public int run(String... args) throws Exception {

        Quarkus.waitForExit();

        return 0;
    }

    public static void main(String...args) {
       Quarkus.run(FurinMain.class,args) ;
    }
}
