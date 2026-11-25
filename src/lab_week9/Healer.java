/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab_week9;

/**
 *
 * @author melih
 */
public class Healer extends GameCharacter implements Supporter {
    private int healAmount;

    public Healer(String name, int level, int maxHealth, int healAmount) {
        super(name, level, maxHealth);
        this.healAmount = Math.max(0, healAmount);
    }

    @Override
    public int calculatePower() {
        return getLevel() * 5 + healAmount;
    }

    @Override
    public int heal(GameCharacter target) {
        if (!this.isAlive() || target == null || !target.isAlive()) {
            return 0;
        }
        int amount = healAmount + getLevel();
        return target.restoreHealth(amount);
    }

    @Override
    public String getCharacterInfo() {
        return String.format("[Healer] Name: %s | Level: %d | HP: %d/%d | HealAmount: %d | Power: %d",
                getName(), getLevel(), getHealth(), getMaxHealth(), healAmount, calculatePower());
    }

    public int getHealAmount() {
        return healAmount;
    }
}
