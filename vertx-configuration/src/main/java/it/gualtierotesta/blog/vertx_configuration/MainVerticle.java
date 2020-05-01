package it.gualtierotesta.blog.vertx_configuration;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Launcher;
import io.vertx.core.Promise;

public class MainVerticle extends AbstractVerticle {

    public static void main(final String[] args) {
        Launcher.executeCommand("run", MainVerticle.class.getName());
    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        Configurator.readConfiguration(vertx)
            .onSuccess(configuration -> {
                System.out.println("Hello " + configuration.getString("user"));
                startPromise.complete();
            })
            .onFailure(startPromise::fail);
    }

}
