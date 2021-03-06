package com.childrenOfTime.gui.singlePlayer;

import com.childrenOfTime.cgd.CustomGameDAO;
import com.childrenOfTime.gui.customGame.CustomScenarioBuilderPanel;
import com.childrenOfTime.gui.customizedElements.*;
import com.childrenOfTime.gui.customizedListeners.MapScreenListener;
import com.childrenOfTime.gui.fillForms.dataHolders.CustomScenarioInfoHolder;
import com.childrenOfTime.model.ChildrenOfTime;
import com.childrenOfTime.model.Player;
import com.childrenOfTime.model.PlayerType;
import com.childrenOfTime.model.Warriors.Warrior;
import com.childrenOfTime.utilities.GUIUtils;

import javax.swing.*;
import java.awt.*;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;

/**
 * Created by mohammadmahdi on 7/12/16.
 */
public class SinglePlayerGame extends MenuScreenPanel {


    private PlayerIndicator indicator;
    private MapScreenListener controller;
    public static SinglePlayerGame lastState;

    CustomizedJLabel currentWealth;
    CustomizedJLabel currentXp;

    private Player playingPlayer;

    CustomScenarioInfoHolder infoHolder;

    public SinglePlayerGame(CustomScenarioInfoHolder infoHolder) {

        lastState = this;
        playingPlayer = new Player(infoHolder.playerWarriors, CustomGameDAO.getCurrentUser().getUserName(), PlayerType.Human);
        this.infoHolder = infoHolder;
        indicator = new PlayerIndicator(infoHolder, playingPlayer);
        initializeCells();
        controller = new MapScreenListener(indicator, this);
        addKeyListener(controller);


        currentWealth = new CustomizedJLabel("Current Money: " + String.valueOf(playingPlayer.getCurrentWealth()) + "$");
        currentXp = new CustomizedJLabel("Current XP: " + String.valueOf(playingPlayer.getCurrentExperience()) + "XP");

        this.add(currentWealth);
        this.add(currentXp);


        currentWealth.setLocation(ELEMENT_GAP,
                ChildrenOfTime.PREFERRED_HEIGHT - CustomizedJLabel.LABEL_HEIGHT - ELEMENT_GAP);


        currentXp.setLocation(ELEMENT_GAP,
                ChildrenOfTime.PREFERRED_HEIGHT - 2 * CustomizedJLabel.LABEL_HEIGHT - 2 * ELEMENT_GAP);

    }
    private void initializeCells() {
        for (int i = 0; i < CustomScenarioBuilderPanel.NUMBER_OF_MAP_COLUMNS; i++) {
            for (int j = 0; j < CustomScenarioBuilderPanel.NUMBER_OF_MAP_ROWS; j++) {
                infoHolder.playingScenario.getIJ(i, j).setLocation(CustomScenarioBuilderPanel.BORDER_GAP + CustomScenarioBuilderPanel.MAP_CELL_DIMENTION * i,
                        CustomScenarioBuilderPanel.BORDER_GAP + CustomScenarioBuilderPanel.MAP_CELL_DIMENTION * j);
            }
        }
    }
    @Override
    public void initialize() {


        JButton saveGame = new CustomizedJButton("Save the game");
        JButton quit = new CustomizedJButton("Quit");

        quit.setBackground(Color.red);
        quit.setForeground(Color.yellow);

        this.add(quit);
        this.add(saveGame);

        quit.setLocation(ChildrenOfTime.PREFERRED_WIDTH - ELEMENT_GAP - CustomizedJButton.BUTTON_WIDTH,
                ChildrenOfTime.PREFERRED_HEIGHT - CustomizedJButton.BUTTON_HEIGHT - ELEMENT_GAP);
        saveGame.setLocation(ChildrenOfTime.PREFERRED_WIDTH - ELEMENT_GAP - CustomizedJButton.BUTTON_WIDTH,
                ChildrenOfTime.PREFERRED_HEIGHT - 2 * CustomizedJButton.BUTTON_HEIGHT - 2 * ELEMENT_GAP);

        quit.addActionListener(e -> ChildrenOfTime.changeContentPane(new SinglePlayerMenuScreenPanel()));
        emerge();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        currentWealth.setText("Current Money: " + String.valueOf(playingPlayer.getCurrentWealth()) + "$");
        currentXp.setText("Current XP: " + String.valueOf(playingPlayer.getCurrentExperience()) + "XP");

        Graphics2D g2d = (Graphics2D) g;


        AffineTransform newTransform = new AffineTransform();

        newTransform.translate(400, 50);
        newTransform.scale(1, 0.5);
        newTransform.rotate(Math.toRadians(45));

        AffineTransform undoTransform = new AffineTransform();
        undoTransform.rotate(Math.toRadians(-45));
        undoTransform.scale(1, 2);
        undoTransform.translate(-400, -50);

        g2d.transform(newTransform);


        paintMap(g2d);
        paintIndicator(g2d);


        g2d.transform(undoTransform);
    }

    private void paintMap(Graphics2D g2d) {

        for (int i = 0; i < CustomScenarioBuilderPanel.NUMBER_OF_MAP_COLUMNS; i++) {
            for (int j = 0; j < CustomScenarioBuilderPanel.NUMBER_OF_MAP_ROWS; j++) {
                g2d.drawImage(GUIUtils.iconToImage(GUIUtils.getScaledIcon(infoHolder.playingScenario.getIJ(i, j).getIcon(), ScenarioCell.SCENARIO_CELL_DIMENTION, ScenarioCell.SCENARIO_CELL_DIMENTION, 0)),
                        infoHolder.playingScenario.getIJ(i, j).getX(), infoHolder.playingScenario.getIJ(i, j).getY(), this);
            }
        }
    }

    private void paintIndicator(Graphics2D g2d) {


        g2d.setColor(Color.red);

        g2d.fillRoundRect(indicator.getX(), indicator.getY(), PlayerIndicator.PLAYER_INDICATOR_DIAMETER
                , PlayerIndicator.PLAYER_INDICATOR_DIAMETER, PlayerIndicator.PLAYER_INDICATOR_DIAMETER,
                PlayerIndicator.PLAYER_INDICATOR_DIAMETER);


    }


}
