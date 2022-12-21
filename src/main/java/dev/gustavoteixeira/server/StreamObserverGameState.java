package dev.gustavoteixeira.server;

import dev.gustavoteixeira.models.GameState;
import dev.gustavoteixeira.models.Void;
import io.grpc.stub.StreamObserver;

public class StreamObserverGameState implements StreamObserver<Void> {

    private final Board board;
    private final StreamObserver<GameState> gameStateStreamObserver;

    public StreamObserverGameState(Board board, StreamObserver<GameState> gameStateStreamObserver) {
        this.board = board;
        this.gameStateStreamObserver = gameStateStreamObserver;
    }


    @Override
    public void onNext(Void value) {

        board.movePlayers();
        var gameState = GameState.newBuilder()
                .setClientPosition(board.getClient())
                .setServerPosition(board.getServer())
                .build();
        gameStateStreamObserver.onNext(gameState);

    }

    @Override
    public void onError(Throwable t) {
        System.out.println("Server Error! Message = " + t.getMessage());
    }

    @Override
    public void onCompleted() {
        gameStateStreamObserver.onCompleted();
    }
}
