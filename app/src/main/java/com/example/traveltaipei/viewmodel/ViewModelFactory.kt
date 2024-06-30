package com.example.traveltaipei.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.traveltaipei.data.repository.AttractionRepository

class ViewModelFactory(private val repository: AttractionRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListAttractionViewModel(repository) as T
    }
}