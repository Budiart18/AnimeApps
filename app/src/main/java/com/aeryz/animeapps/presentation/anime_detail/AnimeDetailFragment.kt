package com.aeryz.animeapps.presentation.anime_detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.aeryz.animeapps.R
import com.aeryz.animeapps.databinding.FragmentAnimeDetailBinding
import com.aeryz.core.data.source.remote.Resource
import com.aeryz.core.domain.model.Anime
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AnimeDetailFragment : Fragment() {

   private var _binding: FragmentAnimeDetailBinding? = null
   private val binding get() = _binding!!
   private lateinit var navController: NavController
   private val animeDetailViewModel: AnimeDetailViewModel by viewModel()
   private val args: AnimeDetailFragmentArgs by navArgs()
   private var status = false

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentAnimeDetailBinding.inflate(inflater, container, false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      navController = Navigation.findNavController(view)
      binding.toolbar.setNavigationOnClickListener { navController.navigateUp() }
      isFavourite()
      getAnimeData()
   }

   @SuppressLint("SetTextI18n")
   private fun getAnimeData() {
      val id = args.id
      animeDetailViewModel.getAnimeDetail(id)
      viewLifecycleOwner.lifecycleScope.launch {
         animeDetailViewModel.animeData.collect { anime ->
            anime?.let {
               val animeData = anime.data
               when (anime) {
                  is Resource.Loading -> {
                     Log.d(TAG, "LOADING")
                     with(binding) {
                        lottieLoading.visibility = View.VISIBLE
                        lottieLoading.playAnimation()
                        cardAnime.visibility = View.GONE
                        tvAnimeTitle.visibility = View.GONE
                        tvDesc.visibility = View.GONE
                        tvRating.visibility = View.GONE
                        tvYearDurScore.visibility = View.GONE
                     }
                  }
                  is Resource.Success -> {
                     if (animeData != null) {
                        insertAnimeToDatabase(animeData)
                     }
                     Log.d(TAG, "SUCCESS")
                     val year = anime.data?.year
                     val duration = anime.data?.duration
                     val score = anime.data?.score

                     with(binding) {
                        Glide.with(ivAnime)
                           .load(anime.data?.images)
                           .error(com.aeryz.core.R.drawable.noimage)
                           .into(ivAnime)
                        tvAnimeTitle.text = anime.data?.title
                        tvDesc.text = anime.data?.synopsis
                        tvRating.text = anime.data?.rating
                        tvYearDurScore.text = "$year | $duration | $score"

                        lottieLoading.visibility = View.GONE
                        lottieLoading.cancelAnimation()
                        cardAnime.visibility = View.VISIBLE
                        tvAnimeTitle.visibility = View.VISIBLE
                        tvDesc.visibility = View.VISIBLE
                        tvRating.visibility = View.VISIBLE
                        tvYearDurScore.visibility = View.VISIBLE

                        icFavourite.setOnClickListener {
                           status = !status
                           animeDetailViewModel.setAnimeFavourite(animeData!!, status)
                           if (!status) {
                              icFavourite.setImageResource(R.drawable.favourite_stroke_rounded)
                           } else {
                              icFavourite.setImageResource(R.drawable.favourite_fill_rounded)
                           }
                        }
                     }
                  }
                  is Resource.Error -> {
                     Log.d(TAG, "ERROR")
                     with(binding) {
                        lottieLoading.visibility = View.GONE
                        lottieLoading.cancelAnimation()
                        cardAnime.visibility = View.VISIBLE
                        tvAnimeTitle.visibility = View.VISIBLE
                        tvDesc.visibility = View.VISIBLE
                        tvRating.visibility = View.VISIBLE
                        tvYearDurScore.visibility = View.VISIBLE
                     }
                  }
               }
            }
         }
      }
   }

   private fun isFavourite() {
      viewLifecycleOwner.lifecycleScope.launch {
         animeDetailViewModel.animeFavourite.collect { anime ->
            status = anime.any { it.id == args.id }
            if (!status) {
               binding.icFavourite.setImageResource(R.drawable.favourite_stroke_rounded)
            } else {
               binding.icFavourite.setImageResource(R.drawable.favourite_fill_rounded)
            }
         }
      }
   }

   private fun insertAnimeToDatabase(animeData: Anime) {
      viewLifecycleOwner.lifecycleScope.launch {
         animeDetailViewModel.animeListInDb.collect { anime ->
            anime.let { resource ->
               when (anime) {
                  is Resource.Success -> {
                     val isAnimeInDatabase = resource.data?.any { it.id == args.id }
                     Log.d(TAG, "insertAnimeToDatabase: $isAnimeInDatabase")
                     if (isAnimeInDatabase == false) {
                        animeDetailViewModel.insertFavoriteAnime(animeData, false)
                     }
                  }
                  is Resource.Error -> {}
                  is Resource.Loading -> {}
               }
            }
         }
      }
   }
   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }

   companion object {
      const val TAG = "DetailAnime"
   }
}