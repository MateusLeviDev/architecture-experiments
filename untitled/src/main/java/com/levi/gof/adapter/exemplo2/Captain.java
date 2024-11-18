package com.levi.gof.adapter.exemplo2;

public class Captain {
    private final RowingBoat rowingBoat;

    public Captain(final RowingBoat rowingBoat) {
        this.rowingBoat = rowingBoat;
    }

    public void row() {
        rowingBoat.row();
    }
}
