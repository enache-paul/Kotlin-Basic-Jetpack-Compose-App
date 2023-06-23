package com.example.mobilesecurityproject.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilesecurityproject.api.ApiService
import com.example.mobilesecurityproject.api.model.Wizard
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var wizardListResponse : List<Wizard> by mutableStateOf(listOf())
    var errorMessage : String by mutableStateOf("")


    init {
        getCatFact()
    }
    fun getCatFact() {
        viewModelScope.launch {
            val apiService = ApiService.getInstance()

            try {
                val wizardList =  apiService.getWizardsList()
                wizardListResponse = wizardList
            } catch (e : java.lang.Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

}