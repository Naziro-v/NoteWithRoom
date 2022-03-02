package com.nazirov.notesappwithroom.database

import android.app.Application
import com.nazirov.notesappwithroom.manager.RoomManager
import com.nazirov.notesappwithroom.model.Note

class NoteRepository {

    lateinit var noteDao: NoteDao

    constructor(application: Application){
        val db= RoomManager.getDatabase(application)
        noteDao=db.noteDao()!!
    }

    fun getNotes():List<Note>{
        return noteDao.getNotes()
    }
    fun saveNote(note: Note){
        noteDao.saveNote(note)
    }

    fun removeNote(postion:Int){
        noteDao.removeNote(postion)
    }

}