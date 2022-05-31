package com.mtnine.dohwaji.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mtnine.dohwaji.model.Post
import com.mtnine.dohwaji.model.WordData
import com.mtnine.dohwaji.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MyViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    fun getRecyclerPagingData() = repository.getRecyclerPagingData().cachedIn(viewModelScope)

    fun getPostPagingData() = repository.getPostPagingData().cachedIn(viewModelScope)

    fun insertPost(post: Post) = viewModelScope.launch {
        repository.insertPost(post)
    }

    fun updatePost(post: Post) = viewModelScope.launch {
        repository.updatePost(post)
    }

    fun deletePost(post: Post) = viewModelScope.launch {
        repository.deletePost(post)
    }
}