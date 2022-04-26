package com.github.zeroarst.cryptodemo.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.github.zeroarst.cryptodemo.data.CurrencyInfo
import com.github.zeroarst.cryptodemo.data.CurrencyTestData
import com.github.zeroarst.cryptodemo.ui.components.CircularIndeterminateLoadingIndicator
import com.github.zeroarst.cryptodemo.ui.currencylist.CurrencyList
import com.github.zeroarst.cryptodemo.ui.theme.AppTheme
import com.github.zeroarst.cryptodemo.ui.currencylist.CurrencyListViewModel
import com.github.zeroarst.cryptodemo.ui.currencylist.CurrencyListViewModelFactory

class MainActivity : AppCompatActivity() {

    private val viewModel: CurrencyListViewModel by viewModels { CurrencyListViewModelFactory(application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                MainScreen(viewModel)
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: CurrencyListViewModel) {
    val currencyList = viewModel.currencies.observeAsState()
    val uiState = viewModel.uiState.observeAsState()
    Scaffold {
        MainContent(currencyList.value, uiState.value) {
            viewModel.loadData()
        }
    }
}

@Composable
fun MainContent(
    currencies: List<CurrencyInfo>?,
    uiState: CurrencyListViewModel.UIState?,
    onClickLoadCurrencyListButton: () -> Unit
) {
    val context = LocalContext.current
    var toast by remember { mutableStateOf<Toast?>(null) }
    Column {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Button(
                onClick = onClickLoadCurrencyListButton,
                modifier = Modifier.testTag("LoadCurrencyListButton"),
                enabled = uiState != CurrencyListViewModel.UIState.LOADING,
            ) {
                Text(text = "Load Currency List")
            }
        }
        Box {
            if (currencies != null) {
                CurrencyList(
                    currencyList = currencies,
                    onClickItem = {
                        toast?.cancel()
                        toast = Toast.makeText(context, it.name, Toast.LENGTH_SHORT).apply { show() }
                    }
                )
            }
            // Show loading indicator if is in loading state.
            CircularIndeterminateLoadingIndicator(uiState == CurrencyListViewModel.UIState.LOADING)
        }
    }
}


@Preview
@Composable
fun PreviewMainContent() {
    MainContent(
        currencies = CurrencyTestData.currencies,
        uiState = CurrencyListViewModel.UIState.SHOW_DATA
    ) { }
}