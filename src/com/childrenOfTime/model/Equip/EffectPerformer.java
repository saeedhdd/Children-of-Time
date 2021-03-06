package com.childrenOfTime.model.Equip;

import com.childrenOfTime.model.Warriors.Warrior;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by SaeedHD on 07/09/2016.
 */
public class EffectPerformer implements Serializable {

    private static boolean doesPassiveAllowsToContinue(Effect effect, Warrior performer) {
        if (effect.getEffectType().isPassive()) {
            performer.addPassiveEffect(effect);
            return false;
        }
        return true;
    }

    //TODO anotherCheck required
    public static void performEffects(boolean CalledByAttack, Collection<Effect> effects, Warrior performer, Warrior[] selectedTargets, Warrior[] allEnemies, Warrior[] allTeamMates) {
        if (effects == null) return;
        if (effects.size() == 0) return;
        Iterator<Effect> itr = effects.iterator();
        Warrior[] finalTargets;

        while (itr.hasNext()) {
            Effect nextEffect = itr.next();
            if (!CalledByAttack && !doesPassiveAllowsToContinue(nextEffect, performer)) return;
            finalTargets = chooseTargts(nextEffect, performer, selectedTargets, allEnemies, allTeamMates);

            nextEffect.perform(finalTargets);

        }
    }


    //TODO anotherCheck required
    public static void wearOffEffects(Collection<Effect> effects, Warrior performer, Warrior[] selectedTargets, Warrior[] allEnemies, Warrior[] allTeamMates) {
        Iterator<Effect> itr = effects.iterator();
        Warrior[] finalTargets;

        while (itr.hasNext()) {
            Effect nextEffect = itr.next();
            if (nextEffect.effectType.isPassive()) {
                if (performer.containsPassiveEffect(nextEffect)) {
                    performer.getPassiveEffects().remove(nextEffect);
                }
            }
            finalTargets = chooseTargts(nextEffect, performer, selectedTargets, allEnemies, allTeamMates);
            nextEffect.wearOff(finalTargets);

        }
    }




    private static Warrior[] chooseTargts(Effect eff, Warrior performer, Warrior[] selectedTargets, Warrior[] allEnemies, Warrior[] allTeamMates) {
        Warrior[] newTargets = null;
        switch (eff.getTargetType()) {
            case HimSelf:
                newTargets = new Warrior[1];
                newTargets[0] = performer;
                break;
            case AllEnemies:
                newTargets = allEnemies;
                break;
            case AllTeammates:
                newTargets = allTeamMates;
                break;
            case SingleEnemy:
            case SeveralEnemies:
            case theAttackedOne:
            case SeveralTeamMates:
                newTargets = selectedTargets;
                break;

        }
        return newTargets;
    }
}


/**
 * public static void performEffects(List<Effect> effects, @NotNull Warrior performer, @Nullable Warrior[] target_s, @Nullable Warrior... implicitTargets){
 * Iterator<Effect> itr = effects.iterator();
 * Warrior[] finalTargets = target_s;
 * while (itr.hasNext()) {
 * //TODO anotherCheck required
 * Effect nextEffect = itr.next();
 * if (nextEffect.getEffectType().isTargetUnChoosable() && implicitTargets != null) {
 * finalTargets = implicitTargets;
 * }
 * if (finalTargets != null) {
 * <p>
 * nextEffect.perform(finalTargets);
 * } else throw new RuntimeException("no Targets Selected");
 * if (nextEffect.getEffectType().isPassive()) {
 * itr.remove();
 * }
 * }
 * <p>
 * }
 */


