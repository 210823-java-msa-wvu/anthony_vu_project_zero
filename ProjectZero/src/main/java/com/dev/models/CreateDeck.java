package com.dev.models;

public class CreateDeck {

    private Integer id;
    private String deckUser;
    private String deckName;

    public CreateDeck(){

    }

    public CreateDeck(String deckUser, String deckName){
        this.deckUser = deckUser;
        this.deckName = deckName;
    }

    public CreateDeck(Integer id, String deckUser, String deckName){
        this.id = id;
        this.deckUser = deckUser;
        this.deckName = deckName;
    }

    public CreateDeck(String deckUser) {
        this.deckUser = deckUser;
    }

    public CreateDeck(Integer id, String deckName) {
        this.id = id;
        this.deckName = deckName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public String getDeckUser() {
        return deckUser;
    }

    public void setDeckUser(String deckUser) {
        this.deckUser = deckUser;
    }

    @Override
    public String toString() {
        return "CreateDeck{" +
                "id=" + id +
                ", deckUser='" + deckUser + '\'' +
                ", deckName='" + deckName + '\'' +
                '}';
    }
}
