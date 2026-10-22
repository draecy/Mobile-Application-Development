package com.example.pba

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) :
    SQLiteOpenHelper(context, "CarDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE car(id INTEGER PRIMARY KEY AUTOINCREMENT, model TEXT, price REAL)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS car")
        onCreate(db)
    }

    fun insertData(model: String, price: Double) {
        val db = writableDatabase
        val values = ContentValues()
        values.put("model", model)
        values.put("price", price)
        db.insert("car", null, values)
    }

    fun getLastData(): String {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM car ORDER BY id DESC LIMIT 1", null)

        return if (cursor.moveToFirst()) {
            val model = cursor.getString(1)
            val price = cursor.getDouble(2)
            "Model: $model\nFinal Price: RM $price"
        } else {
            "No data"
        }
    }
}