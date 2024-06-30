package com.example.traveltaipei.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.traveltaipei.data.model.ResultData
import com.example.traveltaipei.data.repository.AttractionRepository
import com.example.traveltaipei.event.SingleLiveEvent
import com.example.traveltaipei.ultils.FILTER
import com.example.traveltaipei.ultils.LANGUAGE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListAttractionViewModel(private val repository: AttractionRepository) : ViewModel() {

    private val _listItem = MutableLiveData<Array<AttractionDataUIViewmodel>>()
    val listItem: LiveData<Array<AttractionDataUIViewmodel>>
        get() = _listItem

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _showPopupEvent = SingleLiveEvent<Unit>()
    val showPopupEvent: LiveData<Unit>
        get() = _showPopupEvent

    private val _statusError = MutableLiveData<String>()
    val statusError: LiveData<String>
        get() = _statusError

    private val _showPopupFilter = SingleLiveEvent<Unit>()
    val showPopupFilter: LiveData<Unit>
        get() = _showPopupFilter

    init {
        getAttractions(LANGUAGE.EN.value)
    }

    fun getAttractions(language: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            repository.getAttractionsFlow(categoryIds = "12,34,124", page = 1, lang = language)
                .collect { result ->
                    when (result) {
                        is ResultData.Success -> {
                            val mapList = result.data?.data?.map { item ->
                                AttractionDataUIViewmodel.mapToUI(item)
                            }
                            _listItem.postValue(mapList?.filter { item -> item.isVisibility }
                                ?.toTypedArray())
                            _isLoading.postValue(false)
                        }

                        is ResultData.Error -> {
                            Log.e("AttractionError", "Error ${result.code}: ${result.message}")
                            _statusError.postValue(result.message.orEmpty())
                            _isLoading.postValue(false)
                        }
                    }
                }
        }
    }

    fun onLanguageClick() {
        _showPopupEvent.call()
    }

    fun onFilterClick() {
        _showPopupFilter.call()
    }

    fun setFilter(value: String) {
        val data = _listItem.value
        when (value) {
            FILTER.A_Z.value -> {
                data?.sortWith(
                    compareBy(String.CASE_INSENSITIVE_ORDER) { it.name }
                )
            }

            FILTER.Z_A.value -> {
                data?.sortWith(
                    compareByDescending(String.CASE_INSENSITIVE_ORDER) { it.name }
                )
            }

            else -> {
                data?.sortBy { it.distance }
            }
        }
        data?.let {
            _listItem.value = it
        }
    }
}