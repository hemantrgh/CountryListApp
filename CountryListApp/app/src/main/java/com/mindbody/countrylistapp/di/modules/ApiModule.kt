package com.exozet.demoapp.di.modules

import com.exozet.demoapp.api.CountryInfoApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
class ApiModule{
    @Provides
    @Singleton
    fun providesCountryApi(retrofit: Retrofit): CountryInfoApi {
        return retrofit.create<CountryInfoApi>(CountryInfoApi::class.java)
    }
}