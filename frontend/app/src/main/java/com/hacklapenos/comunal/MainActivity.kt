package com.hacklapenos.comunal

import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hacklapenos.comunal.data.signup.SignUpViewModel
import com.hacklapenos.comunal.ui.SplashScreen
import com.hacklapenos.comunal.ui.login.Login
import com.hacklapenos.comunal.ui.login.SignUp1
import com.hacklapenos.comunal.ui.login.SignUp2
import com.hacklapenos.comunal.ui.theme.ComunalTheme
import com.hacklapenos.comunal.ui.theme.Screens
import com.google.firebase.auth.FirebaseAuth
import com.hacklapenos.comunal.data.community.CommunityViewModel
import com.hacklapenos.comunal.data.login.LoginViewModel
import com.hacklapenos.comunal.ui.Home
import com.hacklapenos.comunal.ui.UserScreen
import com.hacklapenos.comunal.ui.community.CommunityScreen
import com.hacklapenos.comunal.ui.community.CreateCommunity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComunalTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Start()
                }
            }
        }
    }
}

@Composable
fun Start() {
    val navController = rememberNavController()
    val user = FirebaseAuth.getInstance().currentUser

    NavHost(
        navController = navController,
        startDestination = Screens.Splash.name
    ) {
        val signUpViewModel = SignUpViewModel()

        composable(Screens.Splash.name) {
            SplashScreen()
            val timer = object : CountDownTimer(1500, 1000) {
                override fun onTick(millisUntilFinished: Long) {

                }

                override fun onFinish() {
                    if (navController.currentBackStackEntry?.destination?.route == Screens.Splash.name) {
                        if (user != null) {
                            navController.navigate(Screens.Home.name) {
                                popUpTo(Screens.Splash.name) {
                                    inclusive = true
                                }
                            }
                        } else {
                            navController.navigate(Screens.Login.name) {
                                popUpTo(Screens.Splash.name) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                }
            }

            timer.start()

        }
        composable(Screens.Home.name) {
            Home(navController = navController)
        }

        composable(Screens.Login.name) {
            Login(navController = navController, LoginViewModel())
        }

        composable(Screens.SignUp1.name) {
            SignUp1(navController = navController, signUpViewModel)
        }

        composable(Screens.SignUp2.name) {
            SignUp2(navController = navController, signUpViewModel)
        }

        composable(Screens.User.name) {
            UserScreen(navController = navController)
        }

        composable(Screens.AddCommunity.name) {
            CreateCommunity(navController = navController, communityViewModel = CommunityViewModel())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    ComunalTheme {
        Start()
    }
}