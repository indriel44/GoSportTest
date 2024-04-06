package com.example.gosporttest

import android.app.Application
import io.paperdb.Paper

class AppAplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Paper.init(applicationContext);
    }


}