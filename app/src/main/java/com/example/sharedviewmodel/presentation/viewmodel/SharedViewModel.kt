package com.example.sharedviewmodel.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SharedViewModel: ViewModel() {

    private val _cityName = MutableStateFlow("Chennai")
    var cityName: StateFlow<String> = _cityName

    fun saveCity(city: String) {
        _cityName.value = city
    }

}