package com.akokko.contact.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.akokko.contact.pojo.Person

class DBHelper(
    context: Context?,
    name: String? = "Contact",
    version: Int = 1,
): SQLiteOpenHelper(context, name, null, version) {

    private val db: SQLiteDatabase by lazy { writableDatabase }
    private var SQL: String = "create table contactList(name varchar(20) primary key, tel varchar(20) not null)"

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(SQL)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun addPerson(name: String, telNumber: String): Boolean {
        val cv = ContentValues()
        cv.put("name", name)
        cv.put("tel", telNumber)
        val x = db.insert("contactList", null, cv)
        return x > 0
    }

    fun deletePerson(name: String): Boolean {
        val flag = db.delete("contactList", "name=?", Array(1){name})
        return flag > 0
    }

    fun getAllPerson(): ArrayList<Person> {
        val personList = ArrayList<Person>()
        val cursor = db.query("contactList", null,null,null,null,null,null)
        while (cursor.moveToNext()) {
            @SuppressLint("Range") val name = cursor.getString(cursor.getColumnIndex("name"))
            @SuppressLint("Range") val tel = cursor.getString(cursor.getColumnIndex("tel"))
            val person = Person(name, tel)
            personList.add(person)
        }
        return personList
    }
}