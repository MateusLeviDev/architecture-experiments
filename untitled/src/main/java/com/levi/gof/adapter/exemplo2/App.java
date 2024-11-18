package com.levi.gof.adapter.exemplo2;

public class App {
    public static void main(String[] args) {
        final var captain = new Captain(new FishingBoatAdapter());
        captain.row();
    }
}
