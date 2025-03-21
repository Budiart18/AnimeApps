package com.aeryz.animeapps.presentation.profile

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aeryz.animeapps.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

   private var _binding: FragmentProfileBinding? = null
   private val binding get() = _binding!!

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      // Inflate the layout for this fragment
      _binding = FragmentProfileBinding.inflate(inflater, container, false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      changeLanguage()
   }

   private fun changeLanguage(){
      binding.tvChangeLanguage.setOnClickListener {
         startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
      }
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }
}