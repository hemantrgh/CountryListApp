package com.exozet.demoapp.api
import com.mindbody.countrylistapp.model.Country
import com.mindbody.countrylistapp.model.Province
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Api class for performing search operations on user
 */

interface CountryInfoApi {
    @GET("country")
    fun getCountryList() : Call<List<Country>>

    @GET("country/{country}/province")
    fun getCountryProvinceList(@Path("country") user: String) : Call<List<Province>>
}