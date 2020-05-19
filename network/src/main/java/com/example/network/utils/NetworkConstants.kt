package com.example.network.utils

object NetworkConstants {

    /***************
     *   CATEGORY  *
     ***************/
    //  TYPE
    const val TYPE_PER_GAME = "per-game"
    const val TYPE_PER_LEVEL = "per-level"

    /******************
     *     TIMING     *
     ******************/
    const val TIMING_REALTIME = "realtime"
    const val TIMING_REALTIME_NO_LOADS = "realtime_noloads"
    const val TIMING_INGAME = "ingame"

    /*****************
     *     RULES     *
     *****************/
    const val RULES_TIMES_REALTIME = "realtime"
    const val RULES_TIMES_REALTIME_NO_LOADS = "realtime_noloads"
    const val RULES_TIMES_INGAME = "ingame"

    /*****************
     *      RUN      *
     *****************/

    const val STATUS_NEW = "new" //Field "examiner" is null
    const val STATUS_VERIFIED = "verified" // Has an extra "verified-date" field
    const val STATUS_REJECTED = "rejected" // Has an extra "reason" field
    /******************
     *      USER      *
     ******************/
    //  STYLE -> STYLE
    const val STYLE_SOLID = "solid" // Has one color
    const val STYLE_GRADIENT = "gradient" // Has 2 colors

    //  ROLE
    const val ROLE_BANNED = "banned"
    const val ROLE_USER = "user"
    const val ROLE_TRUSTED = "trusted"
    const val ROLE_MODERATOR = "moderator"
    const val ROLE_ADMIN = "admin"
    const val ROLE_PROGRAMMER = "programmer"

    const val REL_USER = "user"
    const val REL_GUEST = "guest"
}