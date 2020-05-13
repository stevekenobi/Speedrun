package com.example.network.utils.enums

object RunEnums {

    /********************
     *      STATUS      *
     ********************/

    const val STATUS_NEW = "new" //Field "examiner" is null
    const val STATUS_VERIFIED = "verified" // Has an extra "verified-date" field
    const val STATUS_REJECTED = "rejected" // Has an extra "reason" field
}