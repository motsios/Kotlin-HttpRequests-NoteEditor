package com.example.notekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpPut
import kotlinx.android.synthetic.main.activity_second.*


class secondActivity : AppCompatActivity() {
    private var NoteModel: NoteModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val title = findViewById<EditText>(R.id.title)
        val content = findViewById<EditText>(R.id.content)
        val create = findViewById<TextView>(R.id.create)
        val id = findViewById<TextView>(R.id.id)


        var intent = intent
        NoteModel = intent.getSerializableExtra("Content") as NoteModel
        id.text=(NoteModel!!.getIds())
        title.setText(NoteModel!!.getTitles())
        content.setText(NoteModel!!.getContents())
        create.text=(NoteModel!!.getCreatedAts())

        btnBack.setOnClickListener {
            onBackPressed()
        }



        var urlq=Urls.geturl+"/${id.text}"
        btnDelete.setOnClickListener{
            urlq.httpDelete().response { request, response, result ->
            }
            Toast.makeText(this, "Deleted Successfully!", Toast.LENGTH_SHORT).show()
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        btnNoteUpdate.setOnClickListener {
            val newtitle=title.text.toString()
            val newcontent=content.text.toString()

            urlq.httpPut().header("Content-Type" to "application/json").jsonBody("{ \"title\" : \"${newtitle}\" , \"content\" : \"${newcontent}\"}").response {
            request, response, result ->
            }
            Toast.makeText(this, "Updated Successfully!", Toast.LENGTH_SHORT).show()
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}
