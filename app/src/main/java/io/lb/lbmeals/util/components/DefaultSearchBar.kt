package io.lb.lbmeals.util.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@ExperimentalComposeUiApi
@Composable
fun DefaultSearchBar(
    search: MutableState<String>,
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {},
    isEnabled: Boolean = true,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(modifier = modifier) {
        TextField(
            value = search.value,
            onValueChange = {
                search.value = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            enabled = isEnabled,
            placeholder = {
                Text(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    text = hint,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            },
            shape = CircleShape,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            textStyle = TextStyle(color = MaterialTheme.colorScheme.onPrimaryContainer),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Default,
            ),
            keyboardActions = KeyboardActions {
                keyboardController?.hide()
            },
            leadingIcon = {
                Icon(
                    modifier = Modifier.padding(start = 16.dp),
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            },
            trailingIcon = {
                if (search.value.isNotEmpty()) {
                    IconButton(onClick = {
                        search.value = ""
                        onSearch(search.value)
                    }) {
                        Icon(
                            modifier = Modifier.padding(end = 16.dp),
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }
        )
    }
}
