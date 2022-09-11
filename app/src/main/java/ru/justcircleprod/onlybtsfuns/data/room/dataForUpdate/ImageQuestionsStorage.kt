package ru.justcircleprod.onlybtsfuns.data.room.dataForUpdate

import ru.justcircleprod.onlybtsfuns.data.models.ImageQuestion

object ImageQuestionsStorage {
    fun getQuestions(): List<ImageQuestion> {
        return listOf(
            ImageQuestion(
                156,
                "chimmy_1",
                "Cooky", "Mang",
                "Tata", "Chimmy",
                4, 600
            ), ImageQuestion(
                157,
                "chimmy_2",
                "Chimmy", "Van",
                "Shooky", "Koya",
                1, 600
            ), ImageQuestion(
                158,
                "cooky_1",
                "Koya", "Cooky",
                "Van", "Mang",
                2, 600
            ), ImageQuestion(
                159,
                "cooky_2",
                "Shooky", "Rj",
                "Cooky", "Chimmy",
                3, 600
            ), ImageQuestion(
                160,
                "koya_1",
                "Rj", "Cooky",
                "Mang", "Koya",
                4, 600
            ), ImageQuestion(
                161,
                "koya_2",
                "Koya", "Chimmy",
                "Tata", "Cooky",
                1, 600
            ), ImageQuestion(
                162,
                "mang_1",
                "Shooky", "Mang",
                "Chimmy", "Koya",
                2, 600
            ), ImageQuestion(
                163,
                "mang_2",
                "Chimmy", "Tata",
                "Mang", "Rj",
                3, 600
            ), ImageQuestion(
                164,
                "rj_1",
                "Shooky", "Van",
                "Tata", "Rj",
                4, 600
            ), ImageQuestion(
                165,
                "rj_2",
                "Rj", "Chimmy",
                "Van", "Koya",
                1, 600
            ), ImageQuestion(
                166,
                "shooky_1",
                "Cooky", "Shooky",
                "Chimmy", "Tata",
                2, 600
            ), ImageQuestion(
                167,
                "shooky_2",
                "Cooky", "Mang",
                "Shooky", "Rj",
                3, 600
            ), ImageQuestion(
                168,
                "tata_1",
                "Mang", "Cooky",
                "Chimmy", "Tata",
                4, 600
            ), ImageQuestion(
                169,
                "tata_2",
                "Tata", "Cooky",
                "Rj", "Koya",
                1, 600
            ), ImageQuestion(
                170,
                "van_1",
                "Shooky", "Van",
                "Mang", "Chimmy",
                2, 600
            ), ImageQuestion(
                171,
                "van_2",
                "Tata", "Cooky",
                "Van", "Chimmy",
                3, 600
            )
        )
    }

    fun getQuestionsFor23Migration(): List<ImageQuestion> {
        return listOf(
            ImageQuestion(
                172,
                "jung_ho_seok_20",
                "Suga", "J-Hope",
                "Jin", "Jimin",
                2, 400
            )
        )
    }

    fun getQuestionsFor34Migration(): List<ImageQuestion> {
        return listOf(
            ImageQuestion(
                173,
                "jeon_jung_kook_23",
                "V", "J-Hope",
                "Suga", "Jungkook",
                4, 350
            ),
            ImageQuestion(
                174,
                "jeon_jung_kook_24",
                "Jungkook", "Jin",
                "RM", "Jimin",
                1, 400
            ),
            ImageQuestion(
                175,
                "jeon_jung_kook_25",
                "Suga", "Jungkook",
                "V", "J-Hope",
                2, 500
            ),
            ImageQuestion(
                176,
                "jeon_jung_kook_26",
                "Jimin", "RM",
                "Jungkook", "Suga",
                3, 600
            ),
            ImageQuestion(
                177,
                "jung_ho_seok_21",
                "J-Hope", "Jimin",
                "Jin", "Jungkook",
                1, 400
            ),
            ImageQuestion(
                178,
                "jung_ho_seok_22",
                "RM", "J-Hope",
                "Jin", "Suga",
                2, 500
            ),
            ImageQuestion(
                179,
                "jung_ho_seok_23",
                "Jin", "Suga",
                "J-Hope", "V",
                3, 550
            ),
            ImageQuestion(
                180,
                "jung_ho_seok_24",
                "Jimin", "RM",
                "Jungkook", "J-Hope",
                4, 350
            ),
            ImageQuestion(
                181,
                "kim_nam_joon_22",
                "Suga", "Jin",
                "RM", "Jimin",
                3, 600
            ),
            ImageQuestion(
                182,
                "kim_nam_joon_23",
                "J-Hope", "RM",
                "Jimin", "Jungkook",
                2, 400
            ),
            ImageQuestion(
                183,
                "kim_nam_joon_24",
                "Jin", "V",
                "RM", "J-Hope",
                3, 350
            ),
            ImageQuestion(
                184,
                "kim_nam_joon_25",
                "Jungkook", "Suga",
                "Jin", "RM",
                4, 450
            ),
            ImageQuestion(
                185,
                "kim_seok_jin_25",
                "RM", "Jin",
                "Jimin", "Suga",
                2, 600
            ),
            ImageQuestion(
                186,
                "kim_seok_jin_26",
                "Jin", "J-Hope",
                "Jungkook", "RM",
                1, 350
            ),
            ImageQuestion(
                187,
                "kim_seok_jin_27",
                "Suga", "V",
                "Jin", "Jimin",
                3, 400
            ),
            ImageQuestion(
                188,
                "kim_seok_jin_28",
                "Jungkook", "J-Hope",
                "V", "Jin",
                4, 350
            ),
            ImageQuestion(
                189,
                "kim_tae_hyung_25",
                "V", "Suga",
                "Jin", "J-Hope",
                1, 550
            ),
            ImageQuestion(
                190,
                "kim_tae_hyung_26",
                "Suga", "V",
                "Jimin", "Jungkook",
                2, 350
            ),
            ImageQuestion(
                191,
                "kim_tae_hyung_27",
                "J-Hope", "Jungkook",
                "V", "Jin",
                3, 400
            ),
            ImageQuestion(
                192,
                "kim_tae_hyung_28",
                "Jungkook", "Jin",
                "Suga", "V",
                4, 400
            ),
            ImageQuestion(
                193,
                "min_yoon_gi_24",
                "Jin", "Jungkook",
                "J-Hope", "Suga",
                4, 600
            ),
            ImageQuestion(
                194,
                "min_yoon_gi_25",
                "Suga", "RM",
                "Jimin", "Jungkook",
                1, 400
            ),
            ImageQuestion(
                195,
                "min_yoon_gi_26",
                "Jin", "Suga",
                "V", "Jimin",
                2, 450
            ),
            ImageQuestion(
                196,
                "min_yoon_gi_27",
                "V", "J-Hope",
                "Suga", "Jimin",
                3, 350
            ),
            ImageQuestion(
                197,
                "park_ji_min_23",
                "Jimin", "Suga",
                "Jungkook", "Jin",
                1, 600
            ),
            ImageQuestion(
                198,
                "park_ji_min_24",
                "J-Hope", "Jimin",
                "Suga", "V",
                2, 350
            ),
            ImageQuestion(
                199,
                "park_ji_min_25",
                "Jungkook", "Jin",
                "Jimin", "RM",
                3, 550
            ),
            ImageQuestion(
                200,
                "park_ji_min_26",
                "RM", "Jungkook",
                "V", "Jimin",
                4, 450
            )
        )
    }

    fun getQuestionsFor67Migration(): List<ImageQuestion> {
        return listOf(
            ImageQuestion(
                201,
                "chimmy_3",
                "Chimmy", "Cooky",
                "Koya", "Mang",
                1, 600,
            ),
            ImageQuestion(
                202,
                "cooky_3",
                "Tata", "Cooky",
                "Shooky", "Rj",
                2, 600,
            ),
            ImageQuestion(
                203,
                "koya_3",
                "Mang", "Chimmy",
                "Koya", "Shooky",
                3, 600,
            ),
            ImageQuestion(
                204,
                "mang_3",
                "Rj", "Tata",
                "Chimmy", "Mang",
                4, 600,
            ),
            ImageQuestion(
                205,
                "rj_3",
                "Rj", "Koya",
                "Tata", "Cooky",
                1, 600,
            ),
            ImageQuestion(
                206,
                "shooky_3",
                "Koya", "Shooky",
                "Mang", "Chimmy",
                2, 600,
            ),
            ImageQuestion(
                207,
                "tata_3",
                "Cooky", "Rj",
                "Tata", "Mang",
                3, 600,
            ),
            ImageQuestion(
                208,
                "jeon_jung_kook_27",
                "Jimin", "J-Hope",
                "Suga", "Jungkook",
                4, 550,
            ),
            ImageQuestion(
                209,
                "jung_ho_seok_25",
                "J-Hope", "Jungkook",
                "Jin", "V",
                1, 600,
            ),
            ImageQuestion(
                210,
                "kim_nam_joon_26",
                "Jin", "RM",
                "V", "Jimin",
                2, 550,
            ),
            ImageQuestion(
                211,
                "kim_seok_jin_29",
                "RM", "Suga",
                "Jin", "Jungkook",
                3, 600,
            ),
            ImageQuestion(
                212,
                "kim_tae_hyung_29",
                "J-Hope", "Jin",
                "Jimin", "V",
                4, 600,
            ),
            ImageQuestion(
                213,
                "min_yoon_gi_28",
                "Suga", "J-Hope",
                "Jungkook", "Jimin",
                1, 600,
            ),
            ImageQuestion(
                214,
                "park_ji_min_27",
                "V", "Jimin",
                "Suga", "Jungkook",
                2, 600,
            ),
            ImageQuestion(
                215,
                "jhope_jimin_1",
                "J-Hope, Jungkook", "V, Jimin",
                "J-Hope, Jimin", "J-Hope, Suga",
                3, 600,
            ),
            ImageQuestion(
                216,
                "jin_v_1",
                "Suga, V", "V, Jin",
                "V, Jungkook", "Jin, V",
                4, 500,
            ),
            ImageQuestion(
                217,
                "jungkook_rm_1",
                "Jungkook, RM", "J-Hope, RM",
                "Jungkook, V", "Jungkook, Jimin",
                1, 400,
            ),
            ImageQuestion(
                218,
                "suga_jimin_1",
                "Suga, Jimin", "Suga, Jimin",
                "RM, Jimin", "J-Hope, Jimin",
                2, 600,
            )
        )
    }
}