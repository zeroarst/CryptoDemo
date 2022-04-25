package com.crypto.demo.data

import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.crypto.demo.untils.TABLE_NAME_CURRENCIES

@Dao
interface CurrencyInfoDao {

    @Query("SELECT * FROM $TABLE_NAME_CURRENCIES where :name IS NULL OR name LIKE '%:name%'")
    fun getCurrencies(name: String? = null): List<CurrencyInfo>

    @RawQuery
    fun getCurrencyListByRawQuery(query: SupportSQLiteQuery): List<CurrencyInfo>?

    fun getCurrencyListOptionalOrderBy(order: Order? = null): List<CurrencyInfo> {
        val statement = "SELECT * FROM currencies ${createOrderByString(order)}"
        val query: SupportSQLiteQuery = SimpleSQLiteQuery(statement, arrayOf())
        return getCurrencyListByRawQuery(query).orEmpty()
    }

    private fun createOrderByString(order: Order? = null): String? {
        return when (order) {
            Order.ASC -> "ORDER BY name ASC"
            Order.DESC -> "ORDER BY name DESC"
            else -> ""
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(currencyList: List<CurrencyInfo>)

}
