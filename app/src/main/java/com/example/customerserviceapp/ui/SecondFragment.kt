package com.example.customerserviceapp.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.customerserviceapp.aplication.CleaningsApp
import com.example.customerserviceapp.databinding.FragmentSecondBinding
import com.example.customerserviceapp.model.CleaningS
import kotlin.reflect.typeOf

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var applicationContext: Context
    private val cleaningsViewModel: CleaningsViewModel by viewModels {
        CleaningsViewModelFactory((applicationContext as CleaningsApp).repository)
    }
    private val args : SecondFragmentArgs by navArgs()
    private var cleanings : CleaningS? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        applicationContext = requireContext().applicationContext
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cleanings = args.cleanings
        // kita cek jika null maka tampilan default nambah cleaning
        // jika cleanings tidak null tampilan sedikit berubah ada tombol hapus dan cleaning
        if (cleanings !=null){
            binding.deleteButton.visibility = View.VISIBLE
            binding.saveButton.text = "Ubah"
            binding.nameEditText.setText(cleanings?.name)
            binding.addressEditTextText2.setText(cleanings?.address)
            binding.typeEditText.setText(cleanings?.type)
        }
        val name= binding.nameEditText.text
        val address= binding.addressEditTextText2.text
        val type = binding.nameEditText.text
        binding.saveButton.setOnClickListener {
             //kita kasih kondisi jika nama dan alamat kosong tidak bisa menyimpan
            if (name.isEmpty()) {
                Toast.makeText(context, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }else if (address.isEmpty()) {
                Toast.makeText(context, "Alamat tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }else if (type.isEmpty()) {
                Toast.makeText(context, "Jenis Pelayanan tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else {
                if ( cleanings == null){
                    val cleanings  = CleaningS(0, name.toString(), address.toString(), type.toString())
                    cleaningsViewModel.insert(cleanings)
                }else {
                    val cleanings = CleaningS(
                        cleanings?.id!!,
                        name.toString(),
                        address.toString(),
                        type.toString()
                    )
                    cleaningsViewModel.update(cleanings)
                }

                findNavController().popBackStack()
            }
        }

        binding.deleteButton.setOnClickListener {
            cleanings?.let { cleaningsViewModel.delete(it) }
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}