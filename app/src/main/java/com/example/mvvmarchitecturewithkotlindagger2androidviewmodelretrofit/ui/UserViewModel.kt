package com.example.mvvmarchitecturewithkotlindagger2androidviewmodelretrofit.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class UserViewModel @Inject constructor(private val mUserRepo: UserRepository) : ViewModel() {

    private var userModel = MutableLiveData<Response>()
    private val error = MutableLiveData<String>()

    fun getProgress() = mUserRepo.getProgress()

    fun getUserModel(): MutableLiveData<Response> = userModel

    fun getErrors() = error

    fun getData() {
        userModel.value?.let {
            return
        }
        userModel = mUserRepo.getUserData()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("DISPOSE", "----- disposed -------")
        mUserRepo.getCompositeDisposable().clear()
        mUserRepo.getCompositeDisposable().dispose()
    }
}