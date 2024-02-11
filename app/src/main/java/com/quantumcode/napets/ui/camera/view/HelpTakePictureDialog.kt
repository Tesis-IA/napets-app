package com.quantumcode.napets.ui.camera.view

import com.quantumcode.napets.databinding.FragmentHelpTakePictureDialogBinding
import com.quantumcode.napets.ui.base.BaseDialog

class HelpTakePictureDialog : BaseDialog<FragmentHelpTakePictureDialogBinding>() {

    override fun getViewBinding() = FragmentHelpTakePictureDialogBinding.inflate(layoutInflater)

    override fun setListeners() {
        super.setListeners()
        binding.helpTakePictureDialogButton.setOnClickListener { dismiss() }
    }

    companion object {
        fun newInstance() = HelpTakePictureDialog()
    }
}