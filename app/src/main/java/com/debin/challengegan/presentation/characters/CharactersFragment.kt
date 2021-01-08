package com.debin.challengegan.presentation.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.debin.challengegan.databinding.FragmentCharactersBinding
import com.debin.challengegan.framework.utils.BottomSheetFragment
import com.debin.challengegan.framework.utils.Resource
import com.debin.challengegan.presentation.CharactersViewModel
import kotlinx.android.synthetic.main.fragment_characters.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

private const val TAG = "CharactersFragment"
class CharactersFragment : Fragment() {

    private lateinit var adapter: CharactersAdapter
    private lateinit var binding : FragmentCharactersBinding
    private val viewModel : CharactersViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharactersBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindViews()
        initViews()
        observeData()
        observeSearchClick()
        searchCharacter()
        observeSeasonFilter()
    }

    private fun bindViews() {
        binding.characterViewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun initViews() {
        println("$TAG :: initViews")
      adapter = CharactersAdapter(arrayListOf(), CharactersAdapter.OnCharacterItemClick{
          viewModel.setCharacterDetails(it)
         findNavController().navigate(CharactersFragmentDirections.actionCharactersFragmentToDetailsFragment())
      })
      rv_characters.adapter = adapter
    }

    private fun observeSearchClick() {
        println("$TAG :: observeSearchClick")
        viewModel.searchClick.observe(viewLifecycleOwner, Observer { searchClicked->
            if(searchClicked) {
                search_view.visibility = View.VISIBLE
                viewModel.searchClicked()
            }
        })
    }

    private fun searchCharacter() {
        println("$TAG :: searchCharacter")
      search_view.setOnQueryTextListener(object :
          androidx.appcompat.widget.SearchView.OnQueryTextListener {
          override fun onQueryTextSubmit(query: String): Boolean {
              return false
          }

          override fun onQueryTextChange(newText: String): Boolean {
              adapter.filter.filter(newText)
              return false
          }

      })
    }

    private fun observeSeasonFilter() {
       viewModel.filterClick.observe(viewLifecycleOwner, Observer { filterClicked->
           if(filterClicked) {
               showBottomSheetDialog()
               viewModel.filterClicked()
           }
       })
    }


    private fun observeData() {
        println("$TAG :: observeData")
        viewModel.characterList.observe(viewLifecycleOwner, Observer { result ->
            when(result) {
                is Resource.Loading -> {
                    showProgress()
                }
                is Resource.Success -> {
                    hideProgress()
                    adapter.updateCharacters(result.result)
                }
                is Resource.Error -> {
                    //can handle error here too
                    hideProgress()
                }
            }
        })
    }

    private fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    private fun showBottomSheetDialog() {
        val bottomSheetFragment = BottomSheetFragment(BottomSheetFragment.OnFilter{seasonApperance->
            println("$TAG :: Seasons :: ${seasonApperance.size}")
            adapter.seasonBasedFilter(seasonApperance)
        })
        bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
    }

}