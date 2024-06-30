package com.example.traveltaipei.viewmodel

import android.text.SpannableString
import android.text.style.UnderlineSpan
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class AttractionDetailViewmodel: ViewModel() {
    private val _urlClicked = MutableLiveData<String>()
    val urlClicked: LiveData<String> get() = _urlClicked

    private val _link = MutableLiveData<String>()
    val link: LiveData<String> get() = _link

    fun onUrlClicked(url: String) {
        _urlClicked.value = url
    }

    fun setLink(url: String) {
        url?.let {
            val mSpannableString = SpannableString(url)
            mSpannableString.setSpan(UnderlineSpan(), 0, mSpannableString.length, 0)
            _link.value = mSpannableString.toString()
        }
    }
}