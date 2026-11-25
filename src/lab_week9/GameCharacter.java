/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab_week9;

/**
 *
 * @author melih
 */
public abstract class GameCharacter {
    private String name;
    private int level;
    private int health;
    private int maxHealth;

    public GameCharacter(String name, int level, int maxHealth) {
        this.name = name;

        if (level < 1 || level > 100) {
            System.out.println("Warning: Invalid level for " + name + ". Setting level to 1.");
            this.level = 1;
        } else {
            this.level = level;
        }

        if (maxHealth <= 0) {
            System.out.println("Warning: Invalid maxHealth for " + name + ". Setting maxHealth to 100.");
            this.maxHealth = 100;
        } else {
            this.maxHealth = maxHealth;
        }

        this.health = this.maxHealth;
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public int takeDamage(int damage) {
        if (damage <= 0 || !isAlive()) {
            return 0;
        }
        int actualDamage = Math.min(this.health, damage);
        this.health -= actualDamage;
        return actualDamage;
    }

    public int restoreHealth(int amount) {
        if (!isAlive() || amount <= 0) {
            return 0;
        }
        int actualRestored = Math.min(this.maxHealth - this.health, amount);
        this.health += actualRestored;
        return actualRestored;
    }

    public abstract String getCharacterInfo();
    public abstract int calculatePower();

    // Getter ve Setter metotları
    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }
}
