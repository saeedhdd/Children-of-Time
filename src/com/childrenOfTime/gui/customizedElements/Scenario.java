package com.childrenOfTime.gui.customizedElements;

import com.childrenOfTime.gui.customGame.CustomScenarioBuilderPanel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mohammadmahdi on 7/11/16.
 */
public class Scenario implements Serializable {

    private String name;
    private ArrayList<ScenarioCell> map = new ArrayList<>();


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void addAnewCell(ScenarioCell cell) {
        map.add(cell);
    }
    public ScenarioCell getIJ(int i, int j) {
        int index = CustomScenarioBuilderPanel.NUMBER_OF_MAP_COLUMNS * j + i;
        return map.get(index);
    }
}
