package org.furin.loaders;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.furin.registeries.AppRegistry;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AppLoader {
    private static final Logger logger=Logger.getLogger("ProviderLoader");

    @Inject
    AppRegistry appRegistry;

    void onStart(@Observes StartupEvent ev){
        logger.info("Loading all the available providers");
        load();
    }

    public void load(){
        try{
            Path currentPath = Paths.get("").toAbsolutePath();

            // Navigate to the providers directory, which is a sibling of the current directory
            Path providersPath = currentPath.resolveSibling("providers");
            if(providersPath==null){
                System.out.println("No providers directory found");
                return;
            }

            File[] providerJars=providersPath.toFile().listFiles();

            if(providerJars==null){
                System.out.println("Cannot Find any provider jars");
                return;
            }


            List<URL> urls = new ArrayList<>();

            for(File providerJar : providerJars){
               if(providerJar.isFile() && providerJar.getName().toLowerCase().endsWith(".jar")) {
                   urls.add(providerJar.toURI().toURL());
               }
            }

            URLClassLoader classLoader=new URLClassLoader(urls.toArray(new URL[0]),Thread.currentThread().getContextClassLoader());

//            ServiceLoader<ProviderInterfacee> serviceloader=ServiceLoader.load(ProviderInterfacee.class,classLoader);
//            for(ProviderInterfacee provider:serviceloader){
//                providerRegistry.addProvider(provider);
//            }



        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
