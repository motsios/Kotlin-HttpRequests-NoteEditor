package com.example.notekotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.kittinunf.fuel.httpPost

import org.json.JSONObject


class NewNoteActivity : AppCompatActivity() {

    private var ettitle: EditText? = null
    private var etcontent: EditText? = null
    private var btnStore: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)

        ettitle = findViewById(R.id.title) as EditText
        etcontent = findViewById(R.id.content) as EditText
        btnStore = findViewById(R.id.btnstore) as Button

        btnStore!!.setOnClickListener {

            val jsonobj = JSONObject()
            //  val JSON = "application/json; charset=utf-8".toMediaTypeOrNull()
            jsonobj.put("title", ettitle!!.text.toString())
            jsonobj.put("content", etcontent!!.text.toString())

            val num1=ettitle!!.text.toString()
            val num2=etcontent!!.text.toString()

           Urls.geturl.httpPost().header("Content-Type" to "application/json").jsonBody("{ \"title\" : \"${num1}\" , \"content\" : \"${num2}\"}")
                .response { request, response, result ->
                //Ought to be a Success!
            }
            Toast.makeText(this, "Stored Successfully!", Toast.LENGTH_SHORT).show()
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
          }
    }
}


