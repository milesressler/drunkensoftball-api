package com.drunkensoftball.api.bat.domain;

public class BatRequest {

    private String gameUuid;
    private String rosterUuid;
    private String atBatResult;
    private String uniqueId;

    public String getGameUuid() {
        return gameUuid;
    }

    public String getRosterUuid() {
        return rosterUuid;
    }

    public String getAtBatResult() {
        return atBatResult;
    }

    public String getUniqueId() {
        return uniqueId;
    }
}
