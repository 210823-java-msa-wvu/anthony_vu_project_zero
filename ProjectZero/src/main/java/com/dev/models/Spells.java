package com.dev.models;

public class Spells {
    private Integer id;
    private String spellName;
    private String spellType;
    private String spellRace;

    public Spells(){

    }

    public Spells(String spellName, String spellType, String spellRace){
        this.spellName = spellName;
        this.spellType = spellType;
        this.spellRace = spellRace;
    }

    public Spells(Integer id, String spellName, String spellType, String spellRace){
        this.id = id;
        this.spellName = spellName;
        this.spellType = spellType;
        this.spellRace = spellRace;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpellName() {
        return spellName;
    }

    public void setSpellName(String spellName) {
        this.spellName = spellName;
    }

    public String getSpellType() {
        return spellType;
    }

    public void setSpellType(String spellType) {
        this.spellType = spellType;
    }

    public String getSpellRace() {
        return spellRace;
    }

    public void setSpellRace(String spellRace) {
        this.spellRace = spellRace;
    }

    @Override
    public String toString() {
        return "Spells{" +
                "id=" + id +
                ", spellName='" + spellName + '\'' +
                ", spellType='" + spellType + '\'' +
                ", spellRace='" + spellRace + '\'' +
                '}';
    }
}
