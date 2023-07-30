package com.example.gezegendekackilosun

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationSet
import android.view.animation.DecelerateInterpolator
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

private lateinit var checkBoxMars:CheckBox
private lateinit var checkBoxVenüs:CheckBox
private lateinit var checkBoxJüpiter:CheckBox
private lateinit var editTextAgirlik:EditText
private lateinit var btnHesapla:Button
private lateinit var textViewAgirlik:TextView
private lateinit var mars_image:ImageView
private lateinit var venus_image:ImageView
private lateinit var jupiter_image:ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkBoxMars = findViewById(R.id.checkBoxMars)
        checkBoxVenüs = findViewById(R.id.checkBoxVenüs)
        checkBoxJüpiter = findViewById(R.id.checkBoxJüpiter)
        editTextAgirlik = findViewById(R.id.editTextAgirlik)
        btnHesapla = findViewById(R.id.btnHesapla)
        textViewAgirlik = findViewById(R.id.textViewAgirlik)
        mars_image = findViewById(R.id.imageMars)
        venus_image = findViewById(R.id.imageVenüs)
        jupiter_image = findViewById(R.id.imageJüpiter)

        checkBoxMars.setOnClickListener { kontrolCheck(checkBoxMars) }
        checkBoxVenüs.setOnClickListener { kontrolCheck(checkBoxVenüs) }
        checkBoxJüpiter.setOnClickListener { kontrolCheck(checkBoxJüpiter) }

        checkBoxMars.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                seçiliGezegeniAyarla("Mars")
                checkBoxVenüs.isChecked = false
                checkBoxJüpiter.isChecked = false
                gezegenAgirligiHesapla()
            }
        }

        checkBoxVenüs.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                seçiliGezegeniAyarla("Venüs")
                checkBoxMars.isChecked = false
                checkBoxJüpiter.isChecked = false
                gezegenAgirligiHesapla()
            }
        }

        checkBoxJüpiter.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                seçiliGezegeniAyarla("Jüpiter")
                checkBoxMars.isChecked = false
                checkBoxVenüs.isChecked = false
                gezegenAgirligiHesapla()
            }
        }

        btnHesapla.setOnClickListener {
            gezegenAgirligiHesapla()
        }

    }

    fun kontrolCheck(selectedCheckBox: CheckBox) {
        checkBoxMars.isChecked = selectedCheckBox == checkBoxMars
        checkBoxVenüs.isChecked = selectedCheckBox == checkBoxVenüs
        checkBoxJüpiter.isChecked = selectedCheckBox == checkBoxJüpiter
    }



    fun gezegenAgirligiHesapla() {
        var dünyaAgirligi = editTextAgirlik.text.toString().toDoubleOrNull()

        val marsYerCekimi = 0.38
        val venüsYerCekimi = 0.91
        val jüpiterYerCekimi = 2.34

        var gezegenAgirligi = 0.0

        if (dünyaAgirligi != null) {


            if (checkBoxMars.isChecked) {
                gezegenAgirligi = dünyaAgirligi * marsYerCekimi
                textViewAgirlik.text = "${formatlıGezegenAgirligi(gezegenAgirligi)}"
            } else if (checkBoxVenüs.isChecked) {
                gezegenAgirligi = dünyaAgirligi * venüsYerCekimi
                textViewAgirlik.text = "${formatlıGezegenAgirligi(gezegenAgirligi)}"
            } else if (checkBoxJüpiter.isChecked) {
                gezegenAgirligi = dünyaAgirligi * jüpiterYerCekimi
                textViewAgirlik.text = "${formatlıGezegenAgirligi(gezegenAgirligi)}"
            } else {
                textViewAgirlik.text = "Lütfen bir gezegen seçin."
            }
        } else {
            textViewAgirlik.text = "Lütfen geçerli bir ağırlık girin."
        }
    }
    fun formatlıGezegenAgirligi(agirlık: Double): String {
        return String.format("%.3f", agirlık)
    }

    fun seçiliGezegeniAyarla(gezegen: String) {
        mars_image.visibility = if (gezegen == "Mars") View.VISIBLE else View.INVISIBLE
        venus_image.visibility = if (gezegen == "Venüs") View.VISIBLE else View.INVISIBLE
        jupiter_image.visibility = if (gezegen == "Jüpiter") View.VISIBLE else View.INVISIBLE
    }

    fun fadeInVeResmiGöster(resim: ImageView) {
        val fadeInAnimasyonu = AlphaAnimation(0f, 1f)
        fadeInAnimasyonu.interpolator = DecelerateInterpolator()
        fadeInAnimasyonu.duration = 1000
        val animasyonSeti = AnimationSet(false)
        animasyonSeti.addAnimation(fadeInAnimasyonu)
        resim.animation = animasyonSeti
        resim.visibility = View.VISIBLE
    }

    fun resmiGizle(resim: ImageView) {
        resim.visibility = View.INVISIBLE
    }


}