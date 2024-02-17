package com.quantumcode.napets.ui.pestDisease.view.adapter

import com.quantumcode.napets.data.model.pestDisease.PestDisease

interface PestDiseaseAdapterListener {

    fun onItemClick(pestDisease: PestDisease)
}
