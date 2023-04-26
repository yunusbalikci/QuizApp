package com.yunusbalikci.flagquizapp

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.yunusbalikci.flagquizapp.databinding.ActivityMainBinding
import com.yunusbalikci.flagquizapp.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding

    private lateinit var sorular:ArrayList<Flags>
    private lateinit var yanlisSecenekler:ArrayList<Flags>
    private lateinit var dogruSoru:Flags
    private lateinit var vt:DatabaseHelper
    private lateinit var tumSecenekler:HashSet<Flags>

    private var soruSayac = 0
    private var dogruSayac = 0
    private var yanlisSayac = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        vt = DatabaseHelper(this)
        sorular = FlagsDao().random5Flags(vt)
        soruYukle()

        binding.buttonA.setOnClickListener {
            dogruKontrol(binding.buttonA)
            soruSayacaKontrol()
        }
        binding.buttonB.setOnClickListener {
            dogruKontrol(binding.buttonB)
            soruSayacaKontrol()
        }
        binding.buttonC.setOnClickListener {
            dogruKontrol(binding.buttonC)
            soruSayacaKontrol()
        }
        binding.buttonD.setOnClickListener {
            dogruKontrol(binding.buttonD)
            soruSayacaKontrol()
        }

    }

    fun soruYukle(){
        binding.textViewQuestionCount.text = "${soruSayac+1}.Soru"
        dogruSoru = sorular.get(soruSayac)
        binding.imageViewFlag.setImageResource(resources.getIdentifier(dogruSoru.flag_pics,"drawable",packageName))

        yanlisSecenekler = FlagsDao().get3False(vt,dogruSoru.flag_id)
        tumSecenekler= HashSet()
        tumSecenekler.add(dogruSoru)
        tumSecenekler.add(yanlisSecenekler.get(0))
        tumSecenekler.add(yanlisSecenekler.get(1))
        tumSecenekler.add(yanlisSecenekler.get(2))

        binding.buttonA.text = tumSecenekler.elementAt(0).flag_name
        binding.buttonB.text = tumSecenekler.elementAt(1).flag_name
        binding.buttonC.text = tumSecenekler.elementAt(2).flag_name
        binding.buttonD.text = tumSecenekler.elementAt(3).flag_name

    }

    fun soruSayacaKontrol(){
        soruSayac++

        if (soruSayac !=5){
            soruYukle()
        }else{
            val intent = Intent(this@QuizActivity,ResultActivity::class.java)
            intent.putExtra("dogruSayac",dogruSayac)
            startActivity(intent)
            finish()
        }
    }

    fun dogruKontrol(button:Button){
        val buttonYazi = button.text.toString()
        val dogruCevap = dogruSoru.flag_name

        if (buttonYazi == dogruCevap){
            dogruSayac++
        }else{
            yanlisSayac++
        }
        binding.textViewTrue.text = "Doğru : $dogruSayac"
        binding.textViewFalse.text = "Yanlış : $yanlisSayac"
    }
}