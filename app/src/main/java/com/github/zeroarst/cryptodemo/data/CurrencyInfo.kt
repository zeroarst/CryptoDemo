package com.github.zeroarst.cryptodemo.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.zeroarst.cryptodemo.utils.TABLE_NAME_CURRENCIES
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = TABLE_NAME_CURRENCIES)
data class CurrencyInfo(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "symbol") val symbol: String,
) : Parcelable

