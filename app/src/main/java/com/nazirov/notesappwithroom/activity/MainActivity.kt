package com.nazirov.notesappwithroom.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.nazirov.notesappwithroom.MyApplication
import com.nazirov.notesappwithroom.R
import com.nazirov.notesappwithroom.adapter.NoteAdapter
import com.nazirov.notesappwithroom.database.NoteRepository
import com.nazirov.notesappwithroom.helper.AddDialog
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    companion object {
        var notes = MyApplication.notes
        var adapter = NoteAdapter(notes)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        recyclerView=findViewById(R.id.recyclerView)
        recyclerView.layoutManager= GridLayoutManager(this,1)
        recyclerView.adapter= adapter
        val add = findViewById<FloatingActionButton>(R.id.b_add_note)
        add.setOnClickListener {
            val dialog = AddDialog(this)
            dialog.show()
        }

        adapter.deleteDB={
            var repository= NoteRepository(application)
            val exacutor= Executors.newSingleThreadExecutor()
            val handler= Handler(Looper.getMainLooper())
            exacutor.execute{
                repository.removeNote(it)
                handler.post {
                    // adapter.notifyDataSetChanged()
                }
            }
        }
        adapter.deleteListItem={
            notes.removeAt(it)
            adapter.notifyDataSetChanged()
        }

    }


}