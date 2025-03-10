package com.aeryz.animeapps.presentation.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.aeryz.animeapps.databinding.FragmentSearchBinding
import com.aeryz.core.data.source.remote.Resource
import com.aeryz.core.ui.AnimeAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

   private var _binding: FragmentSearchBinding? = null
   private val binding get() = _binding!!
   private val searchViewModel: SearchViewModel by viewModel()
   private lateinit var navController: NavController
   private val animeAdapter: AnimeAdapter = AnimeAdapter()

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentSearchBinding.inflate(inflater, container, false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      navController = Navigation.findNavController(view)
      observeAnime()
      getAnimeDetail()
   }

   private fun observeAnime() {
      binding.searchView.setOnQueryTextListener(object: OnQueryTextListener {
         override fun onQueryTextSubmit(query: String?): Boolean {
            return false
         }

         override fun onQueryTextChange(newText: String?): Boolean {
            viewLifecycleOwner.lifecycleScope.launch {
               searchViewModel.searchAnime(newText!!).collect { anime ->
                  anime.let {
                     when (anime) {
                        is Resource.Loading -> {
                           with(binding) {
                              lottieLoading.visibility = View.VISIBLE
                              lottieLoading.playAnimation()
                              searchPlaceholder.root.visibility = View.GONE
                           }
                        }
                        is Resource.Success -> {
                           with(binding) {
                              lottieLoading.cancelAnimation()
                              searchPlaceholder.root.visibility = View.GONE
                              rvSearchResult.apply {
                                 adapter = animeAdapter
                                 layoutManager = GridLayoutManager(context, 2)
                                 setHasFixedSize(true)
                                 animeAdapter.setData(anime.data!!)
                              }
                           }
                        }
                        is Resource.Error -> {
                           with(binding) {
                              lottieLoading.cancelAnimation()
                              searchPlaceholder.root.visibility = View.GONE
                           }
                        }
                     }
                  }
               }
            }
            return true
         }
      })
   }

   private fun getAnimeDetail() {
      animeAdapter.setOnItemClickCallback(object: AnimeAdapter.OnItemClickCallback {
         override fun onItemClicked(id: Int) {
            val action = SearchFragmentDirections.actionDiscoverFragmentToAnimeDetailFragment()
            action.id = id
            Log.d(TAG, "Id: $id")
            navController.navigate(action)
         }
      })
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }

   companion object {
      const val TAG = "SearchFragment"
   }
}