package com.uangteman.faisalramd.mvp.contactapp.api

import com.uangteman.faisalramd.mvp.contactapp.models.*
import com.uangteman.faisalramd.mvp.contactapp.util.Constants
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


/**
 * Created by ogulcan on 07/02/2018.
 * modified by faisalramd on 01/09/2019
 */
interface ApiServiceInterface {

    @GET("contact")
    fun getContacts(): Observable<DefaultResponse<Contact>>

    @GET("contact/{id}")
    fun getContacts(@Path("id") id: String): Observable<DefaultResponse<Contact>>

    @POST("contact")
    fun addContacts(@Body body: Contact): Observable<DefaultResponse<Contact>>

    @DELETE("contact/{id}")
    fun deleteContacts(@Path("id") id: String): Observable<DefaultResponse<Contact>>

    @PUT("contact")
    fun editContacts(@Body body: Contact): Observable<DefaultResponse<Contact>>

    companion object Factory {
        fun create(): ApiServiceInterface {
            val retrofit = retrofit2.Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constants.BASE_URL)
                    .build()

            return retrofit.create(ApiServiceInterface::class.java)
        }
    }
}