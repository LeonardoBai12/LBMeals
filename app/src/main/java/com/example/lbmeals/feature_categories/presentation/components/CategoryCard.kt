package com.example.lbmeals.feature_categories.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.lbmeals.feature_categories.domain.model.Category

@ExperimentalMaterial3Api
@Composable
fun CategoryCard(category: Category, onClick: () -> Unit) {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    Column(
        modifier = Modifier
            .height(150.dp)
            .fillMaxSize()
            .padding(8.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
            ) {
                onClick.invoke()
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Image(
            modifier = Modifier.fillMaxSize(0.8F),
            painter = rememberAsyncImagePainter(category.thumbnail),
            contentDescription = "categoryThumb",
        )

        Text(
            text = category.name,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )
    }
}