package com.hacklapenos.comunal.ui.learn

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LearnScreen() {
    val videos = listOf(
        Video(
            id = "Ui-T7zYC3qs",
            title = "¿Cómo facturar tus gastos deducibles?",
            description = "Si lo que quieres saber es como facturar todos tus gastos para hacerlos deducibles y pagar menos impuestos, este es el video que debes de ver. Chécalo de forma rápida y gratuita lograras hacerlo.  ",
            videoUrl = "https://www.youtube.com/watch?v=Ui-T7zYC3qs",
            thumbnailUrl = "https://img.youtube.com/vi/Ui-T7zYC3qs/0.jpg"
        ),
        Video(
            id = "ywd-Ie-bx7o",
            title = "Como emitir un CFDI 4.0 en el portal del SAT",
            description = "En este video explicamos cómo emitir un CFDI 4.0 gratis desde el portal del SAT en 2022",
            videoUrl = "https://www.youtube.com/watch?v=ywd-Ie-bx7o",
            thumbnailUrl = "https://img.youtube.com/vi/ywd-Ie-bx7o/0.jpg"
        ),
        Video(
            id = "LTERF9t2bws",
            title = "Agricultura Orgánica",
            description = "La agricultura orgánica consiste en producir alimentos de forma natural, con métodos y prácticas que recuperan y mantienen la fertilidad del suelo, que protegen la diversidad de insectos benéficos y polinizadores. Evitando el uso de agroquímicos tóxicos, y los transgénicos",
            videoUrl = "https://www.youtube.com/watch?v=LTERF9t2bws",
            thumbnailUrl = "https://img.youtube.com/vi/LTERF9t2bws/0.jpg"
        ),
        Video(
            id = "GMEeEgDqLXY",
            title = "Desarrollo Local, ¿Qué podemos hacer como comunidad ?",
            description = "Buscar dentro de la comunidad que pueden ellos producir, los comunitarios pueden unificar esfuerzos en búsqueda de un bien común en un trabajo colaborativo para desarrollar riqueza para la comunidad y bienestar del ser.",
            videoUrl = "https://www.youtube.com/watch?v=GMEeEgDqLXY",
            thumbnailUrl = "https://img.youtube.com/vi/GMEeEgDqLXY/0.jpg"
        ),
        Video(
            id = "VnMSagtx2ww",
            title = "Cómo hacer una planeación de un PROYECTO EN 7 PASOS",
            description = "Hola, KZI-Kaizenia quiere compartir con la comunidad especializada en la realización y ejecución de proyectos material para que cualquier persona considere estos 7 pasos básicos para planear un proyecto y logré los resultados esperados.",
            videoUrl = "https://www.youtube.com/watch?v=VnMSagtx2ww",
            thumbnailUrl = "https://img.youtube.com/vi/VnMSagtx2ww/0.jpg"
        ),
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Text(
            text = "Aprende",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {

            itemsIndexed(videos) { _, video ->
                YoutubeCard(video = video) { videoUrl ->
                }
            }
        }
    }
}