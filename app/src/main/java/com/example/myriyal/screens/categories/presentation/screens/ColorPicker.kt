package com.example.myriyal.screens.categories.presentation.screens

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myriyal.R
import com.github.skydoves.colorpicker.compose.*

@Composable
fun ColorPicker(
    title: String,
    categoryColor: ColorPickerController,
//    onColorSelected: (Color) -> Unit, // Callback when user confirms the new color
//    onDismiss: () -> Unit,           // Callback when user cancels the edit
) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(integerResource(R.integer.colorPickerColumnPadding).dp)
    ) {

        Text(text = title)
        Spacer(Modifier.height(integerResource(R.integer.colorPickerColumnSpacerH).dp))

        // the colors wheel
        HsvColorPicker(
            modifier = Modifier
                .fillMaxWidth()
                .height(integerResource(R.integer.colorPickerSize).dp)
                .padding(integerResource(R.integer.hsvColorPickerPadding).dp),
            controller = categoryColor,
//            initialColor = initialColor
            onColorChanged = {
                Log.d("Color", it.hexCode)
            }

        )

        // to darken a color
        BrightnessSlider(
            modifier = Modifier
                .fillMaxWidth()
                .height(integerResource(R.integer.brightnessSliderHeight).dp),
            controller = categoryColor,
        )
        Spacer(Modifier.height(integerResource(R.integer.verticalSpacer).dp))

        //for current color sample
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AlphaTile(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(integerResource(R.integer.alphaTileSize).dp)
                    .clip(RoundedCornerShape(integerResource(R.integer.roundCardCornerShape).dp)),
                controller = categoryColor
            )
        }

        // Spacer to add vertical space between the color sample field and buttons
        Spacer(Modifier.height(integerResource(R.integer.verticalSpacer).dp))

        // Row to hold the "OK" and "Cancel" buttons
        Row (modifier = Modifier.fillMaxWidth()){
//            TextButton(
//                onClick = {onColorSelected(categoryColor.selectedColor.value)}
//            ) {
//                Text(
//                    text = stringResource(R.string.ok),
//                    color = MaterialTheme.colorScheme.onSurface,
//                    fontWeight = FontWeight.Bold
//                )
//            }

            Spacer(Modifier.weight(1f)) // Pushes Cancel button to the right end

            // Cancel button: closes the dialog without saving
//            TextButton(
//                onClick = onDismiss
//            ) {
//                Text(
//                    text = stringResource(R.string.cancel),
//                    color = MaterialTheme.colorScheme.onSurface,
//                    fontWeight = FontWeight.Bold
//                )
//            }
        }
    }
}