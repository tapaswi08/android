package com.example.googlemap
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HomeActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        var recyclerview = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerview.layoutManager = LinearLayoutManager(this)
        val data = ArrayList<ItemsViewModel>()

        for (i in 1..5) {
            data.add(ItemsViewModel("User " + i,"Online"))
        }
        val adapter = MyAdapter(data)
        recyclerview.adapter = adapter
        
    }
}

private fun RecyclerView.layoutManager(linearLayoutManager: LinearLayoutManager) {

}

