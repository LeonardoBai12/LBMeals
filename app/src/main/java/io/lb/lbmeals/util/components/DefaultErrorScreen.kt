package io.lb.lbmeals.util.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DefaultErrorScreen(
    modifier: Modifier = Modifier,
    onClickTryAgain: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = Modifier.size(64.dp)
                .padding(top = 12.dp),
            imageVector = Icons.Default.Close,
            contentDescription = "Warning",
            tint = MaterialTheme.colorScheme.onBackground,
        )
        Text(
            modifier = Modifier.padding(all = 16.dp),
            text = "Something went wrong =(",
            fontSize = 24.sp,
        )
        Button(
            onClick = {
                onClickTryAgain.invoke()
            },
        ) {
            Text(text = "Try again")
        }
    }
}
