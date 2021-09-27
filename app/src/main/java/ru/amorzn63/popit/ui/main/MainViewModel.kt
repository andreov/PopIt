package ru.amorzn63.popit.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private lateinit var matrix:Array<Boolean> // матрица состояний пузырей

    private val mState = MutableLiveData<Array<Boolean>>() // изменяемая для работы внутри класса
    val state:LiveData<Array<Boolean>> = mState // неизменяемая для передачи во View

    init {
        initGame()
    }

    private  fun initGame(){
        matrix = Array(25) {true} // инициализация матрицы
        mState.value = matrix
    }

    fun onPopClick(index: Int) {

    }
}