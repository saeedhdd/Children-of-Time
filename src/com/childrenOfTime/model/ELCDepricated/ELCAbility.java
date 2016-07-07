package com.childrenOfTime.model.ELCDepricated;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
@Deprecated
public class ELCAbility {
}
//
//    EndlessCollectionInformations info;
//    protected int currentLevel = 0;
//    protected int leftTurnsToCoolDown;
//    protected boolean isInCoolDown = false;
//
//
//    @Completed
//    public ELCAbility(String name) {
//        String firstWord = name.split(" ")[0];
//        this.info = EndlessCollectionInformations.valueOf(firstWord);
//        this.leftTurnsToCoolDown = this.info.coolDownTime;
//
//    }
//
//
//    @Completed
//    public boolean isFullyUpgraded() {
//        return currentLevel == 3;
//    }
//
//    public void acqiure() {
//        this.currentLevel = 1;
//    }
//
//    @InProgress
//    public void cast(Warrior performer, Warrior... warrior) throws AttackException {
//        if (this.currentLevel == 0) {
//            throw new AbilityNotAquiredException("Your " + performer.name + performer.id + " hero, doesn't have this ability (" +
//                    info.name + ")");
//        }
//        Hero hero = (Hero) performer;
//
//        switch (EndlessCollectionInformations.valueOf(this.info.name.split(" ")[0])) {
//            case Fight:
//                FightTraining(hero);
//                break;
//            case Work:
//                WorkOut(hero);
//                break;
//            case Quick:
//                QuickAsABunny(hero);
//                break;
//            case Magic:
//                MagicLessons(hero);
//                break;
//            case Overpowered:
//                OverpoweredAttack(hero, (Foe) warrior[0]);
//                break;
//            case Swirling:
//                SwirlingAttack(hero);
//                break;
//            case Sacrifice:
//                Sacrifice(hero);
//                break;
//            case Critical:
//                CriticalStrike(hero);
//                break;
//            case Elixir:
//                Elixir(hero, (Hero) warrior[0]);
//                break;
//            case Caretaker:
//                CareTaker(hero, (Hero) warrior[0]);
//                break;
//            case Boost:
//                Boost(hero, (Hero) warrior[0]);
//                break;
//            case Mana:
//                ManaBeam(hero, (Hero) warrior[0]);
//                break;
//        }
//    }
//
//
//    @Completed
//    public Integer upgrade(Warrior upgrader, Integer i) {
//        Hero hero = (Hero) upgrader;
//        info.setUpgradeRequirements(hero);
//        if (i - currentLevel == 1) {
//            switch (i) {
//                case 1:
//                    if (!info.upgradeRequirement2)
//                        throw new RequirementsNotMetException();
//                    currentLevel = i;
//                    break;
//                case 2:
//                    if (!info.upgradeRequirement3)
//                        throw new RequirementsNotMetException();
//                    currentLevel = i;
//                    break;
//                case 3:
//                    throw new AbilityMaxLevelReachedException("“This ability cannot be upgraded anymore”");
//            }
//        } else throw new RequirementsNotMetException();
//
//        String aqORup = "";
//        if (this.currentLevel > 1) {
//            aqORup = "upgraded";
//        }
//        //printOutput(this.info.name + " " + aqORup + " successfully, your current experience is: " + player.getCurrentExperience());
//        switch (i) {
//            case 1:
//                return info.xp1;
//            case 2:
//                return info.xp2;
//            case 3:
//                return info.xp3;
//        }
//        return null;
//    }
//
//
//
//
//    switch (currentLevel) {
//            case 0:
//                throw new AbilityNotAquiredException();
//            case 1 :
//                player.changeCurrentExperience(-2);
//        }
//

//    @Completed
//    private void FightTraining(Hero hero) throws NotEnoughXPException {
//        for (int i = 0; i <= currentLevel; i++) {
//            hero.attackPower += 30;
//        }
//    }
//
//    @Completed
//    private void WorkOut(Hero hero) {
//
//        for (int i = 0; i <= currentLevel; i++) {
//        hero.maxHealth += 50;
//        }
//    }
//
//    @Completed
//    private void MagicLessons(Hero hero) {
//        for (int i = 0; i <= currentLevel; i++) {
//            hero.changeMaxMagic(50);
//        }
//    }
//
//
//    @Completed
//    private void QuickAsABunny(Hero hero) {
//        for (int i = 0; i <= currentLevel; i++) {
//            hero.changeEP(1);
//        }
//    }
//
//    @Completed
//    private void OverpoweredAttack(Hero hero, Warrior foe) throws AttackException {
//        hero.changeEP(-2);
//        try {
//            hero.changeMagic(-50);
//        } catch (NotEnoughMagicPointsException e) {
//            hero.changeEP(2);
//            throw new NotEnoughMagicPointsException("Your " + hero.name + hero.id + " hero doesn't have Enough MP to" +
//                    " perform this move\ncurrent MP : " + hero.currentMagic + "\nrequired MP : " + 50 + "\nYou need " +
//                    (50 - hero.currentMagic) + " additional Mps.");
//        }
//
//        hero.attack(foe, (int) (hero.attackPower * (1 + 0.2 * currentLevel)), 0);
//        printOutput("Eley just did an overpowered attack on " +
//                foe + " with " + 1 + 0.2 * currentLevel + " damage");
//    }
//
//    @Completed
//    private void SwirlingAttack(Hero hero) {
//        hero.swirlingisActivated = true;
//        hero.damagePercent = 0.1 * currentLevel;
//    }
//
//    @InProgress
//    private void Sacrifice(Hero hero, Warrior... targets) throws AttackException {
//        hero.changeEP(-3);
//        try {
//            hero.changeMagic(-60);
//        } catch (NotEnoughMagicPointsException e) {
//            hero.changeEP(3);
//        }
//        int H = 0;
//        if (this.isInCoolDown) throw new AbilityInCooldownException("Abiliy is in cooldown");
//        switch (currentLevel) {
//            case 1:
//                H = 40;
//                break;
//            case 2:
//                H = 50;
//                break;
//            case 3:
//                H = 60;
//                break;
//
//        }
//
//        for (Warrior target : targets) {
//            target.changeHealth(-3 * H);
//            hero.changeHealth(-H);
//        }
//        printOutput("Chrome just sacrificed himself to damage all his enemies with " + 3 * H + " power");
//    }
//
//    @InProgress
//    private void CriticalStrike(Hero hero) {
//        hero.criticalIsActivated = true;
//        hero.probability = 10 * (1 + currentLevel);
//
//    }
//
//    @Completed
//    private void Elixir(Hero hero, Warrior hero2) throws AttackException {
//        hero.changeEP(-2);
//        try {
//            hero.changeMagic(-60);
//        } catch (NotEnoughMagicPointsException e) {
//            hero.changeEP(2);
//            throw new NotEnoughMagicPointsException("Your " + hero.name + hero.id + " hero doesn't have Enough MP to" +
//                    " perform this move\ncurrent MP : " + hero.currentMagic + "\nrequired MP : " + 60 + "\nYou need " +
//                    (60 - hero.currentMagic) + " additional Mps.");
//        }
//        int h = 0;
//        switch (this.currentLevel) {
//            case 1:
//                if (isInCoolDown)
//                    throw new AbilityInCooldownException("This ability (" + this.info.name + ")is  in Cooldown");
//                hero2.changeHealth(100);
//                h = 100;
//                isInCoolDown = true;
//                break;
//            case 2:
//                if (isInCoolDown)
//                    throw new AbilityInCooldownException("This ability (" + this.info.name + ")is  in Cooldown");
//                hero2.changeHealth(150);
//                h = 150;
//                isInCoolDown = true;
//                break;
//            case 3:
//                h = 150;
//                hero2.changeHealth(150);
//                break;
//        }
//        printOutput("Meryl just healed " + hero2 + " with " + h + " health points");
//    }
//
//    @Completed
//    private void CareTaker(Hero hero, Hero hero2) throws AttackException {
//        if (!hero2.equals(hero)) {
//            hero.changeMagic(-30);
//            hero2.changeEP(1);
//            switch (this.currentLevel) {
//                case 1:
//                    if (this.isInCoolDown) {
//                        hero2.changeEP(-1);  //TODO we have to check this logically
//                        hero.changeMagic(30);
//                        throw new AbilityInCooldownException("This ability (" + this.info.name + ")is  in Cooldown");
//                    }
//                    //break is not needed
//                case 2:
//                    try {
//                        hero.changeEP(-2);
//                    } catch (NotEnoughEnergyPointsException e) {
//                        hero.changeMagic(30);
//                        hero2.changeEP(-1);
//                        throw new NotEnoughEnergyPointsException("Your " + hero.name + hero.id + " hero doesn't have Enough EP to" +
//                                " perform this move\ncurrent EP : " + hero.currentEnergyPoints + "\nrequired EP : " + 30 + "\nYou need " +
//                                (30 - hero.currentEnergyPoints) + " additional Eps.");
//                    }
//                    isInCoolDown = true;
//                    break;
//                case 3:
//                    try {
//                        hero.changeEP(-1);
//                    } catch (NotEnoughEnergyPointsException e) {
//                        hero.changeMagic(30);
//                        hero2.changeEP(-1);
//                        throw new NotEnoughEnergyPointsException("Your " + hero.name + hero.id + " hero doesn't have Enough MP to" +
//                                " perform this move\ncurrent MP : " + hero.currentMagic + "\nrequired MP : " + 30 + "\nYou need " +
//                                (30 - hero.currentMagic) + " additional Mps.");
//                    }
//                    break;
//
//            }
//
//            printOutput("Meryl just gave " + hero2 + " 1 energy point");
//        }
//    }
//
//    @Completed
//    private void Boost(Hero hero, Hero hero2) throws AttackException {
//        hero.changeEP(-2);
//        try {
//            hero.changeMagic(-50);
//        } catch (NotEnoughMagicPointsException e) {
//            hero.changeEP(2);
//            throw new NotEnoughMagicPointsException("Your " + hero.name + hero.id + " hero doesn't have Enough MP to" +
//                    " perform this move\ncurrent MP : " + hero.currentMagic + "\nrequired MP : " + 50 + "\nYou need " +
//                    (50 - hero.currentMagic) + " additional Mps.");
//        }
//        int a = 0;
//        switch (this.currentLevel) {
//            case 1:
//                if (this.isInCoolDown)
//                    throw new AbilityInCooldownException("This ability (" + this.info.name + ")is  in Cooldown");
//                hero2.changeAttackPower(20);
//                isInCoolDown = true;
//                a = 20;
//                break;
//            case 2:
//                if (this.isInCoolDown)
//                    throw new AbilityInCooldownException("This ability (" + this.info.name + ")is  in Cooldown");
//                hero2.changeAttackPower(30);
//                isInCoolDown = true;
//                a = 30;
//                break;
//            case 3:
//                hero2.changeAttackPower(30);
//                a = 30;
//                break;
//        }
//        printOutput("Bolti just boosted " + hero2 + " with " + a + " power");
//    }
//
//    @Completed
//    private void ManaBeam(Hero hero, Hero hero2) throws AttackException {
//        hero.changeEP(-1);
//        try {
//            hero.changeMagic(-50);
//        } catch (NotEnoughMagicPointsException e) {
//            hero.changeEP(1);
//            throw new NotEnoughMagicPointsException("Your " + hero.name + hero.id + " hero doesn't have Enough MP to" +
//                    " perform this move\ncurrent MP : " + hero.currentMagic + "\nrequired MP : " + 50 + "\nYou need " +
//                    (50 - hero.currentMagic) + " additional Mps.");
//        }
//        int m = 0;
//        switch (this.currentLevel) {
//            case 1:
//                if (this.isInCoolDown)
//                    throw new AbilityInCooldownException("This ability (" + this.info.name + ")is  in Cooldown");
//                hero2.changeMagic(50);
//                isInCoolDown = true;
//                m = 50;
//                break;
//            case 2:
//                if (this.isInCoolDown)
//                    throw new AbilityInCooldownException("This ability (" + this.info.name + ")is  in Cooldown");
//                hero2.changeMagic(80);
//                isInCoolDown = true;
//                m = 80;
//                break;
//            case 3:
//                hero2.changeMagic(80);
//                m = 80;
//                break;
//        }
//        printOutput("Bolti just helped " + hero2 + " with " + m + " magic points");
//    }
//
//
//    public String getSuccessMessage() {
//        //TODO implement
//        return null;
//    }
//
//    public int getCurrentLevel() {
//        return currentLevel;
//    }
//
//    @Completed
//    public void showDescription() {
//        this.info.setDescription();
//        printOutput(this.info.description);
//    }
//
//
//    @Override
//    public boolean equals(Object obj) {
//        if (obj == null || obj.getClass() != this.getClass()) return false;
//        Ability other = (Ability) obj;
//        if (!this.info.name.equals(other.info.name)) return false;
//        return true;
//    }


//
//
//
//    @Override
//    public void aTurnHasPassed() {
//        if (!isInCoolDown) return;
//        if (leftTurnsToCoolDown == 1) {
//            this.isInCoolDown = false;
//            leftTurnsToCoolDown = this.info.coolDownTime;
//        } else {
//            leftTurnsToCoolDown--;
//        }
//    }
//}
//