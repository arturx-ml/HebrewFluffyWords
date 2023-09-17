package ai.arturxdroid.HebrewFluffyWords.di

import ai.arturxdroid.HebrewFluffyWords.ui.viewmodel.MainViewModel
import androidx.lifecycle.viewmodel.InitializerViewModelFactoryBuilder
import androidx.lifecycle.viewmodel.viewModelFactory

object CustomDiFactory {


    private val mainVm by lazy { MainViewModel() }


    fun getMainViewModel(): MainViewModel = mainVm
}