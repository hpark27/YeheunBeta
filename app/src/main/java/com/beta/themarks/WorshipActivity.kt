package com.beta.themarks

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.beta.themarks.databinding.ActivityWorshipBinding

class WorshipActivity: AppCompatActivity() {
    private lateinit var binding: ActivityWorshipBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorshipBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // back
        binding.back.setOnClickListener{
            finish()
        }

        // church map
        binding.faclity.setOnClickListener {
            val facility = Intent(this, FacilityActivity::class.java)

            startActivity(facility)
        }
    }
}
