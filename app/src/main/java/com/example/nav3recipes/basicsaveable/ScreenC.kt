package com.example.nav3recipes.basicsaveable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nav3recipes.content.ContentGreen

@Composable
fun ScreenC(
    navigateToScreenA: () -> Unit,
    screenCViewModel: ScreenCViewModel = viewModel()
) {
    val name by screenCViewModel.name.collectAsStateWithLifecycle()

    ContentGreen(screenCViewModel.message) {
        Column {
            Text(name)

            if (screenCViewModel.keys.isNotEmpty()) {
                Text("Keys: ${screenCViewModel.keys.joinToString()}")
            } else {
                Text("No keys")
            }

            Row {
                Text("Count: ${screenCViewModel.counter}")
                Button(onClick = screenCViewModel::increment) {
                    Text("Increment")
                }
            }
            Button(onClick = navigateToScreenA) {
                Text("Click to navigate")
            }
        }
    }
}