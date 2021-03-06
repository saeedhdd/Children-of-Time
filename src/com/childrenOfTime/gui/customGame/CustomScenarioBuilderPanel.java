package com.childrenOfTime.gui.customGame;

import com.childrenOfTime.cgd.CustomGameDAO;
import com.childrenOfTime.gui.customizedElements.*;
import com.childrenOfTime.gui.fillForms.BlockPickerDialog;
import com.childrenOfTime.model.ChildrenOfTime;
import com.childrenOfTime.utilities.GUIUtils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by mohammadmahdi on 7/8/16.
 */
public class CustomScenarioBuilderPanel extends MenuScreenPanel {

    public static final int NUMBER_OF_MAP_ROWS = 11;
    public static final int NUMBER_OF_MAP_COLUMNS = 14;
    public static final int MAP_CELL_DIMENTION = 50;
    public static final int BORDER_GAP = 15;


    public Scenario newScenario;

    @Override
    public void initialize() {

        newScenario = new Scenario();

        ScenarioHolder scenarioHolder = new ScenarioHolder(this);

        scenarioHolder.setBorder(BorderFactory.createEmptyBorder(BORDER_GAP,BORDER_GAP, BORDER_GAP,BORDER_GAP));
//        scenarioHolder.setLocation(CustomScenarioBuilderPanel.BORDER_GAP,CustomScenarioBuilderPanel.BORDER_GAP);
        Dimension scenarioPanelDimention = scenarioHolder.getDimension();
        Dimension rightPanelDimention = new Dimension((int) (ChildrenOfTime.PREFERRED_WIDTH - scenarioPanelDimention.getWidth()),
                ChildrenOfTime.PREFERRED_HEIGHT);
        this.setLayout(new BorderLayout());
        this.add(scenarioHolder,BorderLayout.WEST);
        MenuScreenPanel_RightSide rightPanel = new MenuScreenPanel_RightSide(rightPanelDimention, this);
        this.add(rightPanel,BorderLayout.EAST);
        emerge();
    }

    public static void cellClicked(ScenarioCell targetCell) {
        new BlockPickerDialog(targetCell);
    }
}

class MenuScreenPanel_RightSide extends MenuScreenPanel {

    private JLabel userNameLabel = new CustomizedJLabel("");
    private JLabel userAvatar = new CustomizedJImage();

    Dimension dimension;
    CustomScenarioBuilderPanel parent;


    public MenuScreenPanel_RightSide(Dimension dimension, CustomScenarioBuilderPanel parent) {

        this.parent = parent;
        this.dimension = dimension;
        this.setLayout(null);
        this.setPreferredSize(dimension);
        this.initialize();

        if(CustomGameDAO.getCurrentUser() != null) {

            userAvatar = new CustomizedJImage("src/user_data/" + CustomGameDAO.getCurrentUser().getUserName()
                    + "/avatar.png", MenuScreenPanel.PREFFERED_AVATAR_SIZE, MenuScreenPanel.PREFFERED_AVATAR_SIZE);
            userNameLabel.setText(CustomGameDAO.getCurrentUser().getUserName());
            userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
            userAvatar.setLocation((int) (dimension.getWidth() - userAvatar.getWidth() / 2 - MenuScreenPanel.ELEMENT_GAP - userNameLabel.getWidth() / 2),
                    MenuScreenPanel.ELEMENT_GAP);
            userNameLabel.setLocation((int) (dimension.getWidth() - CustomizedJLabel.LABEL_WIDTH - MenuScreenPanel.ELEMENT_GAP),
                    userAvatar.getHeight() + MenuScreenPanel.ELEMENT_GAP);
        }

        this.add(userNameLabel);
        this.add(userAvatar);

        initializeForm();

    }

    private void initializeForm() {
        JButton save = new CustomizedJButton("Save Scenario");
        JButton cancel = new CustomizedJButton("Discard Changes");

        cancel.setBackground(Color.red);
        cancel.setForeground(Color.yellow);

        add(cancel);
        add(save);

        cancel.setLocation((int) (dimension.getWidth() - MenuScreenPanel.ELEMENT_GAP - CustomizedJButton.BUTTON_WIDTH),
                (int) (dimension.getHeight() - CustomizedJButton.BUTTON_HEIGHT - MenuScreenPanel.ELEMENT_GAP));
        save.setLocation((int) (dimension.getWidth() - MenuScreenPanel.ELEMENT_GAP - CustomizedJButton.BUTTON_WIDTH),
                (int) (dimension.getHeight() - 2*CustomizedJButton.BUTTON_HEIGHT - 2*MenuScreenPanel.ELEMENT_GAP));

        addSamples((int) (dimension.getWidth() - MenuScreenPanel.ELEMENT_GAP), (int) (dimension.getHeight() - 2*CustomizedJButton.BUTTON_HEIGHT - 2*MenuScreenPanel.ELEMENT_GAP));

        cancel.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(this,"Are you sure you want to discard changes?","Warning",JOptionPane.YES_NO_OPTION);
            switch (choice) {
                case JOptionPane.YES_OPTION:
                    ChildrenOfTime.changeContentPane(new CusomGameEditorMenu());
                    break;
                case JOptionPane.NO_OPTION:
                    break;
            }
        });
        save.addActionListener(e -> {

            String newScenarioName = JOptionPane.showInputDialog(this, "Please specify a name for this scenario", "new scenario");
            parent.newScenario.setName(newScenarioName);

            GUIUtils.deserializeUserFiles();
            CustomGameDAO.currentUserCustomScenarios.add(parent.newScenario);
            try {
                GUIUtils.serializeUserObject(CustomGameDAO.currentUserCustomScenarios, "scenarios");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Custom Scenario \"" + parent.newScenario.getName() + "\" saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            ChildrenOfTime.changeContentPane(new CusomGameEditorMenu());
        });
    }

    @Override
    public void initialize() {

    }

    private void addSamples(int initialX, int initialY) {
//        ScenarioCell groundSample = new ScenarioCell("ground1",0,0);
//        ScenarioCell wallSample = new ScenarioCell("wall",0,0);
//        ScenarioCell storeSample = new ScenarioCell("store",0,0);
//        ScenarioCell upgradeSample = new ScenarioCell("upgrade",0,0);
//        ScenarioCell doorSample = new ScenarioCell("door_down_up",0,0);
//        ScenarioCell storySample = new ScenarioCell("story",0,0);
//        ScenarioCell battleSample = new ScenarioCell("battle",0,0);
//        ScenarioCell bossSample = new ScenarioCell("boss",0,0);
//
//        int sampleDimention = ScenarioCell.SCENARIO_CELL_DIMENTION;
//        int gap = MenuScreenPanel.ELEMENT_GAP;
//
//        groundSample.setLocation(initialX - sampleDimention, initialY - sampleDimention);
//        wallSample.setLocation(initialX - 2*sampleDimention - gap, initialY - sampleDimention);
//        storeSample.setLocation(initialX - 3*sampleDimention - gap, initialY - sampleDimention);
//
//        this.add(groundSample);
//        this.add(wallSample);
//        this.add(storeSample);

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(ChildrenOfTime.GREY);
        g2d.fillRect(0,0,(int)(dimension.getWidth()),(int)(dimension.getHeight()));

    }

}
