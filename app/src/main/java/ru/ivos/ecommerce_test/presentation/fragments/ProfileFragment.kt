package ru.ivos.ecommerce_test.presentation.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import de.hdodenhof.circleimageview.CircleImageView
import ru.ivos.ecommerce_test.R
import ru.ivos.ecommerce_test.databinding.FragmentPageTwoBinding
import ru.ivos.ecommerce_test.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding
        get() = _binding ?: throw RuntimeException("Binding is empty")

    private lateinit var photo: CircleImageView
    private var uri: Uri? = null

    private val contract = registerForActivityResult(ActivityResultContracts.GetContent()){
        photo.setImageURI(it)
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
        photo = binding.ivAvatarProfilePage
        binding.tvChangePhotoProfilePage.setOnClickListener {
            contract.launch("image/*")
        }
    }
}