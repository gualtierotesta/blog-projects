package it.gualtierotesta.blog.vertx.logging.log4j;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.Launcher;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServerRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainVerticle extends AbstractVerticle {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final int PORT = 8888;

    public static void main(final String[] args) {

        Launcher.executeCommand("run", MainVerticle.class.getName());
    }

    @Override
    public void start(final Promise<Void> startPromise) {
        var userName = "Mario";
        vertx.createHttpServer()
            .requestHandler(defaultHandler())
            .listen(PORT, result -> {
                if (result.succeeded()) {
                    LOGGER.info(() ->
                        String.format("Great, %s, the app is available at http://localhost:%d",
                            userName, PORT));
                    startPromise.complete();
                } else {
                    LOGGER.error(() -> "failed", result.cause());
                    startPromise.fail(result.cause());
                }
            });
    }

    private static Handler<HttpServerRequest> defaultHandler() {
        return req -> req.response()
            .putHeader("content-type", "text/plain")
            .end("Hello from Vert.x!");
    }

}
