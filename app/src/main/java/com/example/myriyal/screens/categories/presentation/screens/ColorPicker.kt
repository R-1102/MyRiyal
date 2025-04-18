package com.example.myriyal.screens.categories.presentation.screens

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.material3.Text
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.unit.dp
import com.example.myriyal.R
import com.github.skydoves.colorpicker.compose.*

@Composable
fun ColorPicker(
    title: String,
    categoryColor: ColorPickerController,
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
                    .clip(RoundedCornerShape(integerResource(R.integer.RoundedCornerShape).dp)),
                controller = categoryColor
            )
        }
    }
}