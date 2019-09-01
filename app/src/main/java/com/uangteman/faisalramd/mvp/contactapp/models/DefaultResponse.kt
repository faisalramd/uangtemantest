package com.uangteman.faisalramd.mvp.contactapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class DefaultResponse <E> {

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("data")
    @Expose
    var data: List<E>? = null

}