package com.yunusbalikci.flagquizapp

import android.annotation.SuppressLint

class FlagsDao {

    @SuppressLint("Range")
    fun random5Flags(vt:DatabaseHelper) : ArrayList<Flags> {
        val flagsList = ArrayList<Flags>()
        val db = vt.writableDatabase
        val c = db.rawQuery("SELECT * FROM bayraklar ORDER BY RANDOM() LIMIT 5",null)

        while (c.moveToNext()){
            val bayrak =Flags(c.getInt(c.getColumnIndex("bayrak_id"))
                ,c.getString(c.getColumnIndex("bayrak_ad"))
                ,c.getString(c.getColumnIndex("bayrak_resim")))
            flagsList.add(bayrak)
        }

        return flagsList
    }

    @SuppressLint("Range")
    fun get3False(vt:DatabaseHelper,bayrak_id:Int) : ArrayList<Flags> {
        val flagsList = ArrayList<Flags>()
        val db = vt.writableDatabase
        val c = db.rawQuery("SELECT * FROM bayraklar WHERE bayrak_id != $bayrak_id ORDER BY RANDOM() LIMIT 3",null)

        while (c.moveToNext()){
            val bayrak =Flags(c.getInt(c.getColumnIndex("bayrak_id"))
                ,c.getString(c.getColumnIndex("bayrak_ad"))
                ,c.getString(c.getColumnIndex("bayrak_resim")))
            flagsList.add(bayrak)
        }

        return flagsList
    }

}