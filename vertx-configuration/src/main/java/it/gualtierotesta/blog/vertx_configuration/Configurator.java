package it.gualtierotesta.blog.vertx_configuration;

import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

class Configurator {

    // External configuration file
    private static final String EXTERNAL_CONFIG_FILE = "/opt/config/config.json";

    /**
     * Read and merge all configuration contributions
     *
     * @param pVertx the Vertx instance
     * @return the configuration as JsonObject
     */
    public static Future<JsonObject> readConfiguration(final Vertx pVertx) {
        Promise<JsonObject> promise = Promise.promise();
        createConfigurationRetriever(pVertx).getConfig(promise::handle);
        return promise.future();
    }

    private static ConfigRetriever createConfigurationRetriever(final Vertx pVertx) {

        // Configuration from an external file (file is not mandatory)
        ConfigStoreOptions fileStoreExternal = new ConfigStoreOptions()
                .setType("file")
                .setOptional(true)
                .setConfig(new JsonObject().put("path", EXTERNAL_CONFIG_FILE));

        // Configuration from a file in the application classpath
        // File path: src/resources/config.json
        ConfigStoreOptions fileStoreClasspath = new ConfigStoreOptions()
                .setType("file")
                .setOptional(true)
                .setConfig(new JsonObject().put("path", "config.json"));

        // Configuration from System Properties
        ConfigStoreOptions sysPropsStore = new ConfigStoreOptions().setType("sys");

        // Configuration from environment variables
        ConfigStoreOptions envPropsStore = new ConfigStoreOptions().setType("env");

        // Merge all configurations sources with a well defined order:
        // 1. Environment variables
        // 2. System properties
        // 3. External file
        // 4. Internal (classpath) file
        // Order is "last added wins"
        ConfigRetrieverOptions options = new ConfigRetrieverOptions()
                .addStore(fileStoreClasspath)
                .addStore(fileStoreExternal)
                .addStore(sysPropsStore)
                .addStore(envPropsStore);

        return ConfigRetriever.create(pVertx, options);
    }

}
