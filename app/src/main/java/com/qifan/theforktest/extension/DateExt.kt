package com.qifan.theforktest.extension

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun getCurrentDate(formatDate: String = "dd/MM/yyyy"): String {
    return with(SimpleDateFormat(formatDate))
    {
        format(Date())
    }
}