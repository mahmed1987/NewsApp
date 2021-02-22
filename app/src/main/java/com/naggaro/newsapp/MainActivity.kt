package com.naggaro.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.IntDef
import androidx.annotation.StringDef
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        onlyTakesOneTwos(6) // shouldn't this through a compile error ?
    }


    fun onlyTakesOneTwos(@OnlyOneTwo input:Int)
    {

    }
}

@IntDef(1,2)
@Target(
    AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class OnlyOneTwo
