package com.omni.continuoussharedelementtransition_viewpager2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _currentPosition = MutableLiveData<Int>()
    val currentPositionLiveData: LiveData<Int>
        get() = _currentPosition

    fun updateCurrentPosition(position: Int) {
        _currentPosition.postValue(position)
    }
}