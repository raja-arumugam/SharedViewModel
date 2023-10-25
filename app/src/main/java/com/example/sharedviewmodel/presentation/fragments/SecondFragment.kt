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
import com.example.sharedviewmodel.databinding.FragmentSecondBinding
import com.example.sharedviewmodel.presentation.viewmodel.SharedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding

    // Here we can scope the viewModel instance to the activity (not fragment).
    // So the viewModel will lives as long as activity lives.
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_second, container, false)

        binding.toolbarTitle.text = resources.getString(R.string.second_fragment)

        CoroutineScope(Dispatchers.Main).launch {
            sharedViewModel.cityName.collectLatest { city ->
                binding.etCity2.setText(city)
            }
        }

        binding.btDone2.setOnClickListener {
            sharedViewModel.saveCity(binding.etCity2.text.toString())
            findNavController().navigate(R.id.action_secondFragment_to_firstFragment)
        }

        return binding.root

    }


}