package it.gualtierotesta.blog.vertx.configuration;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Launcher;
import io.vertx.core.Promise;

public class MainVerticle extends AbstractVerticle {

    public static void main(final String[] args) {
        Launcher.executeCommand("run", MainVerticle.class.getName());
    }

    @Override
    public void start(final Promise<Void> startPromise) {
        Configurator.readConfiguration(vertx)
            .onSuccess(configuration -> {

                vertx.deployVerticle(new AVerticle(),
                    new DeploymentOptions().setConfig(configuration));
                startPromise.complete();
            })
            .onFailure(startPromise::fail);
    }

}
