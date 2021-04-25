package com.example.mvvmarchitecturewithkotlindagger2androidviewmodelretrofit

import android.app.Application
import com.example.mvvmarchitecturewithkotlindagger2androidviewmodelretrofit.di.NetworkComponent
import com.example.mvvmarchitecturewithkotlindagger2androidviewmodelretrofit.di.NetworkModule

class AppApplication:Application() {

    lateinit var networkComponent: NetworkComponent

    override fun onCreate() {
        super.onCreate()
        networkComponent = DaggerNetworkComponent
            .builder()
            .networkModule(NetworkModule)
            .build()
    }
}