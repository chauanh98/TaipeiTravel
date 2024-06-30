package com.example.traveltaipei

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.traveltaipei.ui.detail.AttractionDetailFragment
import com.example.traveltaipei.ui.list.ListAttractionFragment
import com.example.traveltaipei.viewmodel.AttractionDataUIViewmodel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ListAttractionFragment())
                .commit()
        }
    }

    fun navigateToDetailFragment(attraction: AttractionDataUIViewmodel) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, AttractionDetailFragment.newInstance(attraction))
            .addToBackStack(null)
            .commit()
    }

    fun openWebViewFragment(url: String) {
        val intent = Intent(this, WebViewActivity::class.java)
        intent.putExtra("url", url)
        startActivity(intent)
    }

    fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
}