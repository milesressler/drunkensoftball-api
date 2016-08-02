package com.drunkensoftball.api.roster.domain;

public enum FieldPosition {

    catcher(0),
    first_base(1),
    second_base(2),
    third_base(3),
    short_stop(5),
    pitcher(4),
    left_field(6),
    centerfield(7),
    right_field(8),
    outfield(10),
    left_centerfield(11),
    right_centerfield(12),
    infield(20),
    rover(30);

    public int id;

    FieldPosition(int id) {
        this.id = id;
    }

    public static FieldPosition getByString(String fieldPositionString){
        for (FieldPosition fieldPosition : FieldPosition.values()){
            if (fieldPosition.name().equals(fieldPositionString)){
                return  fieldPosition;
            }
        }
        throw new RuntimeException("Field Position Not Found");
    }

}
