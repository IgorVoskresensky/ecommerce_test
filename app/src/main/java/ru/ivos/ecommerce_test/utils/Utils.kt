package ru.ivos.ecommerce_test.utils

import android.graphics.Bitmap
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import ru.ivos.ecommerce_test.domain.models.local.Favorite
import ru.ivos.ecommerce_test.domain.models.remote.Details
import ru.ivos.ecommerce_test.domain.constants.Constants.MY_LOG_TAG

var bitmap: Bitmap? = null

fun Fragment.showLongToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
}

fun Fragment.showShortToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun Fragment.showLog(message: String) {
    Log.d(MY_LOG_TAG, message)
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun mapDetailsToFavorite(details: Details): Favorite = with(details) {
    return Favorite(
        name = name,
        description = description,
        rating = rating,
        numberOfReviews = numberOfReviews,
        price = price,
        colors = colors,
        imageUrls = imageUrls
    )
}



