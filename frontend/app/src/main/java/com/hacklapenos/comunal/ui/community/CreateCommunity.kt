package com.hacklapenos.comunal.ui.community

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.AddPhotoAlternate
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.hacklapenos.comunal.data.community.CommunityViewModel
import com.hacklapenos.comunal.ui.ComunalButton
import com.hacklapenos.comunal.ui.login.ErrorMessage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CreateCommunity(communityViewModel: CommunityViewModel, navController: NavController?) {

    val name: String by communityViewModel.name.observeAsState(initial = "")
    val selectedImageUri: String? by communityViewModel.selectedImageUri.observeAsState()
    val errorMessage: String by communityViewModel.errorMessage.observeAsState(initial = "")
    val showError: Boolean by communityViewModel.showError.observeAsState(initial = false)
    val description by communityViewModel.description.observeAsState(initial = "")
    val location by communityViewModel.location.observeAsState(initial = "")
    val selectedCategories: List<String> by communityViewModel.selectedCategories.observeAsState(initial = emptyList())

    val registerImageActivityLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            communityViewModel.onImageSelected(uri.toString())
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Row {
            IconButton(
                onClick = { navController?.navigateUp() },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "Go back",
                )
            }

            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Crear comunidad",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }

        Text(
            text = "Llena los siguientes campos para crear una comunidad",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 16.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Box(modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .clickable {
                registerImageActivityLauncher.launch("image/*")
            }
            .align(Alignment.CenterHorizontally), contentAlignment = Alignment.Center
        ) {
            if (selectedImageUri != null) {
                GlideImage(
                    model = selectedImageUri,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            } else {
                Icon(
                    imageVector = Icons.Rounded.AddPhotoAlternate,
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .background(MaterialTheme.colorScheme.onBackground)
                        .padding(20.dp),
                    tint = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        if (showError) {
            ErrorMessage(errorMessage)
        }

        Spacer(modifier = Modifier.height(16.dp))

        CommunityTextField(value = name, placeholder = "Nombre de la comunidad") {
            communityViewModel.onNameChanged(it)
        }

        CommunityTextField(value = location, placeholder = "Ubicación") {
            communityViewModel.onLocationChanged(it)
        }

        CommunityTextField(
            value = description,
            placeholder = "Descripción",
            modifier = Modifier.height(70.dp)
        ) {
            communityViewModel.onDescriptionChanged(it)
        }

        Spacer(modifier = Modifier.height(20.dp))

        ChipList(categories = listOf("Ganadería", "Jardinería", "Reforestación", "Agricultura", "Pesca", "Artesanías", "Apicultura", "Construcción", "Educación"), selectedCategories = selectedCategories, communityViewModel = communityViewModel)

        Spacer(modifier = Modifier.height(20.dp))

        ComunalButton(
            text = "Crear comunidad",
            enabled = communityViewModel.enableSignUp(name, description, location)
        ) {

        }
    }
}

@Composable
fun CommunityTextField(
    value: String,
    placeholder: String,
    modifier: Modifier? = Modifier,
    onTextChanged: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = {
            onTextChanged(it)
        },
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.bodySmall
            )
        },
        textStyle = MaterialTheme.typography.bodySmall,
        modifier = modifier!!
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 8.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
    )
}

@Composable
fun ChipList(
    categories: List<String>,
    selectedCategories: List<String>,
    communityViewModel: CommunityViewModel
) {
    val chipColors = listOf(
        Color(0xFF8576FF),
        Color(0xFFFF9800),
        Color(0xFF5356FF),
        Color(0xFF35374B),
        Color(0xFF525CEB),
        Color(0xFF527853),
    )

    LazyRow(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
    ) {
        itemsIndexed(categories) { index, category ->
            Chip(
                text = category,
                isSelected = selectedCategories.contains(category),
                color = chipColors[index % chipColors.size],
                onSelected = {
                    if (!selectedCategories.contains(category)) {
                        communityViewModel.onCategorySelected(category)
                    } else {
                        communityViewModel.onCategoryUnselected(category)
                    }
                }
            )
        }
    }
}

@Composable
fun Chip(
    text: String,
    isSelected: Boolean,
    color: Color,
    onSelected: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = if (isSelected) color else Color.LightGray,
        modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable(onClick = onSelected)
        ) {
            Text(
                text = text,
                color = if (isSelected) Color.White else Color.Black,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
            if (isSelected) {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "Close",
                    tint = Color.White,
                    modifier = Modifier.padding(end = 4.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CreateCommunityPreview() {
    CreateCommunity(communityViewModel = CommunityViewModel(), navController = null)
}