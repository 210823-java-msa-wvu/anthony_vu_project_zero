package com.dev.models;

public class Deck {
    private Integer id;
    private String cardName;
    private String cardRace;
    private String cardType;

    public Deck(){

    }

    public Deck(String cardName, String cardRace, String cardType){
        this.cardName = cardName;
        this.cardRace = cardRace;
        this.cardType = cardType;
    }

    public Deck(Integer id, String cardName, String cardRace, String cardType){
        this.id = id;
        this.cardName = cardName;
        this.cardRace = cardRace;
        this.cardType = cardType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardRace() {
        return cardRace;
    }

    public void setCardRace(String cardRace) {
        this.cardRace = cardRace;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @Override
    public String toString() {
        return  "ID: " + id + " | " +
                "Card Name: " + cardName + " | " +
                "Card Race: " + cardRace + " | " +
                "Card Type: " + cardType;
    }
}
