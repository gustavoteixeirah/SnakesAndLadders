package dev.gustavoteixeira.server;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Board {

    private int client;
    private int server;

    private final Map<Integer, Integer> snakes;
    private final Map<Integer, Integer> ladders;

    public Board() {
        resetPositions();
        snakes = getSnakes();
        ladders = getLadders();
    }

    public void movePlayers() {
        moveClient();
        if (this.client >= 100) {
            System.out.println("Client wins the game!");
            resetPositions();
        }
        checkForSnakesAndLadders();

        moveServer();
        if (this.server >= 100) {
            System.out.println("Server wins the game!");
            resetPositions();
        }
        checkForSnakesAndLadders();
    }

    private void moveServer() {
        int positionsToMove = getPositionsToMove();
        System.out.println("Server rolled: " + positionsToMove);
        this.server += positionsToMove;
    }

    private void moveClient() {
        int positionsToMove = getPositionsToMove();
        System.out.println("Client rolled: " + positionsToMove);
        this.client += positionsToMove;
    }

    private static int getPositionsToMove() {
        return ThreadLocalRandom.current().nextInt(1, 7);
    }

    private void checkForSnakesAndLadders() {
        ladders.computeIfPresent(this.client, (k, v) -> this.client = ladders.get(k));
        snakes.computeIfPresent(this.client, (k, v) -> this.client = snakes.get(k));
    }

    private void resetPositions() {
        this.client = 0;
        this.server = 0;
    }

    public int getClient() {
        return client;
    }

    public int getServer() {
        return server;
    }


    private static Map<Integer, Integer> getSnakes() {
        final Map<Integer, Integer> snakes;
        snakes = new HashMap<>();
        snakes.put(36, 6);
        snakes.put(32, 10);
        snakes.put(62, 18);
        snakes.put(88, 24);
        snakes.put(48, 26);
        snakes.put(95, 56);
        snakes.put(97, 78);
        return snakes;
    }

    private static Map<Integer, Integer> getLadders() {
        final Map<Integer, Integer> ladders;
        ladders = new HashMap<>();
        ladders.put(1, 38);
        ladders.put(4, 14);
        ladders.put(8, 30);
        ladders.put(21, 42);
        ladders.put(50, 67);
        ladders.put(71, 92);
        ladders.put(80, 99);
        return ladders;
    }


}
