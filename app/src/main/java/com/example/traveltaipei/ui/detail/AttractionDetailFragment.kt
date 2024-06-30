package com.example.traveltaipei.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.traveltaipei.MainActivity
import com.example.traveltaipei.R
import com.example.traveltaipei.databinding.FragmentAttractionDetailBinding
import com.example.traveltaipei.viewmodel.AttractionDataUIViewmodel
import com.example.traveltaipei.viewmodel.AttractionDetailViewmodel

class AttractionDetailFragment : Fragment() {

    private lateinit var binding: FragmentAttractionDetailBinding
    private lateinit var attraction: AttractionDataUIViewmodel
    private val viewModel: AttractionDetailViewmodel by viewModels()

    companion object {
        private const val ARG_ATTRACTION = "attraction"

        fun newInstance(attraction: AttractionDataUIViewmodel): AttractionDetailFragment {
            val fragment = AttractionDetailFragment()
            val bundle = Bundle()
            bundle.putParcelable(ARG_ATTRACTION, attraction)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAttractionDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        attraction = arguments?.getParcelable(ARG_ATTRACTION)!!
        binding.attraction = attraction
        binding.viewModel = viewModel

        setupAppBar()
        setupViewPager(attraction = attraction)
        viewModel.setLink(attraction.url)

        viewModel.urlClicked.observe(viewLifecycleOwner) { url ->
            url?.let {
                (activity as MainActivity).openWebViewFragment(url = url)
            }
        }

        viewModel.link.observe(viewLifecycleOwner) { url ->
            url?.let {
                binding.url.text = it
            }
        }

        return binding.root
    }

    private fun setupAppBar() {
        binding.toolbar.title = attraction.name
        binding.toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24)
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun setupViewPager(attraction: AttractionDataUIViewmodel) {
        val images = attraction.image
        val adapter = ImagePagerAdapter(images)
        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager, true)
    }
}
