package com.example.mvvmarchitecturewithkotlindagger2androidviewmodelretrofit.ui


sealed class Response
data class UserModel(val userId: String, val userName: String) : Response()
data class Error(val msg: String) : Response()