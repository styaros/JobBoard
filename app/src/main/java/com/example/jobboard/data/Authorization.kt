package com.example.jobboard.data

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

object Authorization {

    var isEmployerFlow = MutableStateFlow(false)
    var isEmployerState = false

    fun refresh(isEmployer: Boolean) {
        isEmployerState = isEmployer
        GlobalScope.launch {
            isEmployerFlow.emit(isEmployer)
        }
    }
}