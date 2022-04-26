package com.github.zeroarst.cryptodemo.ui.currencylist

import android.app.Application
import androidx.lifecycle.*
import com.github.zeroarst.cryptodemo.data.AppDatabase
import com.github.zeroarst.cryptodemo.data.CurrencyInfo
import com.github.zeroarst.cryptodemo.data.CurrencyInfoRepository
import com.github.zeroarst.cryptodemo.data.DataSortingOrder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CurrencyListViewModel(
    application: Application,
) : ViewModel() {

    private val currencyInfoRepository: CurrencyInfoRepository

    init {
        val db = AppDatabase.getInstance(application)
        val dao = db.currencyInfoDao()
        currencyInfoRepository = CurrencyInfoRepository(dao)
    }

    val currencies: LiveData<List<CurrencyInfo>> = MutableLiveData()
    val currenciesSortDirection: LiveData<DataSortingOrder> = MutableLiveData()
    val uiState: LiveData<UIState> = MutableLiveData()

    enum class UIState {
        LOADING, SHOW_DATA,
    }

    fun loadData(sorting: Boolean = false) {
        if (sorting) {
            currenciesSortDirection as MutableLiveData
            currenciesSortDirection.value = when (currenciesSortDirection.value) {
                DataSortingOrder.ASC -> DataSortingOrder.DESC
                DataSortingOrder.DESC, null -> DataSortingOrder.ASC
            }
        }
        uiState as MutableLiveData
        uiState.value = UIState.LOADING
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000) // pretend taking some time to load.
            currencies as MutableLiveData
            currencies.postValue(currencyInfoRepository.getCurrencies(currenciesSortDirection.value))
            uiState.postValue(UIState.SHOW_DATA)
        }
    }
}

class CurrencyListViewModelFactory(private val application: Application) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = CurrencyListViewModel(application) as T
}
