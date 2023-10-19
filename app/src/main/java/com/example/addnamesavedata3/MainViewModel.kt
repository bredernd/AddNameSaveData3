package com.example.addnamesavedata3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData

class MainViewModel : ViewModel() {
    val enteredNames = mutableListOf<String>()
}
