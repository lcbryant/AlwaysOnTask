package com.alwaysontask.viewModels

import androidx.lifecycle.ViewModel
import java.util.Date

private fun getToday() {

}

class MyDayViewModel : ViewModel() {
    private val _today = getToday()
}