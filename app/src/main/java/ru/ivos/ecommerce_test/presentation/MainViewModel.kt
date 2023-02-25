package ru.ivos.ecommerce_test.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.ivos.ecommerce_test.data.remote.ApiRepoImpl
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiRepoImpl: ApiRepoImpl
) : ViewModel() {

    fun getFlashSale() = viewModelScope.launch {
        apiRepoImpl.getFlashSale()
    }
}