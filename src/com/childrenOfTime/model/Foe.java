package com.childrenOfTime.model;

import com.childrenOfTime.Completed;
import com.childrenOfTime.NotImplementedYet;

import java.util.ArrayList;
import java.util.Random;

import static com.childrenOfTime.view.IOHandler.printOutput;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public class Foe extends Warrior {
    protected int healingAmount;
    TypesOfFoes type;
    StrengthOfFoes strength;


    //Just For Final Boss
    int attackPowerInHighHealth;
    int attackPowerInLowHealth;
    int heroAttackingNumberPerTurn;
    int[] heroBurningEnergy;
    String description;

    @Completed
    public Foe(String name, StrengthOfFoes strength, int id) {
        super(name, id);
        type = TypesOfFoes.valueOf(name.split(" ")[0]);
        this.strength = strength;
        type.setStrength(this.strength);

        this.healingAmount = type.healingAmount;
        super.maxHealth = type.maximumHealth;
        super.attackPower = type.attackPower;
        this.heroBurningEnergy = type.heroBurningEnergy;
        this.heroAttackingNumberPerTurn = type.heroAttackingNumberPerTurn;
        this.attackPowerInHighHealth = type.attackPowerInHighHealth;
        this.attackPowerInLowHealth = type.attackPowerInLowHealth;
        this.description = type.description;

        super.currentHealth = maxHealth;

    }


    @Completed
    void updateFinalBoss() {
        if (this.name.equals("Final Boss")) {
            if (currentHealth <= 400) {
                strength = StrengthOfFoes.Mutated;
                attackPower = attackPowerInLowHealth;
                printOutput(WarriorMessages.getMutationMessageForFinalBoss(this));

            } else {
                strength = StrengthOfFoes.Dramatic;
                attackPower = attackPowerInHighHealth;
            }
        }
    }


    @Completed
    public void Act1(ChildrenOfTime game) {
        ArrayList<Object> targets = new ArrayList();
        Warrior singleTarget;
        switch (name) {
            case "Thug":
                singleTarget = this.findTarget(game);
                singleTarget.changeHealth(-attackPower);
                printOutput(WarriorMessages.getAction_1_MessageForFoe(this, singleTarget));

                break;
            case "Angel":
                singleTarget = this.findFoeTarget(game);
                singleTarget.changeHealth(+this.healingAmount);
                printOutput(WarriorMessages.getAction_1_MessageForFoe(this, singleTarget));

                break;
            case "Tank":
                for (Warrior w : ChildrenOfTime.getInstance().getPlayers().get(0).getHeros()) {
                    ((Hero) w).changeHealth(-attackPower);
                    printOutput(WarriorMessages.getAction_1_MessageForFoe(this, w));
                }
                break;
            case "Final Boss":
                singleTarget = this.findTarget(game);
                updateFinalBoss();
                singleTarget.changeHealth(-attackPower);
                printOutput(WarriorMessages.getAction_1_MessageForFoe(this, singleTarget));

                break;
        }

    }

    @Completed
    public void Act2(ChildrenOfTime game) {
        Warrior singleTarget;
        switch (name) {
            case "Final Boss":
                singleTarget = this.findTarget(game);
                Hero h = (Hero) singleTarget;
                h.setCurrentEnergyPoints(h.getCurrentEnergyPoints() + this.heroBurningEnergy[1]);
                printOutput(WarriorMessages.getAction_2_MessageForFoe(this, singleTarget));

                break;

        }

    }

    @NotImplementedYet
    private Warrior findTarget(ChildrenOfTime game) {
        Random rand = new Random();
        ArrayList<Hero> allHeroes = ChildrenOfTime.getInstance().getPlayers().get(0).getHeros();
        int n = rand.nextInt(allHeroes.size());
        return allHeroes.get(n);
    }

    private Foe findFoeTarget(ChildrenOfTime game) {
        Random rand = new Random();
        ArrayList<Foe> allFoe = Battle.getFoes();
        int n = rand.nextInt(allFoe.size());
        return allFoe.get(n);
    }


    @Override
    public String toString() {

        return getName() + " " + getId();
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Foe other = (Foe) obj;
        if (!this.name.equals(other.name)) return false;
        return true;
    }

    @Override
    public String showCurrentTraits() {
        String toReturn = "";
        toReturn += "Health: " + currentHealth + "/" + maxHealth;
        toReturn += "\nAttack power: " + this.attackPower;
        toReturn += "\nHealing amount: " + this.healingAmount + "HP per Turn";

        return toReturn;
    }

}

