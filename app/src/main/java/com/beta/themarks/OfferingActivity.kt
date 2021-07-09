package com.beta.themarks

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.beta.themarks.databinding.ActivityOfferingBinding

class OfferingActivity:AppCompatActivity() {
    private lateinit var binding: ActivityOfferingBinding

    // firebase firestore
    private lateinit var fireStore: FirebaseFirestore

    // array lists for content informations
    private val contents: ArrayList<Offering> = ArrayList()

    // check if layout is expanded
    private var expand: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOfferingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // initialize firestore
        fireStore = FirebaseFirestore.getInstance()
        // collapse layout
        expand = true

        // read data
        fireStore.collection("Offering").get()
            .addOnSuccessListener { result ->
                contents.clear()
                for (document in result) {
                    val item = Offering(document["id"] as String?, document["url"] as String?)

                    contents.add(0, item)

                    // extract id and url
                    val id = contents[0].id.toString()
                    val url = contents[0].url.toString()

                    Toast.makeText(applicationContext, url, Toast.LENGTH_SHORT).show()
                    if (id.isNotEmpty()) {
                        // show id
                        binding.offeringId.text = id
                    }

                    if (url.isNotEmpty()) {
                        // show QR code
                        Glide.with(this).load(url).into(binding.offeringImage)
                    }

                    if (url.isEmpty()) {
                        Toast.makeText(
                            applicationContext,
                            getString(R.string.offering_noqr),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            .addOnFailureListener {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.submain_bulletin_fail),
                    Toast.LENGTH_SHORT
                ).show()
            }

        // back
        binding.back.setOnClickListener {
            finish()
        }

        // expand layout
        binding.expandLayout.setOnClickListener {
            if (expand) {
                binding.offeringArrow.setImageResource(R.drawable.up_arrow)
                binding.offeringExpand.text = getString(R.string.offering_collapse)

                // show layout
                binding.offeringQrLayout.visibility = View.VISIBLE
                // update boolean
                expand = false
            } else {
                // change icon and text
                binding.offeringArrow.setImageResource(R.drawable.down_arrow)
                binding.offeringExpand.text = getString(R.string.offering_expand)
                // hide layout
                binding.offeringQrLayout.visibility = View.INVISIBLE
                // update boolean
                expand = true
            }
        }

        // open google play
        binding.offeringLink.setOnClickListener {
            val googleplay = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(getString(R.string.venmo_url))
                setPackage(getString(R.string.android_vending_package))
            }

            startActivity(googleplay)
        }
    }
}