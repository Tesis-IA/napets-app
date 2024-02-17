package com.quantumcode.napets.ui.home.adapter

import com.quantumcode.napets.data.model.home.Subject

interface HomeSubjectAdapterListener {
    fun onSelectedItem(subject: Subject)
}
