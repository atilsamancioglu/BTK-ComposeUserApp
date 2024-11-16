package com.atilsamancioglu.userapp.service

import com.atilsamancioglu.userapp.model.User
import retrofit2.http.GET

interface UserAPI {

    //source -> https://jsonplaceholder.typicode.com/users

    @GET("atilsamancioglu/UsersJSONPlaceHolder/refs/heads/main/users.json")
    suspend fun getData(): List<User>


    @GET("atilsamancioglu/UsersJSONPlaceHolder/refs/heads/main/users.json")
    suspend fun getSingleUser(): List<User>

}