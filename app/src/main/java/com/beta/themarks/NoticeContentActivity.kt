package com.beta.themarks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.beta.themarks.databinding.ActivityNoticecontentBinding

class NoticeContentActivity: AppCompatActivity() {
    private lateinit var binding: ActivityNoticecontentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoticecontentBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // back
        binding.back.setOnClickListener {
            finish()
        }

        // get data
        binding.noticeTitle.text = intent.getStringExtra("title")
        binding.noticeContent.text = intent.getStringExtra("content")
        binding.noticeDate.text = intent.getStringExtra("date")
    }
}
