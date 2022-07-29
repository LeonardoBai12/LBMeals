package com.example.lbmeals.feature_categories.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.lbmeals.feature_categories.domain.model.Category

@ExperimentalMaterial3Api
@Composable
fun CategoryCard(category: Category, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(0.5f)
        ),
        onClick = {
            onClick.invoke()
        },
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                modifier = Modifier.size(48.dp),
                painter = rememberAsyncImagePainter(category.thumbnail),
                contentDescription = "categoryThumb",
            )
            
            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = category.name,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
        }
    }
}