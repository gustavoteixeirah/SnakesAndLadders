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
        this.snakes = new HashMap<>();
        snakes.put(36, 6);
        snakes.put(32, 10);
        snakes.put(62, 18);
        snakes.put(88, 24);
        snakes.put(48, 26);
        snakes.put(95, 56);
        snakes.put(97, 78);

        this.ladders = new HashMap<>();
        ladders.put(1, 38);
        ladders.put(4, 14);
        ladders.put(8, 30);
        ladders.put(21, 42);
        ladders.put(50, 67);
        ladders.put(71, 92);
        ladders.put(80, 99);
    }


    public void movePlayers() {
        int positionsToMove = ThreadLocalRandom.current().nextInt(1, 6);
        System.out.println("Client rolled: " + positionsToMove);
        this.client += positionsToMove;

        if (this.client >= 100) {
            System.out.println("Client wins the game!");
            resetPositions();
        }

        ladders.computeIfPresent(this.client, (k, v) -> this.client = ladders.get(k));
        snakes.computeIfPresent(this.client, (k, v) -> this.client = snakes.get(k));


        positionsToMove = ThreadLocalRandom.current().nextInt(1, 6);
        System.out.println("Server rolled: " + positionsToMove);
        this.server += positionsToMove;

        if (this.server >= 100) {
            System.out.println("Server wins the game!");
            resetPositions();
        }

        ladders.computeIfPresent(this.server, (k, v) -> this.server = ladders.get(k));
        snakes.computeIfPresent(this.server, (k, v) -> this.server = snakes.get(k));
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
}
