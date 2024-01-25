package com.example.foodbrowser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast

import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var searchEditText: EditText
    private lateinit var foodListView: ListView
    private lateinit var foodAdapter: ArrayAdapter<String>

    private val apiBaseUrl = "https://uih0b7slze.execute-api.us-east-1.amazonaws.com/dev/search"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchEditText = findViewById(R.id.searchEditText)
        foodListView = findViewById(R.id.foodListView)
        foodAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1)
        foodListView.adapter = foodAdapter

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // Call API with the search term
                val searchTerm = s.toString()
                if (searchTerm.length >= 3) {
                    fetchDataFromApi(searchTerm)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        foodListView.setOnItemClickListener { _, _, position, _ ->
            val selectedFoodName = foodAdapter.getItem(position)
            Toast.makeText(this, "Selected food: $selectedFoodName", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fetchDataFromApi(searchTerm: String) {
        val url = "$apiBaseUrl?kv=$searchTerm"

        val request = Request.Builder()
            .url(url)
            .build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()

                // Parse JSON response and update the UI
                runOnUiThread {
                    updateUIWithFoodItems(responseData)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                // Handle failure
                e.printStackTrace()
            }
        })
    }

    private fun updateUIWithFoodItems(jsonData: String?) {
        // Parse JSON and update the list view
        val foodList = Gson().fromJson(jsonData, Array<Food>::class.java)

        // Update the adapter with food names
        foodAdapter.clear()
        foodList?.forEach { food ->
            foodAdapter.add(food.name)
        }
    }
}
