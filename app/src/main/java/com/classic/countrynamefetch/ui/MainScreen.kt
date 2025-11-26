package com.classic.countrynamefetch.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.classic.countrynamefetch.viewModel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val networkError = viewModel.networkerror

    val results = viewModel.fetchResults

    LaunchedEffect(Unit) {
        viewModel.searchCountry()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Country Search Result") },

            )
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(results) { country ->
                CountryItem(
                    name = country.name,
                    region = country.region,
                    code = country.code,
                    capital = country.capital
                )
            }
        }

        if (networkError) {
            AlertDialog(
                onDismissRequest = { viewModel.setNetworkError(false) },
                confirmButton = {
                    TextButton(onClick = {
                        viewModel.setNetworkError(false)   // dismiss
                        viewModel.searchCountry()   // retry
                    }) {
                        Text("Retry")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { viewModel.setNetworkError(false) }) {
                        Text("Cancel")
                    }
                },
                title = { Text("Error") },
                text = { Text("Network timeout. Please retry.") }
            )
        }
    }
}

@Composable
fun CountryItem(
    name: String,
    region: String,
    code: String,
    capital: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            // First row: name + region on left, code on right
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "$name, $region",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = code,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Capital row
            Text(
                text = capital,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
