package com.example.addnamesavedata3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.addnamesavedata3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Declare variables
    private lateinit var mainViewModel: MainViewModel
    private val ENTERED_NAMES_KEY = "enteredNamesKey"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set up Data Binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        if (mainViewModel.enteredNames.isEmpty()) {
            binding.displayNames.text = "No names to display"
        } else {
            binding.displayNames.text = mainViewModel.enteredNames.joinToString("\n")
        }

        binding.addName.setOnClickListener {
            val enteredName = binding.editNameText.text.toString().trim()

            if (enteredName.isNotEmpty()) {
                if (binding.displayNames.text.toString().equals("No Names to Display", ignoreCase = true) ||
                    binding.displayNames.text.toString().equals("No Name Entered", ignoreCase = true)
                ) {
                    binding.displayNames.text = ""
                } else {
                    binding.displayNames.append("\n")
                }
                binding.displayNames.append(enteredName)
                binding.editNameText.text.chars()
                mainViewModel.enteredNames.add(enteredName)
            } else {
                binding.displayNames.text = "No Name Entered"
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList(ENTERED_NAMES_KEY, ArrayList(mainViewModel.enteredNames))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val enteredNames = savedInstanceState.getStringArrayList(ENTERED_NAMES_KEY)
        if (!enteredNames.isNullOrEmpty()) {
            binding.displayNames.text = enteredNames.joinToString("\n")
            mainViewModel.enteredNames.addAll(enteredNames)
        }
    }
}
