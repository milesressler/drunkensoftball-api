package com.drunkensoftball.api.bat.domain;

public enum AtBatResult {

    hit_single(1),
    hit_double(2),
    hit_triple(3),
    hit_home_run(4),
    out(10),
    strikeout(11),
    pop_fly(12),
    tag_out(13),
    force_out(14),
    walk(20),
    hit_by_pitch(21),
    fielders_choice(22)
    ;

    public int id;

    AtBatResult(int id) {
        this.id = id;
    }

    public static AtBatResult getByString(String atBatResultString){
        for (AtBatResult atBatResult : AtBatResult.values()){
            if (atBatResult.name().equals(atBatResultString)){
                return  atBatResult;
            }
        }
        throw new RuntimeException("At-Bat Result Not Found");
    }
}
