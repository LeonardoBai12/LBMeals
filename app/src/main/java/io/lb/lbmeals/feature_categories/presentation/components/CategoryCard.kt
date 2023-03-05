package io.lb.lbmeals.feature_categories.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import io.lb.lbmeals.feature_categories.domain.model.Category
import io.lb.lbmeals.util.components.shimmerAnimation

@ExperimentalMaterial3Api
@Composable
fun CategoryCard(category: Category, onClick: () -> Unit) {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    val loading = remember {
        mutableStateOf(true)
    }

    val couldNotLoad = remember {
        mutableStateOf(false)
    }

    if (loading.value) {
        Box(
            modifier = Modifier
                .height(150.dp)
                .fillMaxSize()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else if (couldNotLoad.value) {
        Box(
            modifier = Modifier
                .height(150.dp)
                .fillMaxSize()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Couldn't load picture")
        }
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
            painter = rememberAsyncImagePainter(
                model = category.thumbnail,
                onSuccess = {
                    loading.value = false
                },
                onError = {
                    loading.value = false
                    couldNotLoad.value = true
                }
            ),
            contentDescription = "categoryThumb",
        )

        Text(
            text = category.name,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )
    }
}

@Composable
fun CategoryShimmerCard() {
    Column(
        modifier = Modifier
            .height(150.dp)
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxSize(0.8F)
                .padding(bottom = 12.dp)
                .shimmerAnimation()
        )
        Spacer(
            modifier = Modifier
                .height(16.dp)
                .fillMaxWidth(0.7f)
                .shimmerAnimation()
        )
    }
}
