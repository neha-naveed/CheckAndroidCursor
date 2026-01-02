package com.example.translator.ui.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.translator.core.bases.dialog.BaseDialogFragment
import com.example.translator.databinding.FragmentCommonDialogBinding

class CommonDialogFragment : BaseDialogFragment() {
    private var _binding: FragmentCommonDialogBinding? = null
    private val binding get() = _binding!!

    override fun getLayoutResId(): Int {
        // Not used when using ViewBinding
        return 0
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommonDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initViews() {
        // Setup dialog content
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

