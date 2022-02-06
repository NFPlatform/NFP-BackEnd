package com.nfplatform.nfpbackend.auction;

public enum AuctionStatus {
    SELL("SELL"),
    SOLD("SOLD");

    private String text;

    AuctionStatus(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }
}
