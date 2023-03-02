package ru.ivos.ecommerce_test.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import ru.ivos.ecommerce_test.R
import ru.ivos.ecommerce_test.domain.models.local.User
import ru.ivos.ecommerce_test.presentation.viewmodels.UserViewModel
import ru.ivos.ecommerce_test.utils.bitmap
import ru.ivos.ecommerce_test.utils.showLog

class SplashFragment : Fragment() {

    private val viewModel by viewModels<UserViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}