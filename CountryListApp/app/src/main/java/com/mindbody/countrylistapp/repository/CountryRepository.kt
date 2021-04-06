package com.exozet.demoapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.exozet.demoapp.api.CountryInfoApi
import com.exozet.demoapp.exception.AppException
import com.exozet.demoapp.network.NetworkResponse
import com.mindbody.countrylistapp.model.Country
import com.mindbody.countrylistapp.model.Province
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CountryRepository @Inject constructor(val countryInfoApi: CountryInfoApi){

     fun getCountryList(): LiveData<NetworkResponse<List<Country>>> {

        val resultData = MutableLiveData<NetworkResponse<List<Country>>> ()

        countryInfoApi.getCountryList().enqueue(object : Callback<List<Country>> {

            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                if(response.isSuccessful && response.body() != null) {
                    resultData.value = NetworkResponse.success(response?.body())
                } else {
                    val exception = AppException(response.message())
                    resultData.value = NetworkResponse.error(exception)
                }
            }

            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                val exception = AppException(t.message)
                resultData.value = NetworkResponse.error(exception)
            }
        })

        return resultData
    }

    fun getCountryProvinceList(countryID: String): LiveData<NetworkResponse<List<Province>>> {

        val resultData = MutableLiveData<NetworkResponse<List<Province>>> ()

        countryInfoApi.getCountryProvinceList(countryID).enqueue(object : Callback<List<Province>> {

            override fun onResponse(call: Call<List<Province>>, response: Response<List<Province>>) {
                if(response.isSuccessful && response.body() != null) {
                    resultData.value = NetworkResponse.success(response?.body())
                } else {
                    val exception = AppException(response.message())
                    resultData.value = NetworkResponse.error(exception)
                }
            }

            override fun onFailure(call: Call<List<Province>>, t: Throwable) {
                val exception = AppException(t.message)
                resultData.value = NetworkResponse.error(exception)
            }
        })

        return resultData
    }
}