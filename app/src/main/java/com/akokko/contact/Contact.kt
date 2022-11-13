package com.akokko.contact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.akokko.contact.db.DBHelper
import com.akokko.contact.pojo.Person

class Contact : AppCompatActivity() {

    val listView: ListView by lazy { findViewById(R.id.list_view) }
    val dbHelper: DBHelper by lazy { DBHelper(this@Contact) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)
    }

    override fun onStart() {
        super.onStart()
        val personList: List<Person> = dbHelper.getAllPerson()
        val arrAdapter = ArrayAdapter(this@Contact, android.R.layout.simple_list_item_1, personList)
        listView.adapter = arrAdapter
    }
}