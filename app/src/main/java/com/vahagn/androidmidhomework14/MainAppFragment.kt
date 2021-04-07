package com.vahagn.androidmidhomework14

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.vahagn.androidmidhomework14.databinding.FragmentMainAppBinding
import kotlinx.coroutines.*
import kotlin.properties.Delegates

class MainAppFragment : Fragment() {
    private var _binding: FragmentMainAppBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainAppBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.currentCountText.text = currentCount.toString()
        binding.putButton.setOnClickListener { put() }
        binding.takeButton.setOnClickListener { take() }
        binding.resetButton.setOnClickListener { reset() }
    }

    private fun reset() {
        viewLifecycleOwner.lifecycleScope.launch {
                currentCount = initialCount

                withContext(Dispatchers.Main) {
                binding.resetButton.visibility = View.GONE
                binding.actionButtons.visibility = View.VISIBLE

                binding.currentCountText.text = currentCount.toString()
            }
        }
    }

    private fun take() {
        viewLifecycleOwner.lifecycleScope.launch {
            currentCount -= 1

            withContext(Dispatchers.Main) {
                if(currentCount == 0) {
                    binding.actionButtons.visibility = View.INVISIBLE
                    binding.resetButton.visibility = View.VISIBLE

                    Toast.makeText(requireContext(), "Box is Empty!", Toast.LENGTH_SHORT).show()
                }

                binding.currentCountText.text = currentCount.toString()
            }
        }
    }

    private fun put() {
        viewLifecycleOwner.lifecycleScope.launch {
            currentCount += 1

            withContext(Dispatchers.Main) {
                if(currentCount == capacity) {
                    binding.actionButtons.visibility = View.INVISIBLE
                    binding.resetButton.visibility = View.VISIBLE

                    Toast.makeText(requireContext(), "Box is full!", Toast.LENGTH_SHORT).show()
                }

                binding.currentCountText.text = currentCount.toString()
            }
        }
    }

    companion object {
        var capacity by Delegates.notNull<Int>()
        var initialCount by Delegates.notNull<Int>()
        var currentCount by Delegates.notNull<Int>()
    }
}