package com.hacklapenos.comunal.ui

import android.content.pm.PackageManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.rounded.Forest
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.School
import androidx.compose.material.icons.rounded.SelfImprovement
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.google.firebase.auth.FirebaseAuth
import com.hacklapenos.comunal.MainActivity
import com.hacklapenos.comunal.R
import com.hacklapenos.comunal.ui.community.CommunityScreen
import com.hacklapenos.comunal.ui.learn.LearnScreen
import com.hacklapenos.comunal.ui.projects.ProjectsScreen
import com.hacklapenos.comunal.ui.theme.Screens

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun Home(navController: NavController?) {
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }
    val auth = FirebaseAuth.getInstance()

    val items =
        listOf("Inicio", "Proyectos", "Comunidad", "Aprende")
    val icons = listOf(
        Icons.Rounded.Home,
        Icons.Rounded.Forest,
        Icons.Rounded.SelfImprovement,
        Icons.Rounded.School
    )

    var isLocationPermissionGranted by remember { mutableStateOf(false) }
    val context = LocalContext.current

    if (ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        isLocationPermissionGranted = true
    } else {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                context as MainActivity,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            AlertDialog(onDismissRequest = {
                ActivityCompat.requestPermissions(
                    context,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    1
                )
            }, title = { Text(text = "Permiso de ubicación") }, text = {
                Text(text = "Se necesita el permiso de ubicación para mostrar el clima de tu ubicación actual")
            }, confirmButton = { Text(text = "Aceptar") })
        } else {
            ActivityCompat.requestPermissions(
                context,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
        }

    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.logo),
                                contentDescription = "App logo",
                                modifier = Modifier
                                    .size(30.dp)
                                    .align(Alignment.CenterVertically)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = stringResource(id = R.string.app_name),
                                style = MaterialTheme.typography.headlineMedium,
                                modifier = Modifier.align(Alignment.CenterVertically),
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController?.navigate(Screens.User.name)
                    }) {
                        if (auth.currentUser?.photoUrl != null) {
                            GlideImage(
                                model = auth.currentUser?.photoUrl,
                                contentDescription = "User profile picture",
                                modifier = Modifier.clip(
                                    CircleShape
                                ),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Filled.AccountCircle,
                                contentDescription = "User profile picture"
                            )
                        }

                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItem == index,
                        onClick = { selectedItem = index },
                        icon = {
                            Icon(
                                imageVector = icons[index],
                                contentDescription = item
                            )
                        },
                        label = { Text(text = item, style = MaterialTheme.typography.bodySmall) })
                }
            }
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            when (selectedItem) {
                0 -> {
                    MainScreen(navController = navController!!)
                }

                1 -> {
                    ProjectsScreen(navController = navController!!)
                }

                2 -> {
                    CommunityScreen(navController = navController!!)
                }

                3 -> {
                    LearnScreen()
                }
            }
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    Home(null)
}