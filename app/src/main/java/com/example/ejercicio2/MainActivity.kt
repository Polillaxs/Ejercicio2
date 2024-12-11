package com.example.ejercicio2

import android.os.Bundle
import android.widget.Toast
import android.view.LayoutInflater
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import android.graphics.drawable.Drawable
import android.graphics.Color as AndroidColor


data class ImageItem(
    val id: Int,
    val imageRes: Int,
    val title: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageGalleryScreen()
        }
    }
}


@Composable
fun ImageGalleryScreen() {

    val images = listOf(
        ImageItem(1, R.drawable.gato4, "Gatito tabby"),
        ImageItem(2, R.drawable.gato1, "Gato acariciado"),
        ImageItem(3, R.drawable.gato2, "Gato de pie"),
        ImageItem(4, R.drawable.gato5, "Gato esfinge"),
        ImageItem(5, R.drawable.gato6, "Gatito blanco"),
        ImageItem(6, R.drawable.gato3, "Gato de perfil"),
        ImageItem(7, R.drawable.gato8, "Gato con gafas"),
        ImageItem(8, R.drawable.gato7, "Gato siames")
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Galería",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .wrapContentWidth(Alignment.CenterHorizontally)
        )


        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize()
        ) {
            items(images) { image ->
                ImageCard(image = image)
            }
        }
    }
}


@Composable
fun ImageCard(image: ImageItem) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable {

                    showCustomToast(context, "Has seleccionado: ${image.title}")
                }
        ) {

            Image(
                painter = painterResource(id = image.imageRes),
                contentDescription = image.title,
                modifier = Modifier
                    .size(100.dp)
                    .padding(bottom = 8.dp)
            )

            Text(
                text = image.title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}


fun showCustomToast(context: android.content.Context, message: String) {
    val layoutInflater = LayoutInflater.from(context)
    val layout = layoutInflater.inflate(android.R.layout.simple_list_item_1, null)
    val textView: TextView = layout.findViewById(android.R.id.text1)
    textView.text = message
    textView.textSize = 20f
    textView.setTextColor(AndroidColor.BLACK)

    layout.setBackgroundColor(AndroidColor.parseColor("#D3D3D3"))
    textView.setPadding(20, 20, 20, 20)


    val toast = Toast(context)
    toast.duration = Toast.LENGTH_SHORT
    toast.view = layout
    toast.setGravity(android.view.Gravity.CENTER, 0, 0)
    toast.show()
}

@Preview(showBackground = true)
@Composable
fun ImageGalleryScreenPreview() {
    ImageGalleryScreen()
}
