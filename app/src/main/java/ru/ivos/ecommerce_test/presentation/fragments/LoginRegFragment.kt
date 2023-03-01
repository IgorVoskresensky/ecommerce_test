package ru.ivos.ecommerce_test.presentation.fragments

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.ivos.ecommerce_test.R
import ru.ivos.ecommerce_test.databinding.FragmentLoginRegBinding
import ru.ivos.ecommerce_test.domain.models.local.User
import ru.ivos.ecommerce_test.presentation.viewmodels.UserViewModel
import ru.ivos.ecommerce_test.utils.gone
import ru.ivos.ecommerce_test.utils.showLongToast
import ru.ivos.ecommerce_test.utils.showShortToast
import ru.ivos.ecommerce_test.utils.visible

@AndroidEntryPoint
class LoginRegFragment : Fragment() {

    private var _binding: FragmentLoginRegBinding? = null
    private val binding: FragmentLoginRegBinding
        get() = _binding ?: throw RuntimeException("Binding is empty")

    private val viewModel by viewModels<UserViewModel>()

    private var userList = emptyList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getUsers()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginRegBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setupClickListeners()
        setupEditTexts()


//        binding.testEditText.setOnFocusChangeListener {_, hasFocus ->
//            if (hasFocus) {
//                binding.testTextLayout.hint = null
//            }
//            else {
//                binding.testTextLayout.hint = "parol"
//            }
//        }
    }

    private fun setupClickListeners() = with(binding) {
        tvRegOrLogSwitcher.setOnClickListener {
            showLoginElements()
        }

        btnSignInOrLogin.setOnClickListener {
            if (etEmail.isVisible) signUp() else login()
        }
    }

    private fun signUp() = with(binding) {
        if (
            etFirstName.error.isNullOrEmpty() && etInputFirstName.text!!.isNotEmpty() &&
            etLastName.error.isNullOrEmpty() && etInputLastName.text!!.isNotEmpty() &&
            etEmail.error.isNullOrEmpty() && etInputEmail.text!!.isNotEmpty() && checkEmailValid()
        ) {
            val userDoesNotExist = userList.any { it.firstName == etInputFirstName.text.toString().trim() }
            if(!userDoesNotExist) {
                val user = User(
                    firstName = etInputFirstName.text.toString().trim(),
                    lastName = etInputLastName.text.toString().trim(),
                    email = etInputEmail.text.toString().trim()
                )
                viewModel.insertUser(user)
                viewModel.setIsUserSignedIn(true)
                findNavController().navigate(R.id.action_loginRegFragment_to_pageOneFragment)
            } else {
                showLongToast("User already exist")
                etInputLastName.setText("")
                etInputEmail.setText("")
                showLoginElements()
            }
        } else {
            showErrors()
            showLongToast("Invalid fields")
        }
    }

    private fun login() = with(binding) {
        if (etFirstName.error.isNullOrEmpty() && etInputFirstName.text!!.isNotEmpty()){
            val userIsExist = userList.any { it.firstName == etInputFirstName.text.toString().trim() }
            if(userIsExist){
                viewModel.getUser(etInputFirstName.text.toString().trim())
                viewModel.setIsUserSignedIn(true)
                findNavController().navigate(R.id.action_loginRegFragment_to_pageOneFragment)
            } else {
                showLongToast("User doesn't exist")
                showSignInElements()
            }
        } else {
            showErrors()
            showLongToast("Invalid fields")
        }
    }

    private fun setupEditTexts() = with(binding) {
        etInputFirstName.doOnTextChanged { text, start, before, count ->
            if (text!!.isEmpty()) {
                etFirstName.error = "Name must not be empty"
                showShortToast("Name must not be empty")
            } else {
                etFirstName.error = null
            }
        }
        etInputLastName.doOnTextChanged { text, start, before, count ->
            if (text!!.isEmpty()) {
                etLastName.error = "Name must not be empty"
                showShortToast("Name must not be empty")
            } else {
                etLastName.error = null
            }
        }
        etInputEmail.doOnTextChanged { text, start, before, count ->
            if (text!!.isEmpty()) {
                etEmail.error = "Email must not be empty"
                showShortToast("Email must not be empty")
            } else {
                etEmail.error = null
            }
        }
        etInputEmail.setOnFocusChangeListener { view, focus ->
            if (!focus) {
                if (!checkEmailValid()) {
                    etEmail.error = "Invalid email"
                    showShortToast("Invalid email")
                } else {
                    etEmail.error = null
                }
            }
        }
    }

    private fun checkEmailValid(): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(binding.etInputEmail.text!!.trim()).matches()
    }

    private fun observeViewModel() {
        viewModel.users.observe(viewLifecycleOwner) {
            userList = it
        }
    }

    private fun showLoginElements() = with(binding) {
        etLastName.gone()
        etEmail.gone()
        llAlreadyHave.gone()
        btnSingInWithGoogle.gone()
        btnSingInWithApple.gone()
        etPassword.visible()
        btnSignInOrLogin.text = requireContext().getString(R.string.login)
        tvSignInOrLogin.text = requireContext().getString(R.string.login)
    }

    private fun showSignInElements() = with(binding) {
        etLastName.visible()
        etEmail.visible()
        llAlreadyHave.visible()
        btnSingInWithGoogle.visible()
        btnSingInWithApple.visible()
        etPassword.gone()
        btnSignInOrLogin.text = requireContext().getString(R.string.sign_in)
        tvSignInOrLogin.text = requireContext().getString(R.string.sign_in)
    }

    private fun showErrors() = with(binding) {
        if(etInputFirstName.text!!.isEmpty()) etFirstName.error = "Name must not be empty"
        if(etInputLastName.text!!.isEmpty()) etLastName.error = "Name must not be empty"
        if(etInputEmail.text!!.isEmpty()) etEmail.error = "Email must not be empty"
        if(checkEmailValid()) etEmail.error = "Invalid email"
    }
}