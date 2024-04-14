package com.hacklapenos.comunal.data.community

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CommunityViewModel: ViewModel() {

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> = _description

    private val _location = MutableLiveData<String>()
    val location: LiveData<String> = _location

    private val _showError = MutableLiveData<Boolean>()
    val showError: LiveData<Boolean> = _showError

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _selectedImageUri = MutableLiveData<String>()
    val selectedImageUri: MutableLiveData<String> = _selectedImageUri

    private val _selectedCategories = MutableLiveData<List<String>>()
    val selectedCategories: MutableLiveData<List<String>> = _selectedCategories

    fun enableSignUp(name: String, description: String, location: String) =
        name.isNotEmpty() && description.isNotEmpty() && location.isNotEmpty()

    fun onNameChanged(name: String) {
        _name.value = name
    }

    fun onDescriptionChanged(description: String) {
        _description.value = description
    }

    fun onLocationChanged(location: String) {
        _location.value = location
    }

    fun showError() {
        _showError.value = true
    }

    fun hideError() {
        _showError.value = false
    }

    fun onImageSelected(imageUri: String) {
        _selectedImageUri.value = imageUri
    }

    fun onCategorySelected(category: String) {
        _selectedCategories.value = _selectedCategories.value.orEmpty() + category
        Log.d("CommunityViewModel", "Selected categories: ${_selectedCategories.value}")
    }

    fun onCategoryUnselected(category: String) {
        _selectedCategories.value = _selectedCategories.value.orEmpty() - category
    }


}