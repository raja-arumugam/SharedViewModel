package com.example.sharedviewmodel.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.sharedviewmodel.R
import com.example.sharedviewmodel.databinding.FragmentFirstBinding
import com.example.sharedviewmodel.presentation.viewmodel.SharedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding

    // Here we can scope the viewModel instance to the activity (not fragment).
    // So the viewModel will lives as long as activity lives.
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_first, container, false)

        binding.toolbarTitle.text = resources.getString(R.string.first_fragment)

        CoroutineScope(Dispatchers.Main).launch {
            sharedViewModel.cityName.collectLatest { city ->
                binding.etCity1.setText(city)
            }
        }

        binding.btDone1.setOnClickListener {
            sharedViewModel.saveCity(binding.etCity1.text.toString())
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }

        return binding.root
    }

}