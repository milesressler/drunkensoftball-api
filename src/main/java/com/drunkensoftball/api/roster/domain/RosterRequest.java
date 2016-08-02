package com.drunkensoftball.api.roster.domain;

public class RosterRequest {

    private String displayName;
    private String teamUuid;
    private Integer battingPosition;
    private String fieldPosition;

    public String getTeamUuid() {
        return teamUuid;
    }

    public void setTeamUuid(String teamUuid) {
        this.teamUuid = teamUuid;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getFieldPosition() {
        return fieldPosition;
    }

    public void setFieldPosition(String fieldPosition) {
        this.fieldPosition = fieldPosition;
    }

    public Integer getBattingPosition() {
        return battingPosition;
    }

    public void setBattingPosition(Integer battingPosition) {
        this.battingPosition = battingPosition;
    }
}
