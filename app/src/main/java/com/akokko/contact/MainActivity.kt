package com.akokko.contact

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.akokko.contact.db.DBHelper

class MainActivity : AppCompatActivity() {

    // 获取输入框
    val textName by lazy { findViewById<TextView>(R.id.text_name) }
    val textTelNum by lazy { findViewById<TextView>(R.id.text_telNum) }

    // 获取按钮
    val btnInput by lazy { findViewById<Button>(R.id.btn_input) }
    val btnDelete by lazy { findViewById<Button>(R.id.btn_delete) }
    val btnContact by lazy { findViewById<Button>(R.id.btn_contact) }

    // 获取数据库操作类
    private val dbHelper: DBHelper by lazy { DBHelper(this@MainActivity) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        btnOnClick()
    }

    // 绑定按钮事件
    private fun btnOnClick() {
        // 绑定输入按钮点击事件
        btnInput.setOnClickListener {
            // 获取数据
            val name: String = textName?.text?.trim().toString()
            val telNum: String = textTelNum?.text?.trim().toString()

            Log.d(null, name)
            Log.d(null, telNum)

            name.run {
                if (isEmpty()) {
                    Toast.makeText(this@MainActivity, "姓名为空！", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            } ?: return@setOnClickListener

            telNum.run {
                if (isEmpty()) {
                    Toast.makeText(this@MainActivity, "电话号码为空！", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            } ?: return@setOnClickListener

            if (dbHelper.addPerson(name, telNum)) {
                Toast.makeText(this@MainActivity, "添加成功！", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@MainActivity, "添加失败！", Toast.LENGTH_SHORT).show()
            }

        }

        // 绑定删除按钮点击事件
        btnDelete.setOnClickListener {
            // 获取数据
            val name: String = textName.text.trim().toString()

            name.run {
                if (isEmpty()) {
                    Toast.makeText(this@MainActivity, "删除姓名为空！", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            } ?: return@setOnClickListener

            if (dbHelper.deletePerson(name)) {
                Toast.makeText(this@MainActivity, "删除成功！", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@MainActivity, "删除失败！", Toast.LENGTH_SHORT).show()
            }
        }
        // 绑定跳转通讯录按钮点击事件
        btnContact.setOnClickListener {
            val intent = Intent()
            intent.setClass(this@MainActivity, Contact::class.java)
            startActivity(intent)
        }
    }
}