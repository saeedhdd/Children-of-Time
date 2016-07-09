package com.childrenOfTime.model;

import com.childrenOfTime.model.Equip.AbilComps.Ability;
import com.childrenOfTime.model.Equip.AbilComps.Upgrade;
import com.childrenOfTime.model.Equip.Effects;
import com.childrenOfTime.model.Equip.ItemComps.Messages;
import com.childrenOfTime.model.Equip.Target;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.ArrayList;

/**
 * Created by SaeedHD on 07/06/2016.
 */
public class AbilityMaker {
    private Ability ability;

    public void newCustomAbility(@NotNull String name, @NotNull Target targetType, @Nullable String Description, @Nullable String SuccessMessage) {
        ability = new Ability(name, Description, SuccessMessage, new BST<Upgrade>(), targetType, Ability.DEFAUL_AbilityImage);

    }

    @Deprecated
    public void addCustomUpgrade(int numberOfUpgrade, Integer COOLDOWN_TIME, int XPCost, int masrafEP, int masrafMP, Messages messages, String... upgradeCondition) {
        Upgrade newUpgrade = new Upgrade(numberOfUpgrade, messages, COOLDOWN_TIME, XPCost, masrafEP, masrafMP, upgradeCondition);
        ability.getUpgrades().add(newUpgrade);
        newUpgrade.setEffects(new ArrayList<Effects>(1));
        ability = ability;

    }

    public void addCustomUpgrade(@NotNull Integer numberOfUpgrade, @Nullable Messages messages, @Nullable Integer COOLDOWN_TIME, @Nullable Integer XPCost, @Nullable Integer masrafEP, @Nullable Integer masrafMP, @Nullable Boolean castJustAfterAcquire, @Nullable Boolean reastableForUser, @Nullable String... upgradeCondition) {
        Upgrade newUpgrade = new Upgrade(numberOfUpgrade, messages, COOLDOWN_TIME, XPCost, masrafEP, masrafMP, reastableForUser, castJustAfterAcquire, upgradeCondition);
        ability.getUpgrades().add(newUpgrade);
        newUpgrade.setEffects(new ArrayList<Effects>(1));
    }

    public void setBaseUpgrade(Integer upgradeNumber) {
        ability.setBaseState(getUpgradeByNumber(upgradeNumber));
    }

    private Upgrade getUpgradeByNumber(int i) {
        return ability.getUpgradeByNumber(i);
    }
/*
    @Deprecated
    public void addCustomDurableEffect(Integer upgradeNumber, Double factorAttackPower_WithAttack_Amount, int factorAttackPower_WithAttack_Duration, Double factorAttackPower_WithoutAttack_Amount,
                                       int factorAttackPower_WithoutAttack_Duration, int giveMagicPoints_Amount, int giveMagicPoints_Duration, int giveHealth_amount, int giveHealth_Duration, int giveEP_amount, int giveEP_Duration) {
        Upgrade upgrade = getUpgradeByNumber(upgradeNumber);
        if (upgrade == null) return;
        Effects effect = new DuarbleEffects(factorAttackPower_WithAttack_Amount, factorAttackPower_WithAttack_Duration, factorAttackPower_WithoutAttack_Amount,
                factorAttackPower_WithoutAttack_Duration, giveMagicPoints_Amount, giveMagicPoints_Duration, giveHealth_amount, giveHealth_Duration, giveEP_amount, giveEP_Duration);
        upgrade.addEffect(effect);
        ability = ability;
    }

    @Deprecated
    public void addCustomPermanentEffect(Integer upgradeNumber, Integer giveAttackPowerPermanently, Double factorAttackPowerPermanently, Integer giveMaxMagicPermanently) {
        Upgrade upgrade = getUpgradeByNumber(upgradeNumber);
        if (upgrade == null) return;
        Effects effect = new PermanentEffects(giveAttackPowerPermanently, factorAttackPowerPermanently, giveMaxMagicPermanently);
        upgrade.addEffect(effect);
        ability = ability;
    }

    @Deprecated
    public void newCustomExtraAbilities(String name, String SuccessMessage, String Description, String[] UpgradeDescriptons, Boolean swirlingHeal, Integer[] UpgradeNumbers, Integer[] swirlingHealPercents, Boolean swirlingAttack1, Integer[] swirlingAttackPercents, Boolean crticalAttack, Double[] crticalFactor, Integer[] criticalProbabiliy, int[] EPCost, int[] MPCost, int[] XPCosts, String[] requirements) {
        ability = new ExtraAbility(name, SuccessMessage, Description, UpgradeDescriptons, swirlingHeal, UpgradeNumbers, swirlingHealPercents, swirlingAttack1, swirlingAttackPercents, crticalAttack, crticalFactor, criticalProbabiliy, EPCost, MPCost, XPCosts, requirements);
    }
*/

    public void addCustomEffect(Integer upgradeNumber, Effects effect) {
        Upgrade upgrade = getUpgradeByNumber(upgradeNumber);
        if (upgrade == null) return;
        upgrade.addEffect(effect);
    }


    public Ability returnAbility() {
        Ability ab = ability;
        ability = null;
        return ab;
    }

/*
    public static void newEndlessCollectionAbility(String name) {
        try {
            elcAbility = new ELCAbility(name);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
*/
}
