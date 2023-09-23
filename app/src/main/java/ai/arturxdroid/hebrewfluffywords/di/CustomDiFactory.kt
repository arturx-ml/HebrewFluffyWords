package ai.arturxdroid.hebrewfluffywords.di

import ai.arturxdroid.hebrewfluffywords.ui.viewmodel.MainViewModel

object CustomDiFactory {


    private val mainVm by lazy { MainViewModel() }


    fun getMainViewModel(): MainViewModel = mainVm
}