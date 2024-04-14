package com.hacklapenos.comunal.ui.projects

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hacklapenos.comunal.ui.theme.Screens

val projects = listOf(
    Project(
        name = "Agricultura sostenible",
        description = "Proyecto de agricultura sostenible.",
        imageURL = "https://images.unsplash.com/photo-1560493676-04071c5f467b?q=80&w=1374&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        community = "San Miguel Ahuehuetitlán",
    ),
    Project(
        name = "Reforestación en Yajalón",
        description = "Proyecto de reforestación.",
        imageURL = "https://images.unsplash.com/photo-1647220576336-f2e94680f3b8?q=80&w=1471&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        community = "Yajalón",
    ),
    Project(
        name = "Reciclaje de plástico en Coacalco",
        description = "Proyecto de reciclaje.",
        imageURL = "https://images.unsplash.com/photo-1595278069441-2cf29f8005a4?q=80&w=1471&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        community = "Coacalco",
    ),
    Project(
        name = "Arte urbano en Huehuetoca",
        description = "Proyecto de arte urbano.",
        imageURL = "https://images.unsplash.com/photo-1611063158871-7dd3ed4a2ac8?q=80&w=1374&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        community = "Huehuetoca",
    ),
)

@Composable
fun ProjectsScreen(navController: NavController) {


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screens.CreateProject.name) },
            ) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add project")
            }
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            item {
                Text(
                    text = "Proyectos",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                )
            }

            items(projects) { project ->
                ProjectCard(project = project) {
                    navController.navigate("${Screens.ProjectDetail.name}/${project.name}") {
                        launchSingleTop = true
                    }
                }
            }
        }
    }
}