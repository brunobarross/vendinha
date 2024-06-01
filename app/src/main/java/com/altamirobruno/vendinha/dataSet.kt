package com.altamirobruno.vendinha

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class DataSet(
    val id: Int,
    @StringRes val group: Int,
    @StringRes val name: Int,
    @DrawableRes val icon: Int



)
