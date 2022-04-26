package com.github.zeroarst.cryptodemo.ui.currencylist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.zeroarst.cryptodemo.data.CurrencyInfo
import com.github.zeroarst.cryptodemo.data.CurrencyTestData
import com.github.zeroarst.cryptodemo.ui.theme.AppTheme
import com.github.zeroarst.cryptodemo.ui.theme.Typography

@Composable
fun CurrencyList(currencyList: List<CurrencyInfo>, onClickItem: (CurrencyInfo) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .background(Color.White)
            .testTag("currencyList"),
        contentPadding = PaddingValues(horizontal = 4.dp, vertical = 4.dp)
    ) {
        items(
            items = currencyList,
            key = { it.id },
            itemContent = { it ->
                CurrencyListItem(currencyInfo = it) {
                    onClickItem(it)
                }
            }
        )
    }
}

@Composable
fun CurrencyListItem(currencyInfo: CurrencyInfo, onClickItem: (CurrencyInfo) -> Unit) {
    Card(
        shape = MaterialTheme.shapes.medium,
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .testTag("currencyListItem")
            .padding(8.dp)
            .clickable {
                onClickItem(currencyInfo)
            },
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(48.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // circle box shows currency first letter.
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .width(32.dp)
                    .height(32.dp)
                    .aspectRatio(1f)
                    .background(Color.Gray, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(text = currencyInfo.symbol.first().toString(), color = Color.White, textAlign = TextAlign.Center)
            }
            Text(text = currencyInfo.name, style = Typography.h6)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = currencyInfo.symbol, style = Typography.body1)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CurrencyListPreview() {
    AppTheme {
        val currencies = CurrencyTestData.currencies
        CurrencyList(currencies) {

        }
    }
}