package ru.ivos.ecommerce_test.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.ivos.ecommerce_test.domain.usecases.api_usecases.GetDetailsUseCase
import ru.ivos.ecommerce_test.utils.PageOneStates
import ru.ivos.ecommerce_test.utils.PageTwoStates
import javax.inject.Inject

@HiltViewModel
class PageTwoViewModel @Inject constructor(
    private val getDetailsUseCase: GetDetailsUseCase
) : ViewModel() {

    private var _state = MutableLiveData<PageTwoStates>()
    val state: LiveData<PageTwoStates> get() = _state

    init {
        getDetails()
    }

    private fun getDetails() = viewModelScope.launch {
        _state.value = PageTwoStates.LOADING
        kotlin.runCatching {
            getDetailsUseCase.invoke()
        }
            .onSuccess { _state.value = PageTwoStates.SUCCESS(it) }
            .onFailure { _state.value = PageTwoStates.FAILURE("Error") }
    }
}