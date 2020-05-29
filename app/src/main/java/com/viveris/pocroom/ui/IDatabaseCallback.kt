package com.viveris.pocroom.ui

interface IDatabaseCallback {

    fun insertSuccess()
    fun insertFailure()
    fun queryAllSuccess()
    fun queryAllFailure()
    fun querySucess()
    fun queryFailure()
    fun deleteSuccess()
    fun deleteFailure()
}