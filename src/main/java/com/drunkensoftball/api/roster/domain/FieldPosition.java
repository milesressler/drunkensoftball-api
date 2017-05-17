package com.drunkensoftball.api.roster.domain;

public enum FieldPosition {

    catcher(0),
    first_base(1),
    second_base(2),
    third_base(3),
    short_stop(4),
    pitcher(5),
    infield(9),
    outfield(10),
    left_field(11),
    left_centerfield(12),
    centerfield(13),
    right_centerfield(14),
    right_field(15),
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
