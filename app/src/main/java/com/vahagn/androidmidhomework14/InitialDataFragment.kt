package com.vahagn.androidmidhomework14

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.vahagn.androidmidhomework14.databinding.FragmentInitialDataBinding
import java.lang.Exception

class InitialDataFragment : Fragment() {
    private var _binding: FragmentInitialDataBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInitialDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        binding.okButton.setOnClickListener { ok() }
    }

    private fun ok() {
        try {
            val capacity = binding.capacityEdit.text.toString().toInt()
            val initialNumber = binding.countEdit.text.toString().toInt()

            if(initialNumber >= capacity) {
                Toast.makeText(requireContext(), "Initial Number nust be less than capacity!", Toast.LENGTH_SHORT).show()
                return
            }

            if (initialNumber == 0) {
                Toast.makeText(requireContext(), "Initial Number can't be 0!", Toast.LENGTH_SHORT).show()
                return
            }

            MainAppFragment.initialCount = initialNumber
            MainAppFragment.currentCount = initialNumber
            MainAppFragment.capacity = capacity

            findNavController().navigate(R.id.goToMainApp)
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Invalid input!", Toast.LENGTH_SHORT).show()
        }
    }
}