package com.exozet.demoapp.di

import android.app.Application
import android.content.Context
import com.exozet.demoapp.di.modules.NetModule
import com.exozet.demoapp.di.modules.RepositoryModule
import com.exozet.demoapp.di.modules.ApiModule
import com.exozet.demoapp.di.modules.AppModule
import com.exozet.demoapp.repository.CountryRepository
import com.mindbody.countrylistapp.ui.countryinfo.CountryInfoViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(AppModule::class, NetModule::class, RepositoryModule::class, ApiModule::class)
)

interface AppComponent {

    fun inject(countryInfoViewModel: CountryInfoViewModel)
    fun inject(activity: Context)

    fun provideSearchUserRepository(): CountryRepository;

    companion object Factory{
        fun create(app: Application, baseUrl: String): AppComponent {
            val appComponent = DaggerAppComponent.builder().
                appModule(AppModule(app)).
                apiModule(ApiModule()).
                netModule(NetModule(baseUrl)).
                repositoryModule(RepositoryModule()).
                build();
            return appComponent
        }
    }
}