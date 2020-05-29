package com.viveris.pocroom.ui

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.viveris.pocroom.R
import com.viveris.pocroom.RoomApplication
import com.viveris.pocroom.db.RoomDatabaseManager
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IDatabaseCallback {

    companion object {
        private const val TAG_PERF = "DATABASE_PERF"
    }

    private lateinit var roomDatabaseManager: RoomDatabaseManager
    private val compositeDisposable = CompositeDisposable()

    private var progressBarManager: ProgressBarManager? = null

    private var beforeInsert: Long = 0
    private var beforeGet: Long = 0
    private var beforeQuery: Long = 0
    private var beforeDelete: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        roomDatabaseManager = RoomDatabaseManager((application as RoomApplication).appRoomDatabase, compositeDisposable, this)

        setContentView(R.layout.activity_main)
        progressBarManager = ProgressBarManager()

        buttonInsert.setOnClickListener { insertUsersInDb() }

        buttonQueryForAll.setOnClickListener { getAllDbUsers() }

        buttonQueryWhere.setOnClickListener { queryUsersBySuffix() }

        buttonSuppress.setOnClickListener { deleteAllDbUsers() }
    }

    private fun insertUsersInDb() {
        val tableSize = when(TextUtils.isEmpty(nbEntrees.text)) {
            true -> 100
            else -> nbEntrees.text.toString().toInt()
        }
        progressBarManager?.onLoaderStateChange(true, progressBarHolder)
        beforeInsert = System.currentTimeMillis()
        roomDatabaseManager.insertUsers(tableSize)
    }

    private fun getAllDbUsers() {
        progressBarManager?.onLoaderStateChange(true, progressBarHolder)
        beforeGet = System.currentTimeMillis()
        roomDatabaseManager.getAllUsers()
    }

    private fun queryUsersBySuffix() {
        condition.text?.let {
            progressBarManager?.onLoaderStateChange(true, progressBarHolder)
            beforeQuery = System.currentTimeMillis()
            roomDatabaseManager.queryUsers(it.toString())
        }
    }

    private fun deleteAllDbUsers() {
        progressBarManager?.onLoaderStateChange(true, progressBarHolder)
        beforeDelete = System.currentTimeMillis()
        roomDatabaseManager.deleteAllUsers()
    }

    override fun insertSuccess() {
        val duration = System.currentTimeMillis() - beforeInsert
        Log.i(TAG_PERF, "insert duration $duration")
        progressBarManager?.onLoaderStateChange(false, progressBarHolder)
        Toast.makeText(application, "Insert users Success", Toast.LENGTH_SHORT).show()
    }

    override fun insertFailure() {
        progressBarManager?.onLoaderStateChange(false, progressBarHolder)
        Toast.makeText(application, "Insert users Error", Toast.LENGTH_SHORT).show()
    }

    override fun queryAllSuccess() {
        progressBarManager?.onLoaderStateChange(false, progressBarHolder)
        Toast.makeText(application, "query all Success", Toast.LENGTH_SHORT).show()
    }

    override fun queryAllFailure() {
        progressBarManager?.onLoaderStateChange(false, progressBarHolder)
        Toast.makeText(application, "query all Error", Toast.LENGTH_SHORT).show()
    }

    override fun querySucess() {
        progressBarManager?.onLoaderStateChange(false, progressBarHolder)
        Toast.makeText(application, "query users sucess", Toast.LENGTH_SHORT).show()
    }

    override fun queryFailure() {
        progressBarManager?.onLoaderStateChange(false, progressBarHolder)
        Toast.makeText(application, "query users Error", Toast.LENGTH_SHORT).show()
    }

    override fun deleteSuccess() {
        progressBarManager?.onLoaderStateChange(false, progressBarHolder)
        Toast.makeText(application, "delete users success", Toast.LENGTH_SHORT).show()
    }

    override fun deleteFailure() {
        progressBarManager?.onLoaderStateChange(false, progressBarHolder)
        Toast.makeText(application, "delete users Error", Toast.LENGTH_SHORT).show()
    }
}