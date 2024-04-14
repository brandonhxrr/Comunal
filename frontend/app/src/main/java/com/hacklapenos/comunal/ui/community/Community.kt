package com.hacklapenos.comunal.ui.community

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hacklapenos.comunal.ui.theme.Screens

data class Community(
    val name: String,
    val description: String,
    val imageURL: String,
    val members: List<String>,
    val projects: List<String>,
)

val communities = listOf(
    Community(
        name = "San Miguel Ahuehuetitlán",
        description = "Comunidad de índigenas que practican la música tradicional de la región.",
        imageURL = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQoyP4oeK1BDqnKHx6akWQjG4Nti7_wy7cHptvXzpbrpg&s",
        members = listOf("Ian Vega", "Epifanio Garcia"),
        projects = listOf("Proyecto 1", "Proyecto 2"),
    ),
    Community(
        name = "Yajalón",
        description = "Comunidad de agricultores y ganaderos que buscan mejorar sus técnicas de producción.",
        imageURL = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT-iqu9H7Bt8noRLuCxQYRlcS8xiSuP7ZrIGZ2YuAx8mQ&s",
        members = listOf("Juan Hernández", "María López"),
        projects = listOf("Proyecto 1", "Proyecto 2"),
    ),
    Community(
        name = "Coacalco",
        description = "Comunidad de comerciantes que buscan mejorar su economía local.",
        imageURL = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQt6VnDy78WXR3jzED5hv9tgDLXnx5d27NjSvu87l4qOA&s",
        members = listOf("Miguel Pastor", "Juana de Asbaje"),
        projects = listOf("Proyecto 1", "Proyecto 2"),
    ),
    Community(
        name = "Huehuetoca",
        description = "Comunidad de artesanos que trabajan con madera.",
        imageURL = "https://blog.hogaresunion.com/hubfs/Jardin%20vecinal%20%282%29.jpg#keepProtocol",
        members = listOf("Brandon Herrera", "Jacinto Juárez"),
        projects = listOf("Proyecto 1", "Proyecto 2"),
    ),
)

@Composable
fun CommunityScreen(navController: NavController) {


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screens.AddCommunity.name) },
            ) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add community")
            }
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            item {
                Text(
                    text = "Comunidades",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                )
            }

            items(communities) { community ->
                CommunityCard(community = community) {
                    navController.navigate("${Screens.CommunityDetail.name}/${community.name}") {
                        launchSingleTop = true
                    }
                }
            }
        }
    }
}
