package com.example.mvvmarchitecturewithkotlindagger2androidviewmodelretrofit.di

import com.example.mvvmarchitecturewithkotlindagger2androidviewmodelretrofit.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ViewModelModule::class])
interface NetworkComponent {
    fun inject(activity: MainActivity)
}