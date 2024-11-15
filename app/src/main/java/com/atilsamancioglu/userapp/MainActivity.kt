package com.atilsamancioglu.userapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.atilsamancioglu.userapp.screens.UserList
import com.atilsamancioglu.userapp.ui.theme.UserAppTheme
import com.atilsamancioglu.userapp.viewmodel.UserViewModel

class MainActivity : ComponentActivity() {

    private val viewModel : UserViewModel by viewModels<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UserAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        viewModel.getUsers()
                        UserList(userList = viewModel.userList.value)
                    }
                }

            }
        }
    }
}

