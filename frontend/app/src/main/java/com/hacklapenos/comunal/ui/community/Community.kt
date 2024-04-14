package com.hacklapenos.comunal.ui.community

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hacklapenos.comunal.ui.theme.Screens

@Composable
fun CommunityScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
            .padding(bottom = 16.dp)
    ) {
        Text(text = "Comunidades")
        Spacer(modifier = Modifier.padding(16.dp))

        FloatingActionButton(
            onClick = { navController.navigate(Screens.AddCommunity.name) },
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add community")
        }
    }
}
