package ru.ivos.ecommerce_test.utils

import ru.ivos.ecommerce_test.domain.models.remote.Details
import ru.ivos.ecommerce_test.domain.models.remote.FlashSale
import ru.ivos.ecommerce_test.domain.models.remote.Latest

sealed class PageOneStates {
    object LOADING : PageOneStates()
    class FAILURE(val message: String) : PageOneStates()
    class SUCCESS(
        val listFlash: List<FlashSale>,
        val listLatest: List<Latest>,
        val listBrands: List<String>
        ) : PageOneStates()
}

sealed class PageTwoStates {
    object LOADING : PageTwoStates()
    class FAILURE(val message: String) : PageTwoStates()
    class SUCCESS(val details: Details) : PageTwoStates()
}