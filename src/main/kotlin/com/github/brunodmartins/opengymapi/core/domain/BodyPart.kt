package com.github.brunodmartins.opengymapi.core.domain

import com.github.brunodmartins.opengymapi.core.domain.BodyPart.Companion.PART.BACK
import com.github.brunodmartins.opengymapi.core.domain.BodyPart.Companion.PART.FRONT

/**
 * Body Parts, based on:
 * <a>https://www.bestmusclesupps.com/wp-content/uploads/2017/04/Best-Exercises-For-Each-Muscle-Group.jpg</a>
 */
enum class BodyPart(front: PART) {
    DELTOID(FRONT),
    CHEST(FRONT),
    BICEPS(FRONT),
    FOREARMS(FRONT),
    SIDE_ABS(FRONT),
    ABDOMINAL(FRONT),
    QUADRICEPS(FRONT),
    TIBIALIS(FRONT),
    UPPER_BACK(BACK),
    INFRASPINATUS(BACK),
    TRICEPS(BACK),
    MIDDLE_BACK(BACK),
    LOWER_BACK(BACK),
    GLUTEUS_MAXIMUMS(BACK),
    HAMSTRING_GROUP(BACK),
    CALF(BACK);

    companion object {
        enum class PART {
            FRONT,
            BACK,
        }
    }
}
