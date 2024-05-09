package com.example.Main.Scenario.Dto;

import java.util.List;

public class EnableScenarioRequest {
    private List<String> scenarioIds;

    public List<String> getScenarioIds() {
        return scenarioIds;
    }

    public void setScenarioIds(List<String> scenarioIds) {
        this.scenarioIds = scenarioIds;
    }
}
