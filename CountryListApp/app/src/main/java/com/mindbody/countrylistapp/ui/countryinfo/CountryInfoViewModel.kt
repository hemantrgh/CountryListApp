package com.mindbody.countrylistapp.ui.countryinfo

import androidx.lifecycle.*
import com.exozet.demoapp.network.NetworkResponse
import com.exozet.demoapp.repository.CountryRepository
import com.mindbody.countrylistapp.model.Country
import com.mindbody.countrylistapp.model.Province
import javax.inject.Inject

class CountryInfoViewModel : ViewModel() {

    private var countriesList:LiveData<NetworkResponse<List<Country>>>? = null

    private var provinceList:LiveData<NetworkResponse<List<Province>>>? = null

    private lateinit var countryRepository: CountryRepository

    @Inject fun init (countryRepository: CountryRepository){
        this.countryRepository = countryRepository
    }

    fun getCountriesList(): LiveData<NetworkResponse<List<Country>>>? {
        if(countriesList  == null) {
            countriesList = MutableLiveData()
            countriesList =  countryRepository.getCountryList()
        }
        return countriesList
    }

    fun getProvinceList(country: String): LiveData<NetworkResponse<List<Province>>>? {
        if(provinceList  == null) {
            provinceList = MutableLiveData()
            provinceList =  countryRepository.getCountryProvinceList(country)
        }
        return provinceList
    }

}