package com.example.myriyal.screens.categories.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import com.github.skydoves.colorpicker.compose.*


@Composable
fun ColorPicker(
    title: String,
    /*selectedColor: String,
    onColorSelected: () -> Unit*/
    categColor: ColorPickerController,
//    initialColor: Color,
) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(46.dp)
    ) {

        Text(text = title)
        Spacer(Modifier.height(16.dp))

        // the colors wheel
        HsvColorPicker(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(10.dp),
            controller = categColor,
//            initialColor = initialColor
        )

        // to darken a color
        BrightnessSlider(
            modifier = Modifier
                .fillMaxWidth()
//                .padding(10.dp)
                .height(35.dp),
            controller = categColor,
        )
        Spacer(Modifier.height(16.dp))

        //for current color sample
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AlphaTile(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(60.dp)
                    .clip(RoundedCornerShape(6.dp)),
                controller = categColor
            )
        }
    }
}