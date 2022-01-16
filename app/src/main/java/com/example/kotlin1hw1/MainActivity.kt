package com.example.kotlin1hw1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin1hw1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var result: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btnClick()
        registerForActivity()
    }

    private fun registerForActivity() {
        result =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK && result.data != null) {
                    binding.edtFirst.setText(result?.data?.getStringExtra(SecondActivity.TEXT_FROM_SECOND))
                }
            }
    }

    private fun btnClick() {
        binding.btnFirst.setOnClickListener {
            if (binding.edtFirst.text?.trim().isNullOrEmpty()) {
                binding.edtFirst.text = null
                Toast.makeText(this, "Type anything", Toast.LENGTH_SHORT).show()
            } else {
                share()
            }
        }
    }

    private fun share() {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(TEXT_KEY, binding.edtFirst.text.toString())
        result.launch(intent)
    }

    companion object {
        const val TEXT_KEY = "TEXT_KEY"
    }
}