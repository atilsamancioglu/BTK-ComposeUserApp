package com.atilsamancioglu.userapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atilsamancioglu.userapp.model.User
import com.atilsamancioglu.userapp.service.UserAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserViewModel : ViewModel() {

    private val BASE_URL = "https://raw.githubusercontent.com/"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UserAPI::class.java)

    val userList = mutableStateOf<List<User>>(listOf())


    fun getUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            userList.value = retrofit.getData()
        }
    }

}