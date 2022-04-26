package com.github.zeroarst.cryptodemo.data

class CurrencyInfoRepository(
    private val currencyInfoDao: CurrencyInfoDao
) {

    fun getCurrencies(order: DataSortingOrder? = null) = currencyInfoDao.getCurrencyListOptionalOrderBy(order)

}