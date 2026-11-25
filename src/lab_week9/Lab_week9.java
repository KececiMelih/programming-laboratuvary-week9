/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab_week9;

/**
 *
 * @author melih
 */
public class Lab_week9 {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        System.out.println("--- 1. TESTING INVALID INPUTS ---");
        // Geçersiz seviye (150) ve geçersiz maxHealth (-50) testi
        Warrior invalidChar = new Warrior("TestDummy", 150, -50, 10);
        System.out.println(invalidChar.getCharacterInfo());

        Team heroTeam = new Team();
        Warrior warrior = new Warrior("Aragorn", 12, 200, 20);
        Mage mage = new Mage("Gandalf", 15, 130, 50);
        Healer healer = new Healer("Elrond", 10, 120, 25);

        heroTeam.addCharacter(warrior);
        heroTeam.addCharacter(mage);
        heroTeam.addCharacter(healer);

        // Aynı isimle karakter ekleme testi
        Warrior duplicateWarrior = new Warrior("Aragorn", 5, 100, 10);
        heroTeam.addCharacter(duplicateWarrior);

        System.out.println("\n--- 2. INITIAL TEAM STATUS ---");
        heroTeam.displayTeam();

        // Güçlü düşman oluşturma
        Warrior boss = new Warrior("Golem", 20, 450, 30);
        System.out.println("\nEnemy Appears: " + boss.getCharacterInfo());

        // En az 2 rauntluk savaş simülasyonu
        for (int round = 1; round <= 3; round++) {
            System.out.println("\n================ ROUND " + round + " ================");
            
            // Takım düşmana saldırır
            System.out.println(">> Team attacks " + boss.getName() + ":");
            heroTeam.attackAll(boss);
            
            if (!boss.isAlive()) {
                break;
            }

            // Düşman takım üyelerine saldırır
            System.out.println("\n>> " + boss.getName() + " counter-attacks:");
            int dmgToWarrior = boss.attack(warrior);
            System.out.println(boss.getName() + " hits " + warrior.getName() + " for " + dmgToWarrior + " damage!");
            
            int dmgToMage = boss.attack(mage);
            System.out.println(boss.getName() + " hits " + mage.getName() + " for " + dmgToMage + " damage!");

            // Takım kendini iyileştirir (En düşük can oranına sahip üyeyi bulup iyileştirir)
            System.out.println("\n>> Team heals lowest health ratio members:");
            heroTeam.healTeam();

            System.out.println("\n>> End of Round " + round + " Status:");
            System.out.println(boss.getCharacterInfo());
            heroTeam.displayTeam();
        }

        System.out.println("\n--- 3. SEARCH AND REMOVE TESTS ---");
        GameCharacter found = heroTeam.findByName("Gandalf");
        System.out.println("Search result for 'Gandalf': " + (found != null ? found.getCharacterInfo() : "Not found"));

        heroTeam.removeCharacter("Elrond");
        heroTeam.displayTeam();
    }
}
    

