package com.hacklapenos.comunal.data.projects

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Locale

class ProjectViewModel : ViewModel() {
    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _goal = MutableLiveData<String>()
    val goal: LiveData<String> = _goal

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> = _description

    private val _duration = MutableLiveData<String>()
    val duration: LiveData<String> = _duration

    private val _startDate = MutableLiveData<String>()
    val startDate: LiveData<String> = _startDate

    private val _endDate = MutableLiveData<String>()
    val endDate: LiveData<String> = _endDate

    private val _initialAmount = MutableLiveData<String>()
    val initialAmount: LiveData<String> = _initialAmount

    fun onNameChanged(name: String) {
        _name.value = name
    }

    fun onDescriptionChanged(description: String) {
        _description.value = description
    }

    fun onGoalChanged(goal: String) {
        _goal.value = goal
    }

    fun onDurationChanged(duration: String) {
        _duration.value = duration
    }

    fun onStartDateChanged(startDate: String) {
        _startDate.value = startDate
    }

    fun onEndDateChanged(endDate: String) {
        _endDate.value = endDate
    }

    fun onInitialAmountChanged(initialAmount: String) {
        _initialAmount.value = initialAmount
    }

    fun enableCreateProject(
        name: String,
        description: String,
        goal: String,
        duration: String,
        startDate: String,
        endDate: String
    ) =
        name.isNotEmpty() && description.isNotEmpty() && goal.isNotEmpty() && duration.isNotEmpty() && isValid(
            startDate
        ) && isValid(endDate
}

fun isValid(date: String): Boolean {
    val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    format.isLenient = false

    return try {
        format.parse(date)
        true
    } catch (e: Exception) {
        false
    }
}