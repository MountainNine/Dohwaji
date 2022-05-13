package com.mtnine.dohwaji.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mtnine.dohwaji.model.WordData
import com.mtnine.dohwaji.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MyViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    private val _getWord = MutableLiveData<WordData>()
    val getWord: LiveData<WordData> = _getWord

    fun getWord() = viewModelScope.launch {
        val wordData = repository.getWord()
        _getWord.postValue(wordData)
    }
}