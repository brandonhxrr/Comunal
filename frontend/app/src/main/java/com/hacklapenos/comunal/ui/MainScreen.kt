package com.hacklapenos.comunal.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hacklapenos.comunal.ui.community.CommunityCard
import com.hacklapenos.comunal.ui.community.communities
import com.hacklapenos.comunal.ui.projects.ProjectCard
import com.hacklapenos.comunal.ui.projects.projects
import com.hacklapenos.comunal.ui.theme.Screens

@Composable
fun MainScreen(navController: NavController) {

    Scaffold(
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(it)) {

                Text(
                    text = "Explora comunidades",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(16.dp)
                )
            LazyColumn(contentPadding = PaddingValues(16.dp), modifier = Modifier.height(250.dp)) {
                    items(communities) { community ->
                        CommunityCard(community = community) {
                            navController.navigate("${Screens.CommunityDetail.name}/${community.name}") {
                                launchSingleTop = true
                            }
                        }
                    }
                }


                Text(
                    text = "Explora proyectos",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(16.dp)
                )
                LazyColumn(contentPadding = PaddingValues(16.dp), modifier = Modifier.height(250.dp)) {
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
}
