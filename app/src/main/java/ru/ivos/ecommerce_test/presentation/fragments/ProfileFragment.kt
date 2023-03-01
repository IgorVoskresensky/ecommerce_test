package ru.ivos.ecommerce_test.presentation.fragments

import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.ivos.ecommerce_test.R
import ru.ivos.ecommerce_test.databinding.FragmentProfileBinding
import ru.ivos.ecommerce_test.domain.models.local.User
import ru.ivos.ecommerce_test.presentation.MainActivity
import ru.ivos.ecommerce_test.presentation.viewmodels.UserViewModel

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding
        get() = _binding ?: throw RuntimeException("Binding is empty")

    private val viewModel by viewModels<UserViewModel>()

    private lateinit var photo: CircleImageView
    private lateinit var user: User

    private val contract = registerForActivityResult(ActivityResultContracts.GetContent()) {
        photo.setImageURI(it)
        if (it != null) {
            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.updateUserPhoto(
                    user,
                    MediaStore.Images.Media.getBitmap(requireContext().contentResolver, it)
                )
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        photo = binding.ivAvatarProfilePage

        val string = viewModel.getCurrentUserName()
        val name = string?.substringBefore(" ")
        viewModel.getUser(name!!)
        viewModel.currentUser.observe(viewLifecycleOwner) {
            user = it!!
            if (user.bitmap != null) {
                photo.setImageBitmap((user.bitmap))
//                Glide.with(photo).load(user.bitmap).circleCrop().into(photo)
            }
        }
    }

    private fun setupClickListeners() {
        binding.tvChangePhotoProfilePage.setOnClickListener {
            contract.launch("image/*")
        }
        binding.llLogOutProfilePage.setOnClickListener {
            viewModel.setIsUserSignedIn(false)
            viewModel.deleteUser(user.id)
            findNavController().navigate(R.id.action_profileFragment_to_loginRegFragment)
            (activity as MainActivity).setBottomNavInvisible()
        }

        binding.tvNameProfilePage.text = viewModel.getCurrentUserName()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}