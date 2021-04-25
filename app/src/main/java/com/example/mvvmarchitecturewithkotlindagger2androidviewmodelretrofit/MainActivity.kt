package com.example.mvvmarchitecturewithkotlindagger2androidviewmodelretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmarchitecturewithkotlindagger2androidviewmodelretrofit.ui.UserModel
import com.example.mvvmarchitecturewithkotlindagger2androidviewmodelretrofit.ui.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val mViewModel: UserViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[UserViewModel::class.java]
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        (application as AppApplication).networkComponent.inject(this)
        mViewModel.getData()
        observeUser()
        observeProgress()
        observeError()
    }

    private fun observeError() {
        mViewModel.getErrors().observe(this, Observer {
            it?.let { tvMain.text = it }
        })
    }

    private fun observeProgress() {
        mViewModel.getProgress().observe(this, Observer {
            progress.visibility = it.getVisibility()
        })
    }

    private fun observeUser() {
        mViewModel.getUserModel().observe(this, Observer {
            it?.let {
                when (it) {
                    is UserModel -> tvMain.text = it.userName
                    is Error -> mViewModel.getErrors().postValue(it.message)
                }
            }
        })
    }

    private fun Boolean?.getVisibility(): Int = if (this != null && this) View.VISIBLE else View.GONE
}