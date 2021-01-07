package com.debin.challengegan.presentation.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.debin.challengegan.databinding.FragmentCharactersBinding
import com.debin.challengegan.framework.utils.Resource
import com.debin.challengegan.presentation.CharactersViewModel
import kotlinx.android.synthetic.main.fragment_characters.*
import org.koin.android.viewmodel.ext.android.sharedViewModel


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
    }

    private fun bindViews() {
        binding.characterViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun initViews() {
      adapter = CharactersAdapter(arrayListOf(), CharactersAdapter.OnCharacterItemClick{
          viewModel.setCharacterDetails(it)
         findNavController().navigate(CharactersFragmentDirections.actionCharactersFragmentToDetailsFragment())
      })
      rv_characters.adapter = adapter
    }

    private fun observeData() {
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

}