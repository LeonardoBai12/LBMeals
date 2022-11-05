package io.lb.lbmeals.util.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun DefaultSearchBAr(
    modifier: Modifier = Modifier,
    hint: String = "Procurar...",
    onSearch: (String) -> Unit = {}
) {
    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(modifier = modifier) {
        TextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = MaterialTheme.colorScheme.onPrimaryContainer),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = !it.isFocused
                },
            leadingIcon = {
                Icon(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            },
            trailingIcon = {
                if (!isHintDisplayed) {
                    IconButton(onClick = { isHintDisplayed = true }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Arrow Back",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (isHintDisplayed) {
                Text(
                    text = hint,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 12.dp)
                )
            }
        }
    }
}