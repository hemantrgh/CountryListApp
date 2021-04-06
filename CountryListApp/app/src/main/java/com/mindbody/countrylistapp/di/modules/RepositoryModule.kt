package com.exozet.demoapp.di.modules

import com.exozet.demoapp.api.CountryInfoApi
import com.exozet.demoapp.repository.CountryRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Will return the repository to the callers
 */
@Module
class RepositoryModule{
    @Provides
    @Singleton
    fun provideCountryRepository(api: CountryInfoApi): CountryRepository{
        return CountryRepository(api)
    }
}