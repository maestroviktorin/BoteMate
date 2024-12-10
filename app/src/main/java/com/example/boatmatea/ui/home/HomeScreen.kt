package com.example.boatmatea.ui.home

import LogEntryViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boatmatea.ui.AppViewModelProvider
import com.example.boatmatea.ui.log.AddLogEntryDialog
import com.example.boatmatea.ui.theme.BoatMateATheme
import com.example.boatmatea.ui.theme.LightColorScheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: LogEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    var showAddDialog by remember { mutableStateOf(false) }
    val textBgColor = LightColorScheme.secondary
    val textColor = Color.White
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(LightColorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
        Button(
            { showAddDialog = true }, colors = ButtonColors(
                containerColor = LightColorScheme.tertiary,
                contentColor = Color.White,
                disabledContentColor = Color.Black,
                disabledContainerColor = Color.Gray
            )
        ) {
            Text(
                text = "Добавить запись в бортовой журнал",
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.ExtraBold
            )
        }

        if (showAddDialog) {
            AddLogEntryDialog(
                onDismiss = { showAddDialog = false },
                onSave = { newEntry ->
                    viewModel.insertLogEntry(newEntry)
                }
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