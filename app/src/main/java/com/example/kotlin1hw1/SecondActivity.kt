package com.example.kotlin1hw1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin1hw1.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getData()
        btnClick()
    }

    private fun getData() {
        val text: String? = intent?.getStringExtra(MainActivity.TEXT_KEY)
        binding.edtSecond.setText(text)
    }

    private fun btnClick() {
        binding.btnSecond.setOnClickListener {
            if (binding.edtSecond.text?.trim().isNullOrEmpty()) {
                binding.edtSecond.text = null
                Toast.makeText(this, "Type anything than come back", Toast.LENGTH_SHORT).show()
            } else {
                shareAgain()
            }
        }
    }

    private fun shareAgain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(TEXT_FROM_SECOND, binding.edtSecond.text.toString())
        setResult(RESULT_OK, intent)
        finish()
    }

    companion object {
        const val TEXT_FROM_SECOND = "second text key"
    }
}
