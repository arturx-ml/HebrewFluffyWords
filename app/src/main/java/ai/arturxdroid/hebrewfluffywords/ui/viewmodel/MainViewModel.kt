package ai.arturxdroid.hebrewfluffywords.ui.viewmodel

import ai.arturxdroid.hebrewfluffywords.data.Image
import ai.arturxdroid.hebrewfluffywords.data.RetrofitProvider
import ai.arturxdroid.hebrewfluffywords.ui.MainScreenState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val innerImagesState: MutableLiveData<MainScreenState> = MutableLiveData()
    private val api = RetrofitProvider.getOpenApi()

    val screenState: LiveData<MainScreenState> = innerImagesState

    fun fetchImages(prompt: String) {
        viewModelScope.launch {
            val response = api.getImageFromPrompt(prompt)
            val body = response.body()
            if (response.isSuccessful && !body.isNullOrEmpty()) {
                innerImagesState.postValue(MainScreenState(body))
            }
        }
    }


}