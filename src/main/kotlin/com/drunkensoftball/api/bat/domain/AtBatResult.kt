package com.drunkensoftball.api.bat.domain


enum class AtBatResult private constructor(var id: Int) {

    hit_single(1),
    hit_double(2),
    hit_triple(3),
    hit_home_run(4),
    general_out (10),
    strikeout(11),
    pop_fly(12),
    tag_out(13),
    force_out(14),
    walk(20),
    hit_by_pitch(21),
    fielders_choice(22);


    companion object {

        fun getByString(atBatResultString: String): AtBatResult {
            for (atBatResult in AtBatResult.values()) {
                if (atBatResult.name == atBatResultString) {
                    return atBatResult
                }
            }
            throw RuntimeException("At-Bat Result Not Found")
        }
    }
}
