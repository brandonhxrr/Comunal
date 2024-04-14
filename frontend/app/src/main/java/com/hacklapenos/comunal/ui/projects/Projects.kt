package com.hacklapenos.comunal.ui.projects

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hacklapenos.comunal.ui.theme.Screens

@Composable
fun ProjectsScreen(navController: NavController?) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
            .padding(bottom = 16.dp)
    ) {
        Text(text = "Proyectos")
        Spacer(modifier = Modifier.padding(16.dp))

        FloatingActionButton(
            onClick = { navController?.navigate(Screens.CreateProject.name)},
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add project")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ProjectsScreenPreview() {
    ProjectsScreen(navController = null)
}