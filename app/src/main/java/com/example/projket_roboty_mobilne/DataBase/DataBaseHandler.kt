package com.example.projket_roboty_mobilne.DataBase

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME = "RobotDB"
val TABLE_NAME = "MobileRobots"
val COL_ID = "id"
val COL_ROBOTTYPE = "robotType"
val COL_ROBOTNAME = "robotName"
val COL_MAXSPEED = "maxSpeed"
val COl_DESCRIPTION = "description"

class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_ROBOTTYPE + " VARCHAR(256)," +
                COL_ROBOTNAME + " VARCHAR(256)," +
                COl_DESCRIPTION + " VARCHAR(256)," +
                COL_MAXSPEED + " INTEGER)";
        db?.execSQL(createTable)

    }

    //val instance : DataBaseHandler()



    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(mobileRobot: MobileRobot){
        val db = writableDatabase
        var cv = ContentValues()
        cv.put(COL_ROBOTTYPE, mobileRobot.robotType)
        cv.put(COL_ROBOTNAME, mobileRobot.robotName)
        cv.put(COl_DESCRIPTION, mobileRobot.description)
        cv.put(COL_MAXSPEED, mobileRobot.maxSpeed)
        var result = db.insert(TABLE_NAME, null, cv)
        if (result == -1.toLong())
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }

    fun readData() : MutableList<MobileRobot>
    {
        var list : MutableList<MobileRobot> = ArrayList()
        val db = readableDatabase

        val query = "Select * from " + TABLE_NAME
        val result = db.rawQuery(query, null)

        if(result.moveToFirst())
            do {
                var robot = MobileRobot()
                robot.id = result.getString(0).toInt()
                robot.robotType = result.getString(1).toString()
                robot.robotName = result.getString(2).toString()
                robot.description = result.getString(3).toString()
                robot.maxSpeed = result.getString(4).toInt()
                list.add(robot)
            }while (result.moveToNext())

        result.close()
        db.close()
        return list
    }


    @SuppressLint("Range")
    fun readDataById(id: Int) : MobileRobot
    {
        val db = readableDatabase
        val cursor =  db.rawQuery("Select * from " + TABLE_NAME + " Where " + COL_ID + "=" + id  , null)
        var robot = MobileRobot()

        if (cursor != null)
        {
            if (cursor.moveToFirst())
            {
                robot.id = cursor.getInt(cursor.getColumnIndex(COL_ID));
                robot.robotType = cursor.getString(cursor.getColumnIndex(COL_ROBOTTYPE));
                robot.robotName = cursor.getString(cursor.getColumnIndex(COL_ROBOTNAME));
                robot.description = cursor.getString(cursor.getColumnIndex(COl_DESCRIPTION));
                robot.maxSpeed = cursor.getInt(cursor.getColumnIndex(COL_MAXSPEED));
            }
            cursor.close();
        }
        return robot
    }
    @SuppressLint("Range")
    fun readDataByType(type: String) : MobileRobot?
    {
        val db = readableDatabase
        val cursor =  db.rawQuery("select * from " + TABLE_NAME + " where " + COL_ROBOTTYPE + "=" + type  , null)
        var robot = MobileRobot()

        if (cursor != null)
        {
            if (cursor.moveToFirst())
            {
                robot.id = cursor.getInt(cursor.getColumnIndex(COL_ID));
                robot.robotType = cursor.getString(cursor.getColumnIndex(COL_ROBOTTYPE));
                robot.robotName = cursor.getString(cursor.getColumnIndex(COL_ROBOTNAME));
                robot.description = cursor.getString(cursor.getColumnIndex(COl_DESCRIPTION));
                robot.maxSpeed = cursor.getInt(cursor.getColumnIndex(COL_MAXSPEED));
            }
            cursor.close();
        }
        else
            return null
        return robot
    }

    fun deleteData(id: Int){
        val db = writableDatabase
        val result = db.delete(TABLE_NAME, "$COL_ID=?", arrayOf(id.toString()))
        if (result == -1)
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Successfully deleted", Toast.LENGTH_SHORT).show()
        db.close()
    }

}
