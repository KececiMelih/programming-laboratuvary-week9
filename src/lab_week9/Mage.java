/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab_week9;

/**
 *
 * @author melih
 */
public class Mage extends GameCharacter implements Attacker, Supporter {
    private int mana;

    public Mage(String name, int level, int maxHealth, int mana) {
        super(name, level, maxHealth);
        this.mana = Math.max(0, mana);
    }

    @Override
    public int calculatePower() {
        return getLevel() * 8 + mana;
    }

    @Override
    public int attack(GameCharacter target) {
        if (!this.isAlive() || target == null || !target.isAlive()) {
            return 0;
        }
        if (this.mana < 20) {
            System.out.println(getName() + " does not have enough mana to attack!");
            return 0;
        }
        int currentPower = calculatePower(); // Mana harcanmadan önce güç hesaplanır
        this.mana -= 20;
        int damage = currentPower / 3;
        return target.takeDamage(damage);
    }

    @Override
    public int heal(GameCharacter target) {
        if (!this.isAlive() || target == null || !target.isAlive()) {
            return 0;
        }
        if (this.mana < 15) {
            System.out.println(getName() + " does not have enough mana to heal!");
            return 0;
        }
        this.mana -= 15;
        int healAmount = getLevel() * 2;
        return target.restoreHealth(healAmount);
    }

    @Override
    public String getCharacterInfo() {
        return String.format("[Mage] Name: %s | Level: %d | HP: %d/%d | Mana: %d | Power: %d",
                getName(), getLevel(), getHealth(), getMaxHealth(), mana, calculatePower());
    }

    public int getMana() {
        return mana;
    }
}
