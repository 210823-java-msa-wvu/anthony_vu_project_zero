package com.dev.models;

public class Monster {

    private Integer id;
    private String monsterName;
    private String monsterRace;
    private String monsterType;

    public Monster(){

    }

    public Monster(String monsterName, String monsterRace, String monsterType){
        this.monsterName = monsterName;
        this.monsterRace = monsterRace;
        this.monsterType = monsterType;
    }

    public Monster(Integer id, String monsterName, String monsterRace, String monsterType){
        this.id = id;
        this.monsterName = monsterName;
        this.monsterRace = monsterRace;
        this.monsterType = monsterType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMonsterName() {
        return monsterName;
    }

    public void setMonsterName(String monsterName) {
        this.monsterName = monsterName;
    }

    public String getMonsterRace() {
        return monsterRace;
    }

    public void setMonsterRace(String monsterRace) {
        this.monsterRace = monsterRace;
    }

    public String getMonsterType() {
        return monsterType;
    }

    public void setMonsterType(String monsterType) {
        this.monsterType = monsterType;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "id=" + id +
                ", monsterName='" + monsterName + '\'' +
                ", monsterRace='" + monsterRace + '\'' +
                ", monsterType='" + monsterType + '\'' +
                '}';
    }
}
