package com.example.customerserviceapp.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customerserviceapp.R
import com.example.customerserviceapp.aplication.CleaningsApp
import com.example.customerserviceapp.databinding.FragmentFirstBinding
import com.example.customerserviceapp.model.CleaningS

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private lateinit var applicationContext: Context
    private val cleaningsViewModel: CleaningsViewModel by viewModels {
        CleaningsViewModelFactory((applicationContext as CleaningsApp).repository)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        applicationContext = requireContext().applicationContext
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CleaningsListAdapter { cleanings ->

            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(cleanings)
            findNavController().navigate(action)
        }

        binding.dataRecyclerView.adapter = adapter
        binding.dataRecyclerView.layoutManager = LinearLayoutManager(context)
        cleaningsViewModel.allCleaningS.observe(viewLifecycleOwner) { cleanings ->
            cleanings.let {
                if (cleanings.isEmpty()){
                    binding.emptyTextView.visibility = View.VISIBLE
                    binding.illustrationimageView.visibility = View.VISIBLE
                }else{
                    binding.emptyTextView.visibility = View.GONE
                    binding.illustrationimageView.visibility = View.GONE
                }
                adapter.submitList(cleanings)
            }
        }
        binding.addFAB.setOnClickListener {

            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(null)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}