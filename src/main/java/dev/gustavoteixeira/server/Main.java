package dev.gustavoteixeira.server;

import io.grpc.ServerBuilder;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        var server = ServerBuilder.forPort(6565)
                .addService(new GameService())
                .build();

        server.start();

        server.awaitTermination();
    }
}
