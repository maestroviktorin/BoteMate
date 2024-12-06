package com.example.boatmatea.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.boatmatea.ui.theme.BoatMateATheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val textBgColor = Color(0xCC100873)
    val textColor = Color(0xFFFFFFFF)
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0x445FBDCE))
    ) {
        Text(
            text = "Здесь скорее всего будет добавление новой записи в бортжурнал",
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.ExtraBold,
            color = textColor,
            modifier = Modifier
                .padding(8.dp)
                .clip(
                    shape = RoundedCornerShape(size = 10.dp)
                )
                .fillMaxWidth()
                .height(300.dp)
                .background(color = textBgColor)
        )
        Row {
            Text(
                text = "TODO: Button to Knots",
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.ExtraBold,
                color = textColor,
                modifier = Modifier
                    .weight(0.5f)
                    .padding(8.dp)
                    .clip(
                        shape = RoundedCornerShape(size = 10.dp)
                    )
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(color = textBgColor)
            )

            Text(
                text = "TODO: Button to Craft Navigation Signs",
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.ExtraBold,
                color = textColor,
                modifier = Modifier
                    .weight(0.5f)
                    .padding(8.dp)
                    .clip(
                        shape = RoundedCornerShape(size = 10.dp)
                    )
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(color = textBgColor)
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    BoatMateATheme {
        HomeScreen()
    }
}