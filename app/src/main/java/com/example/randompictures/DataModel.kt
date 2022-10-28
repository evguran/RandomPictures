package com.example.randompictures

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataModel: ViewModel() {
    // ne eby chto zdes' proishodit
    val messageForActivity: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val messageForFragment: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}