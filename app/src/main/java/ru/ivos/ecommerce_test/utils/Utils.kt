package ru.ivos.ecommerce_test.utils

import android.graphics.Bitmap
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import ru.ivos.ecommerce_test.utils.Constants.MY_LOG_TAG

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



