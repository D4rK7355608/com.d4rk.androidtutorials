package com.d4rk.androidtutorials.ui.android.clock.tabs
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.FragmentClockCodeBinding
class ClockTabCodeFragment : Fragment() {
    private lateinit var _binding: FragmentClockCodeBinding
    private val binding get() = _binding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentClockCodeBinding.inflate(inflater, container, false)
        binding.noCodeText.setText(R.string.no_kotlin_code_needed)
        return binding.root
    }
}