package com.quantumcode.napets.ui.pestDisease.view.adapter

import com.quantumcode.napets.data.model.PestDisease

interface PestDiseaseAdapterListener {

    fun onItemClick(pestDisease: PestDisease)
}
