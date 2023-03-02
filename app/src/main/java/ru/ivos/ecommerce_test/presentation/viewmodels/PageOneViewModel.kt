package ru.ivos.ecommerce_test.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.ivos.ecommerce_test.domain.models.local.User
import ru.ivos.ecommerce_test.domain.models.remote.FlashSale
import ru.ivos.ecommerce_test.domain.models.remote.Latest
import ru.ivos.ecommerce_test.domain.usecases.api_usecases.GetBrandsUseCase
import ru.ivos.ecommerce_test.domain.usecases.api_usecases.GetFlashSaleUseCase
import ru.ivos.ecommerce_test.domain.usecases.api_usecases.GetLatestUseCase
import ru.ivos.ecommerce_test.domain.usecases.user_usecases.GetCurrentUserNameUseCase
import ru.ivos.ecommerce_test.domain.usecases.user_usecases.GetUserUseCase
import ru.ivos.ecommerce_test.utils.PageOneStates
import javax.inject.Inject

@HiltViewModel
class PageOneViewModel @Inject constructor(
    private val getFlashSaleUseCase: GetFlashSaleUseCase,
    private val getLatestUseCase: GetLatestUseCase,
    private val getBrandsUseCase: GetBrandsUseCase
) : ViewModel() {

    private var _state = MutableLiveData<PageOneStates>()
    val state: LiveData<PageOneStates> get() = _state

    private var listFlash = emptyList<FlashSale>()
    private var listLatest = emptyList<Latest>()
    private var listBrands = emptyList<String>()

    init {
        getData()
    }


    private fun getData() = viewModelScope.launch {
        _state.value = PageOneStates.LOADING
        kotlin.runCatching {
            listFlash = getFlashSaleUseCase.invoke()
            listLatest = getLatestUseCase.invoke()
            listBrands = getBrandsUseCase.invoke()
        }
            .onSuccess { _state.value = PageOneStates.SUCCESS(listFlash, listLatest, listBrands) }
            .onFailure { _state.value = PageOneStates.FAILURE("Error") }
    }
}