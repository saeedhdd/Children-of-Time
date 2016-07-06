package com.childrenOfTime.model;

/**
 * Created by SaeedHD on 07/06/2016.
 */
class PermanentEffects extends Effects {

    Integer giveAttackPowerPermanently = 0;
    Double factorAttackPowerPermanently = YEK_DOUBLE;
    Integer giveMaxMagicPermanently = 0;

    public PermanentEffects() {
    }

    public PermanentEffects(Integer giveAttackPowerPermanently, Double factorAttackPowerPermanently, Integer giveMaxMagicPermanently) {
        this.giveAttackPowerPermanently = giveAttackPowerPermanently;
        this.factorAttackPowerPermanently = factorAttackPowerPermanently;
        this.giveMaxMagicPermanently = giveMaxMagicPermanently;
    }

    @Override
    public void perform(Hero performer, Warrior... target_s) {
        if (giveAttackPowerPermanently != null || giveAttackPowerPermanently != 0) {
            for (Warrior target : target_s) {
                target.changeAttackPower(this.giveAttackPowerPermanently);
            }
        }
        if (factorAttackPowerPermanently != null || factorAttackPowerPermanently != YEK_DOUBLE) {
            for (Warrior target : target_s) {
                int newAttackpower = (int) (target.getAttackPower() * this.factorAttackPowerPermanently);
                target.setAttackPower(newAttackpower);
            }
        }

        if (giveMaxMagicPermanently != null || giveMaxMagicPermanently != 0)
            for (Warrior target : target_s) {
                if (target instanceof Hero)
                    ((Hero) target).changeMaxMagic(this.giveMaxMagicPermanently);
            }
    }

    public void setGiveAttackPowerPermanently(Integer giveAttackPowerPermanently) {
        this.giveAttackPowerPermanently = giveAttackPowerPermanently;
    }

    public void setFactorAttackPowerPermanently(Double factorAttackPowerPermanently) {
        this.factorAttackPowerPermanently = factorAttackPowerPermanently;
    }

    public void setGiveMaxMagicPermanently(Integer giveMaxMagicPermanently) {
        this.giveMaxMagicPermanently = giveMaxMagicPermanently;
    }
}
