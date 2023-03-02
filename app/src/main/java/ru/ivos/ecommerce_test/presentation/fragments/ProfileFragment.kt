package ru.ivos.ecommerce_test.presentation.fragments

import android.graphics.BitmapFactory
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
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.ivos.ecommerce_test.R
import ru.ivos.ecommerce_test.databinding.FragmentProfileBinding
import ru.ivos.ecommerce_test.domain.models.local.User
import ru.ivos.ecommerce_test.presentation.MainActivity
import ru.ivos.ecommerce_test.presentation.viewmodels.UserViewModel
import ru.ivos.ecommerce_test.utils.bitmap

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding
        get() = _binding ?: throw RuntimeException("Binding is empty")

    private val viewModel by viewModels<UserViewModel>()

    private lateinit var photo: CircleImageView
    private lateinit var user: User

    private val contract = registerForActivityResult(ActivityResultContracts.GetContent()) {
        if (it != null) {
            photo.setImageURI(it)
            val bit = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, it)
            bitmap = bit
            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.updateUserPhoto(
                    user,
                    bit
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
        getUser()
        binding.tvNameProfilePage.text = viewModel.getCurrentUserName()
    }

    private fun setupClickListeners() {
        binding.tvChangePhotoProfilePage.setOnClickListener {
            contract.launch("image/*")
        }
        binding.llLogOutProfilePage.setOnClickListener {
            viewModel.setIsUserSignedIn(false)
//            viewModel.deleteUser(user.id)
            viewModel.updateUserPhoto(user, null)
            findNavController().navigate(R.id.action_profileFragment_to_loginRegFragment)
            (activity as MainActivity).setBottomNavInvisible()
            bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_dude_from_flash_item)
        }
    }

    private fun getUser() {
        val userFullName = viewModel.getCurrentUserName()
        val userFirstName = userFullName?.substringBefore(" ")
        viewModel.getUser(userFirstName!!)
        viewModel.currentUser.observe(viewLifecycleOwner) {
            user = it!!
            if (user.bitmap != null) {
                photo.setImageBitmap((user.bitmap))
//                Glide.with(photo).load(user.bitmap).circleCrop().into(photo)
            } else {
                Glide.with(photo).load(R.drawable.ic_dude_from_flash_item).circleCrop().into(photo)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}