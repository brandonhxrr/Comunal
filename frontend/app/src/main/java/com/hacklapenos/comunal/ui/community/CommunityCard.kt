package com.hacklapenos.comunal.ui.community

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CommunityCard(community: Community, onCommunityClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onCommunityClick(community.imageURL) },
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(8.dp),

    ) {
        Column {
            GlideImage(model = community.imageURL, contentDescription = "Community Image", modifier = Modifier.fillMaxWidth().height(200.dp), contentScale = ContentScale.Crop)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = community.name, style = MaterialTheme.typography.titleSmall, modifier = Modifier.padding(18.dp))
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = community.description, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(top = 0.dp, start = 18.dp, end = 18.dp, bottom = 8.dp))
        }
    }
}