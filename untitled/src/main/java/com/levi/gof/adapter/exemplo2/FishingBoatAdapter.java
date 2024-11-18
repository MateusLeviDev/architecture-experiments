package com.levi.gof.adapter.exemplo2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FishingBoatAdapter implements RowingBoat {
    private final FishingBoat boat;

    public FishingBoatAdapter() {
        boat = new FishingBoat();
    }

    @Override
    public void row() {
        boat.sail();;
    }
}
