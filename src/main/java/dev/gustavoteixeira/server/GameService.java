package dev.gustavoteixeira.server;

import dev.gustavoteixeira.models.GameServiceGrpc;
import dev.gustavoteixeira.models.GameState;
import dev.gustavoteixeira.models.Void;
import io.grpc.stub.StreamObserver;

public class GameService extends GameServiceGrpc.GameServiceImplBase {


    @Override
    public StreamObserver<Void> rollDice(StreamObserver<GameState> responseObserver) {
        var board = new Board();
        return new StreamObserverGameState(board, responseObserver);
    }

}
