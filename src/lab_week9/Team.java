/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab_week9;

/**
 *
 * @author melih
 */
import java.util.ArrayList;

public class Team {
    private ArrayList<GameCharacter> members;

    public Team() {
        this.members = new ArrayList<>();
    }

    public boolean addCharacter(GameCharacter character) {
        if (character == null) {
            return false;
        }
        if (findByName(character.getName()) != null) {
            System.out.println("Error: A character with the name '" + character.getName() + "' is already in the team.");
            return false;
        }
        members.add(character);
        return true;
    }

    public boolean removeCharacter(String name) {
        GameCharacter target = findByName(name);
        if (target != null) {
            members.remove(target);
            System.out.println(name + " has been removed from the team.");
            return true;
        }
        System.out.println("Character '" + name + "' not found in the team.");
        return false;
    }

    public GameCharacter findByName(String name) {
        for (GameCharacter member : members) {
            if (member.getName().equalsIgnoreCase(name)) {
                return member;
            }
        }
        return null;
    }

    public void attackAll(GameCharacter target) {
        if (target == null || !target.isAlive()) {
            System.out.println("Target is already defeated or invalid.");
            return;
        }
        for (GameCharacter member : members) {
            if (member.isAlive() && member instanceof Attacker) {
                Attacker attacker = (Attacker) member;
                int dealt = attacker.attack(target);
                System.out.println(member.getName() + " attacks " + target.getName() + " for " + dealt + " damage!");
                if (!target.isAlive()) {
                    System.out.println(target.getName() + " has been defeated!");
                    break;
                }
            }
        }
    }

    public void healTeam() {
        for (GameCharacter member : members) {
            if (member.isAlive() && member instanceof Supporter) {
                GameCharacter lowestHealthMember = getLowestHealthRatioMember();
                if (lowestHealthMember != null) {
                    Supporter supporter = (Supporter) member;
                    int healed = supporter.heal(lowestHealthMember);
                    if (healed > 0) {
                        System.out.println(member.getName() + " heals " + lowestHealthMember.getName() + " for " + healed + " HP!");
                    }
                }
            }
        }
    }

    private GameCharacter getLowestHealthRatioMember() {
        GameCharacter lowest = null;
        double minRatio = Double.MAX_VALUE;

        for (GameCharacter member : members) {
            if (member.isAlive()) {
                double ratio = (double) member.getHealth() / member.getMaxHealth();
                if (ratio < minRatio) {
                    minRatio = ratio;
                    lowest = member;
                }
            }
        }
        return lowest;
    }

    public int getTotalPower() {
        int totalPower = 0;
        for (GameCharacter member : members) {
            if (member.isAlive()) {
                totalPower += member.calculatePower();
            }
        }
        return totalPower;
    }

    public void displayTeam() {
        System.out.println("=== TEAM ROSTER (Total Alive Power: " + getTotalPower() + ") ===");
        for (GameCharacter member : members) {
            String status = member.isAlive() ? "ALIVE" : "DEFEATED";
            System.out.println(member.getCharacterInfo() + " [" + status + "]");
        }
        System.out.println("================================================");
    }
}