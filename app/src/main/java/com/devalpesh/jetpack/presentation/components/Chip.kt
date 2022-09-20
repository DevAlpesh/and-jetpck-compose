package com.devalpesh.jetpack.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.devalpesh.jetpack.presentation.ui.theme.SpaceSmall

@Composable
fun Chip(
    text: String,
    modifier: Modifier = Modifier,
    selectedColor: Color = MaterialTheme.colors.primary,
    unselectedColor: Color = MaterialTheme.colors.onSurface,
    onChipClick: () -> Unit = {},
    selected: Boolean = false
) {
    Text(
        text = text,
        color = if (selected) selectedColor else unselectedColor,
        modifier = modifier
            .clip(RoundedCornerShape(50.dp))
            .border(
                width = 1.dp,
                color = if (selected) selectedColor else unselectedColor,
                shape = RoundedCornerShape(50.dp)
            )
            .clickable { onChipClick() }
            .padding(SpaceSmall),
    )
}