package com.drunkensoftball.api.roster.domain


enum class FieldPosition private constructor(var id: Int) {

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


    companion object {

        fun getByString(fieldPositionString: String?): FieldPosition? {
            if (fieldPositionString.isNullOrEmpty()) {
                return null
            }

            for (fieldPosition in FieldPosition.values()) {
                if (fieldPosition.name == fieldPositionString) {
                    return fieldPosition
                }
            }

            throw RuntimeException("Field Position Not Found")
        }
    }

}
