package com.example.findmyage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.loader.content.Loader
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    var flag = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        button.setOnClickListener {
            val userYear = editText.text.toString()
            if(userYear == "" ) Toast.makeText(this , "Enter year first" , Toast.LENGTH_SHORT).show()
            else {
                val currYear = Calendar.getInstance().get(Calendar.YEAR)
                val finalResult = currYear - userYear.toInt()
                if (finalResult >= 0){
                    val builder = AlertDialog.Builder(this)
                    val items = arrayOf("apple" , "banana" , "orange" , "mango" , "etc..")
                    builder.setTitle("Information")
                    builder.setMessage("Your Age is : ${finalResult.toString()}")
                    builder.setPositiveButton(android.R.string.yes){dialog, which ->
                        editText.text.clear()
                        Log.d("positiveButton" , "+ve Pressed")
                    }
                    builder.setNegativeButton("Exit"){dialog, which ->
                        exitProcess(1)
                    }
                    builder.show()
                }
                else
                    Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() {
        if(flag)
            exitProcess(1)
        flag = true
        Toast.makeText(this , "press again to exit" , Toast.LENGTH_SHORT).show()
        Handler().postDelayed(Runnable { flag = false } , 2000)
    }
}
