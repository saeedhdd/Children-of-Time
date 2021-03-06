package com.childrenOfTime.gui.customGame;

import com.childrenOfTime.cgd.CustomGameDAO;
import com.childrenOfTime.gui.customizedElements.CustomizedJButton;
import com.childrenOfTime.gui.customizedElements.CustomizedJImage;
import com.childrenOfTime.gui.customizedElements.MenuScreenPanel;
import com.childrenOfTime.gui.fillForms.*;
import com.childrenOfTime.gui.fillForms.dataHolders.*;
import com.childrenOfTime.model.*;
import com.childrenOfTime.model.Equip.AbilComps.Ability;
import com.childrenOfTime.model.Equip.AbilComps.Upgrade;
import com.childrenOfTime.model.Equip.AlterPackage;
import com.childrenOfTime.model.Equip.Effect;
import com.childrenOfTime.model.Equip.EffectType;
import com.childrenOfTime.model.Equip.ItemComps.Item;
import com.childrenOfTime.model.Equip.ItemComps.ItemType;
import com.childrenOfTime.model.Equip.ItemComps.Messages;
import com.childrenOfTime.model.Warriors.HeroClass;
import com.childrenOfTime.model.Warriors.Warrior;
import com.childrenOfTime.utilities.GUIUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mohammadmahdi on 7/8/16.
 */
public class CusomGameEditorMenu extends MenuScreenPanel {


    @Override
    public void initialize() {


        JButton customEffect = new CustomizedJButton("new Effect");
        JButton customStore = new CustomizedJButton("new Store");
        JButton customStory = new CustomizedJButton("new Story");
        JButton customScenario = new CustomizedJButton("new Scenario");
        JButton customBattle = new CustomizedJButton("new Battle");
        JButton customWarriorClass = new CustomizedJButton("new WarriorClass");
        JButton customWarrior = new CustomizedJButton("new Warrior");
        JButton customItem   = new CustomizedJButton("new Item");
        JButton customAbility = new CustomizedJButton("new Ability");
        JButton back = new CustomizedJButton("Back to CG Hub");

        back.setBackground(Color.red);
        back.setForeground(Color.yellow);

        this.add(back);
        this.add(customAbility);
        this.add(customItem);
        this.add(customWarrior);
        this.add(customWarriorClass);
        this.add(customBattle);
        this.add(customScenario);
        this.add(customStory);
        this.add(customStore);
        this.add(customEffect);

        back.setLocation(ELEMENT_GAP, ChildrenOfTime.PREFERRED_HEIGHT - CustomizedJButton.BUTTON_HEIGHT - ELEMENT_GAP);
        customItem.setLocation(ELEMENT_GAP, ChildrenOfTime.PREFERRED_HEIGHT - 2*CustomizedJButton.BUTTON_HEIGHT - 2*ELEMENT_GAP);
        customAbility.setLocation(ELEMENT_GAP, ChildrenOfTime.PREFERRED_HEIGHT - 3*CustomizedJButton.BUTTON_HEIGHT - 3*ELEMENT_GAP);
        customWarrior.setLocation(ELEMENT_GAP, ChildrenOfTime.PREFERRED_HEIGHT - 4*CustomizedJButton.BUTTON_HEIGHT - 4*ELEMENT_GAP);
        customWarriorClass.setLocation(ELEMENT_GAP, ChildrenOfTime.PREFERRED_HEIGHT - 5*CustomizedJButton.BUTTON_HEIGHT - 5*ELEMENT_GAP);
        customStory.setLocation(2 * ELEMENT_GAP + CustomizedJButton.BUTTON_WIDTH, ChildrenOfTime.PREFERRED_HEIGHT - CustomizedJButton.BUTTON_HEIGHT - ELEMENT_GAP);
        customStore.setLocation(2*ELEMENT_GAP + CustomizedJButton.BUTTON_WIDTH, ChildrenOfTime.PREFERRED_HEIGHT - 2*CustomizedJButton.BUTTON_HEIGHT - 2*ELEMENT_GAP);
        customEffect.setLocation(2*ELEMENT_GAP + CustomizedJButton.BUTTON_WIDTH, ChildrenOfTime.PREFERRED_HEIGHT - 3*CustomizedJButton.BUTTON_HEIGHT - 3*ELEMENT_GAP);
        customBattle.setLocation(2*ELEMENT_GAP + CustomizedJButton.BUTTON_WIDTH, ChildrenOfTime.PREFERRED_HEIGHT - 4*CustomizedJButton.BUTTON_HEIGHT - 4*ELEMENT_GAP);
        customScenario.setLocation(2*ELEMENT_GAP + CustomizedJButton.BUTTON_WIDTH, ChildrenOfTime.PREFERRED_HEIGHT - 5*CustomizedJButton.BUTTON_HEIGHT - 5*ELEMENT_GAP);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChildrenOfTime.changeContentPane(new CustomGameUserHubPanel());
            }
        });
        customScenario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fade();
                ChildrenOfTime.changeContentPane(new CustomScenarioBuilderPanel());
            }
        });
        customAbility.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CusomGameEditorMenu.this.fade();
                AbilityDataHolder dataHolder = new AbilityDataHolder();
                new NewAbilityCreationDialog(dataHolder);

                Messages abilityMessages = new Messages(dataHolder.description, dataHolder.successMessage,
                        dataHolder.EPFailiureMessage, dataHolder.MPFailiureMessage, dataHolder.cooldownFailiureMessage,
                        dataHolder.notAcqiredFailiureMessage);

                ImageIcon icon = GUIUtils.getIConByFilePath(dataHolder.imagePath);

                AbilityMaker abilityMaker = new AbilityMaker();

                abilityMaker.newCustomAbility(dataHolder.abilityName, dataHolder.targetType,
                        abilityMessages, icon, dataHolder.power);
                if (!dataHolder.upgrades.isEmpty()) {
                    for (Upgrade upgrade : dataHolder.upgrades) {
                        abilityMaker.addCustomUpgrade(upgrade);
                        if (upgrade.getUpgradeBoolean()) abilityMaker.setBaseUpgrade(upgrade.getNumberOfUpgrade());
                    }
                }

                Ability createdAbility = abilityMaker.returnAbility();
                GUIUtils.deserializeUserFiles();
                CustomGameDAO.currentUserCustomAbilities.add(createdAbility);
                try {
                    GUIUtils.serializeUserObject(CustomGameDAO.currentUserCustomAbilities, "abilities");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        customItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CusomGameEditorMenu.this.fade();
                ItemDataHolder dataHolder = new ItemDataHolder();
                new NewItemCreationDialog(dataHolder);

                ItemType type = new ItemType(dataHolder.hasInflation, dataHolder.isReusable, dataHolder.autoUseAfterPurchse,
                        dataHolder.placableInInventory, dataHolder.removeFromInventoryAfterUsageLimit, dataHolder.wearOffEffectsAfterSelling,
                        dataHolder.hasCooldown, dataHolder.cooldownTurns, dataHolder.initialPrice, dataHolder.reusableTimes, dataHolder.inflationRate);

                Messages messages = new Messages(dataHolder.description, dataHolder.successMessage, dataHolder.EPFailiureMessage,
                        dataHolder.MPFailiureMessage, dataHolder.cooldownFailiureMessage, dataHolder.notAcqiredFailiureMessage);

                Integer delta[] = {dataHolder.APIncrement, dataHolder.HRRIncrement, dataHolder.MPIncrement, dataHolder.MMPIncrement,
                        dataHolder.EPIncrement, dataHolder.HRRIncrement, dataHolder.MMRRIncrement};
                Double factor[] = {dataHolder.APCoefficient, dataHolder.HCoefficient, dataHolder.MPCoefficient, dataHolder.MMPCoefficient,
                        dataHolder.EPCoefficient, dataHolder.HRRCoefficient, dataHolder.MMRRCoefficient};

                AlterPackage sideCost = new AlterPackage(delta, factor);

                ImageIcon icon = GUIUtils.getIConByFilePath(dataHolder.imagePath);  //do not use imageFilePath.
                Item createdItem = new Item(dataHolder.itemName, type, messages, dataHolder.targetType, dataHolder.effects, sideCost, icon);

                GUIUtils.deserializeUserFiles();
                CustomGameDAO.currentUserCustomItems.add(createdItem);

                try {
                    GUIUtils.serializeUserObject(CustomGameDAO.currentUserCustomItems, "items");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        customEffect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CusomGameEditorMenu.this.fade();
                EffectDataHolder dataHolder = new EffectDataHolder();

                new NewEffectCreationDialog(true, dataHolder);

                Integer delta[] = {dataHolder.APIncrement, dataHolder.HIncrement, dataHolder.MHIncrement, dataHolder.HRRIncrement, dataHolder.MPIncrement, dataHolder.MMPIncrement,
                        dataHolder.MMRRIncrement, dataHolder.EPIncrement};
                Double factor[] = {dataHolder.APCoefficient, dataHolder.HCoefficient, dataHolder.MHCoefficient, dataHolder.HRRCoefficient, dataHolder.MPCoefficient, dataHolder.MMPCoefficient,
                        dataHolder.MMRRCoefficient, dataHolder.EPCoefficient};


                Effect createdEffect = new Effect(dataHolder.name, new EffectType(dataHolder.automaticTargetSelection, dataHolder.applyUponAttack,
                        dataHolder.autoRepeatable, !dataHolder.temporaryEffect, dataHolder.indefiniteExcecution,
                        dataHolder.wearOffEffectsAfterExcecution), new AlterPackage(delta, factor,
                        dataHolder.indefiniteExcecutionPercent), dataHolder.TargetType, dataHolder.indefiniteExcecutionPercent,
                        dataHolder.temporaryEffectTurnCount, dataHolder.autoRepeatableTurnCount);
                GUIUtils.deserializeUserFiles();
                CustomGameDAO.currentUserCustomEffects.add(createdEffect);
                try {
                    GUIUtils.serializeUserObject(CustomGameDAO.currentUserCustomEffects, "effects");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        customStore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CusomGameEditorMenu.this.fade();
                StoreDataHolder dataHolder = new StoreDataHolder();
                new NewStoreCreationDialog(dataHolder);
                Map<Item, Integer> storeMappesItems = new HashMap<>();
                for (Item storeItem : dataHolder.storeItems) {
                    storeMappesItems.put(storeItem, dataHolder.storeItems.indexOf(storeItem));
                }
                Store createdStore = new Store(storeMappesItems, dataHolder.acceptsBargains,
                        dataHolder.canInflatePrices, dataHolder.inflationRate, dataHolder.storeName);
                GUIUtils.deserializeUserFiles();
                CustomGameDAO.currentUserCustomStores.add(createdStore);
                try {
                    GUIUtils.serializeUserObject(CustomGameDAO.currentUserCustomStores, "stores");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        customWarriorClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CusomGameEditorMenu.this.fade();
                WarriorClassDataHolder dataHolder = new WarriorClassDataHolder();
                new NewWarriorClassCreationDialog(dataHolder);
                int heroBurnEnergyRange[] = {dataHolder.burnEPRangeStart, dataHolder.burnEPRangeEnd};
                HeroClass createdWarriorClass = new HeroClass(dataHolder.canAttack, dataHolder.healthRelatedAP,
                        dataHolder.APInHighHealthLevel, dataHolder.APInLowHealthLevel, dataHolder.mutationHealthLimit,
                        dataHolder.canBurnEP, heroBurnEnergyRange, dataHolder.EPCostForPerformer, dataHolder.mutationMessage,
                        dataHolder.EPBurningMessage, dataHolder.MPChangeSystem, dataHolder.EPChangeSystem, dataHolder.canBuyItems,
                        dataHolder.CanUseImmortalityPotion, dataHolder.CanUseRefilSystemFeatures, dataHolder.ActionMessage,
                        dataHolder.dyingMessage, dataHolder.name, dataHolder.AI, dataHolder.maxH, dataHolder.HRR, dataHolder.maxM,
                        dataHolder.MRR, dataHolder.turnInitialEP, dataHolder.inventorySize, dataHolder.description, dataHolder.backStory,
                        dataHolder.warriorClassAbilities);
                GUIUtils.deserializeUserFiles();
                CustomGameDAO.currentUserCustomWarriorClasses.add(createdWarriorClass);
                try {
                    GUIUtils.serializeUserObject(CustomGameDAO.currentUserCustomWarriorClasses, "warriorClasses");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        customWarrior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CusomGameEditorMenu.this.fade();
                WarriorDataHolder dataHolder = new WarriorDataHolder();
                new NewWarriorCreationDialog(dataHolder);
                if (!dataHolder.operationCancelled) {
                    ImageIcon icon = GUIUtils.getIConByFilePath(dataHolder.imageFilePath);
                    Warrior createdWarrior = new Warrior(dataHolder.name, dataHolder.warriorClass, dataHolder.specificAbilities, icon);
                    GUIUtils.deserializeUserFiles();
                    CustomGameDAO.currentUserCustomWarriors.add(createdWarrior);
                    try {
                        GUIUtils.serializeUserObject(CustomGameDAO.currentUserCustomWarriors, "warriors");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        customBattle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CusomGameEditorMenu.this.fade();
                BattleDataHolder dataHolder = new BattleDataHolder();
                new NewBattleCreationDialog(dataHolder);

                Reward battleReward = new Reward(dataHolder.rewardXP, dataHolder.rewardMoney, dataHolder.rewardImmortalityPotion);
                Battle createdBattle = new Battle(dataHolder.name, dataHolder.defeatStory, battleReward, dataHolder.battleFoes);
                GUIUtils.deserializeUserFiles();
                CustomGameDAO.currentUserCustomBattles.add(createdBattle);
                try {
                    GUIUtils.serializeUserObject(CustomGameDAO.currentUserCustomBattles, "battles");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });
        customStory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CusomGameEditorMenu.this.fade();
                new NewStoryCreationDialog();
            }
        });

        CustomizedJImage mainMenuArt = new CustomizedJImage("src/ui/Children Of Time Art Assets/COT (33).png", 200, 200);
        this.add(mainMenuArt);
        mainMenuArt.setLocation(ChildrenOfTime.PREFERRED_WIDTH - 200 - ELEMENT_GAP, ChildrenOfTime.PREFERRED_HEIGHT - 200 - ELEMENT_GAP);

        emerge();
    }
}
