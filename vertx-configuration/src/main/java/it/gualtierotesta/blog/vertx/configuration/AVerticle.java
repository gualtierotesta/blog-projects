package it.gualtierotesta.blog.vertx.configuration;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;

public class AVerticle extends AbstractVerticle {

    @Override
    public void start(final Promise<Void> startPromise) {
        JsonObject configuration = config();
        System.out.println("Hello " + configuration.getString("user"));
        startPromise.complete();
    }
}
