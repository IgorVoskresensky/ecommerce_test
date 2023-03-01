package ru.ivos.ecommerce_test.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.ivos.ecommerce_test.R
import ru.ivos.ecommerce_test.databinding.ActivityMainBinding
import ru.ivos.ecommerce_test.presentation.fragments.LoginRegFragment
import ru.ivos.ecommerce_test.presentation.viewmodels.UserViewModel
import ru.ivos.ecommerce_test.utils.gone
import ru.ivos.ecommerce_test.utils.visible

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var navController: NavController

    private val viewModel by viewModels<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_splash)

        val isUserSignedIn = viewModel.getIsUserSignedIn()

        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            setContentView(binding.root)

            val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.fragmentContainer) as NavHostFragment
            navController = navHostFragment.navController
            binding.bnvMain.setupWithNavController(navController)

            if(isUserSignedIn != true) {
                navController.navigate(R.id.action_pageOneFragment_to_loginRegFragment)
                binding.bnvMain.gone()
            }
        }
    }

    fun setBottomNavVisible() {
        binding.bnvMain.visible()
    }

    fun setBottomNavInvisible() {
        binding.bnvMain.gone()
    }
}