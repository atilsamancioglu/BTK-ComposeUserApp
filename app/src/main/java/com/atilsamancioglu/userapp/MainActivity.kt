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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.atilsamancioglu.userapp.model.Address
import com.atilsamancioglu.userapp.model.Company
import com.atilsamancioglu.userapp.model.Geo
import com.atilsamancioglu.userapp.model.User
import com.atilsamancioglu.userapp.screens.DetailScreen
import com.atilsamancioglu.userapp.screens.UserList
import com.atilsamancioglu.userapp.ui.theme.UserAppTheme
import com.atilsamancioglu.userapp.viewmodel.DetailViewModel
import com.atilsamancioglu.userapp.viewmodel.UserViewModel
import com.google.gson.Gson
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val viewModel : UserViewModel by viewModels<UserViewModel>()
    private val detailViewModel : DetailViewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            UserAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavHost(navController = navController, startDestination = "liste_ekrani") {
                            composable("liste_ekrani") {
                                viewModel.getUsers()
                                UserList(userList = viewModel.userList.value, navController = navController)
                            }

                            composable("detay_ekrani/{secilenKullanici}",
                                arguments = listOf(
                                    navArgument("secilenKullanici") {
                                        type = NavType.IntType
                                    }
                                )
                            ) {

                                val kullaniciString = remember {
                                    it.arguments?.getInt("secilenKullanici")
                                }

                                //val secilenKullanici = Gson().fromJson(kullaniciString,User::class.java)

                                /*
                                yanlış yol
                                val scope = rememberCoroutineScope()

                                var secilenKullanici : User? = null
                                scope.launch {
                                    secilenKullanici = detailViewModel.getSingleUser(kullaniciString ?: 0)

                                }
                                secilenKullanici?.let {
                                    DetailScreen(user = secilenKullanici!!)
                                }
                                 */

                                /*

                                //İkinci yol
                                val secilenKullanici = remember {
                                    mutableStateOf<User>(User(1,"","","", Address("","","","",
                                        Geo("","")),"","", Company("","","")))
                                }

                                LaunchedEffect(key1 = Unit) {
                                    secilenKullanici.value = detailViewModel.getSingleUser(kullaniciString?: 0)

                                }

                                 */

                                //üçüncü yol
                                val secilenKullanici = produceState<User>(initialValue = User(1,"","","", Address("","","","",
                                    Geo("","")),"","", Company("","",""))) {
                                    value = detailViewModel.getSingleUser(kullaniciString?: 0)
                                }


                                DetailScreen(user = secilenKullanici.value)


                            }

                        }
                    }

                }
            }
        }
    }
}

