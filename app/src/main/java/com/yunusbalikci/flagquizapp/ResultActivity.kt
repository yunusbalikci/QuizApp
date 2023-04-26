package com.yunusbalikci.flagquizapp

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yunusbalikci.flagquizapp.databinding.ActivityQuizBinding
import com.yunusbalikci.flagquizapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val dogruSayac =intent.getIntExtra("dogruSayac",0)

        binding.textViewResult.text = "$dogruSayac DOĞRU ${5-dogruSayac} YANLIŞ"
        binding.textViewSuccess.text = "% ${(dogruSayac*100)/5} Başarı"
        binding.buttonTryAgain.setOnClickListener {
            startActivity(Intent(this@ResultActivity,MainActivity::class.java))
            finish()
        }


    }

}