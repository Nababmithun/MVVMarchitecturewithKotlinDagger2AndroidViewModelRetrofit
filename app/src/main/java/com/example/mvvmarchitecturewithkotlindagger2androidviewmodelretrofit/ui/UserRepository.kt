package com.example.mvvmarchitecturewithkotlindagger2androidviewmodelretrofit.ui

import androidx.lifecycle.MutableLiveData
import com.example.mvvmarchitecturewithkotlindagger2androidviewmodelretrofit.Data.GithubServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class UserRepository @Inject constructor(private val api: GithubServices) {

    private val mCompositeDisposable = CompositeDisposable()

    private val progress = MutableLiveData<Boolean>()

    fun getProgress() = progress

    fun getCompositeDisposable() = mCompositeDisposable

    fun getUserData(): MutableLiveData<Response> {
        val data = MutableLiveData<Response>()
        api.getUsers("milind-mevada-stl")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { progress.postValue(true) }
            .subscribeBy(
                onNext = {
                    progress.postValue(false)
                    data.value = UserModel("1", it["name"].asString)
                },
                onError = {
                    progress.postValue(false)
                    data.value = Error("User not found")
                }
            ).addTo(mCompositeDisposable)
        return data
    }

}