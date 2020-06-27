package com.example.notekotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.github.kittinunf.fuel.httpGet
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONArray
import org.json.JSONObject
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private var listView: ListView? = null
    private var notesModelArrayList: ArrayList<NoteModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.lv) as ListView

            sampleKo()

        listView!!.onItemClickListener=AdapterView.OnItemClickListener{parent, view, position, id ->
            val intent=Intent(this@MainActivity,secondActivity::class.java)
                intent.putExtra("Content",notesModelArrayList!![position])
                startActivity(intent)
            }
        btnnewNote!!.setOnClickListener {
            val intent = Intent(this@MainActivity,NewNoteActivity::class.java)
            startActivity(intent)
        }
    }

     fun sampleKo() {
       try {
          Urls.geturl.httpGet().responseString { request, response, result ->
                    onTaskCompleted(result.get())
           }
       }
       catch (e: Exception) {
          }
         finally {}
    }
        fun onTaskCompleted(response: String){
        Log.d("responsejson", response)

        notesModelArrayList = getInfo(response)
        listView!!.adapter  = CustomeAdapter(this, notesModelArrayList!!)

    }

    fun getInfo(response:String): ArrayList<NoteModel> {
        val notesModelArrayList = ArrayList<NoteModel>()

        try {
            val ja = JSONArray(response)
            var jo :JSONObject

              for (i in 0 until ja.length()) {
                  val playersModel = NoteModel()
                  jo =ja.getJSONObject(i)
                  playersModel.setIds(jo.getString("id"))
                  playersModel.setTitles(jo.getString("title"))
                  playersModel.setContents(jo.getString("content"))
                  playersModel.setCreatedAts(jo.getString("createdAt"))
                  notesModelArrayList.add(playersModel)
          }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return notesModelArrayList
    }
}
