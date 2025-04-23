package com.example.harjoitusty;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Random;

@Entity
public class Lutemon {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int attack;
    private int defense;
    private int maxHp;
    private int experience;

    // Uudet kentät
    private int currentHealth;
    private int totalBattles;
    private int wins;

    public Lutemon(String name, int attack, int defense, int maxHp) {
        this.name          = name;
        this.attack        = attack;
        this.defense       = defense;
        this.maxHp         = maxHp;
        this.experience    = 0;
        this.currentHealth = maxHp;
        this.totalBattles  = 0;
        this.wins          = 0;
    }

    // Getterit ja setterit Roomille

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public int getAttack() { return attack; }

    public int getDefense() { return defense; }

    public int getMaxHp() { return maxHp; }

    public int getExperience() { return experience; }
    public void setExperience(int experience) { this.experience = experience; }

    public int getCurrentHealth() { return currentHealth; }
    public void setCurrentHealth(int currentHealth) { this.currentHealth = currentHealth; }

    public int getTotalBattles() { return totalBattles; }
    public void setTotalBattles(int totalBattles) { this.totalBattles = totalBattles; }

    public int getWins() { return wins; }
    public void setWins(int wins) { this.wins = wins; }

    /**
     * Simuloitu sopiva hyökkäysarvo (attack + 0–3 satunnainen bonus)
     */
    public int getRandomAttack() {
        return attack + new Random().nextInt(4);
    }
}
