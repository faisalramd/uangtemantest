package com.uangteman.faisalramd.mvp.contactapp.models

import com.google.gson.Gson

/**
 * Created by ogulcan on 08/02/2018.
 */
data class DetailsViewModel(val contacts: List<Contact>, val users: List<User>, val albums: List<Album>) {
    fun toJson(): String {
        return Gson().toJson(this)
    }
}