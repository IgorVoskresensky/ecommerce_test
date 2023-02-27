package ru.ivos.ecommerce_test.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.ivos.ecommerce_test.data.remote.ApiRepoImpl
import ru.ivos.ecommerce_test.domain.models.remote.FlashSale
import ru.ivos.ecommerce_test.domain.usecases.api_usecases.GetFlashSaleUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getFlashSaleUseCase: GetFlashSaleUseCase
) : ViewModel() {

    var _list = MutableLiveData<List<FlashSale>>()

    init {
        getFlashSale()
    }

    fun getFlashSale() = viewModelScope.launch {
        _list.value = getFlashSaleUseCase.invoke()
    }
}