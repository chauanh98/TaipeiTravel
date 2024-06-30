package com.example.traveltaipei.ui.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.traveltaipei.MainActivity
import com.example.traveltaipei.R
import com.example.traveltaipei.data.repository.AttractionRepository
import com.example.traveltaipei.databinding.FragmentAttractionListBinding
import com.example.traveltaipei.ultils.FILTER
import com.example.traveltaipei.ultils.LANGUAGE
import com.example.traveltaipei.viewmodel.ListAttractionViewModel
import com.example.traveltaipei.viewmodel.ViewModelFactory

class ListAttractionFragment : Fragment() {

    private lateinit var binding: FragmentAttractionListBinding
    private lateinit var activity: MainActivity
    private val viewModel: ListAttractionViewModel by viewModels {
        ViewModelFactory(AttractionRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAttractionListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = AttractionAdapter { attraction ->
            activity.navigateToDetailFragment(attraction)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)

        viewModel.listItem.observe(viewLifecycleOwner) { attractions ->
            adapter.submitList(attractions.toList())
        }

        viewModel.showPopupEvent.observe(viewLifecycleOwner) {
            showPopupSetLanguage(binding.toolbar.findViewById(R.id.icon))
        }

        viewModel.showPopupFilter.observe(viewLifecycleOwner) {
            showPopupFilter(binding.toolbar.findViewById(R.id.icon))
        }

        viewModel.statusError.observe(viewLifecycleOwner) {
            if (it.isNullOrBlank().not()) {
                activity.showError(error = it)
            } else {
                activity.showError(error = "Error")
            }
        }

        setupAppBar()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as MainActivity
    }

    private fun setupAppBar() {
        binding.toolbar.title = "TaipeiTour"
    }

    private fun showPopupSetLanguage(view: View) {
        val popupMenu = PopupMenu(requireContext(), view)
        popupMenu.inflate(R.menu.menu)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.option1 -> {
                    viewModel.getAttractions(language = LANGUAGE.VI.value)
                    true
                }

                R.id.option2 -> {
                    viewModel.getAttractions(language = LANGUAGE.ZH_TW.value)
                    true
                }

                R.id.option3 -> {
                    viewModel.getAttractions(language = LANGUAGE.EN.value)
                    true
                }

                R.id.option4 -> {
                    viewModel.getAttractions(language = LANGUAGE.ZH_CN.value)
                    true
                }

                R.id.option5 -> {
                    viewModel.getAttractions(language = LANGUAGE.JA.value)
                    true
                }

                else -> true
            }
        }
        popupMenu.show()
    }

    private fun showPopupFilter(view: View) {
        val popupMenu = PopupMenu(requireContext(), view)
        popupMenu.inflate(R.menu.menu_filter)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.option1 -> {
                    viewModel.setFilter(FILTER.A_Z.value)
                    true
                }

                R.id.option2 -> {
                    viewModel.setFilter(FILTER.Z_A.value)
                    true
                }

                R.id.option3 -> {
                    viewModel.setFilter(FILTER.DISTANCE.value)
                    true
                }

                else -> false
            }
        }
        popupMenu.show()
    }
}
