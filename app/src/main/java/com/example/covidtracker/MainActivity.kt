package com.example.covidtracker

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var topAnim: Animation
    lateinit var bottomAnim: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val connectionClass = ConnectionClass(this)

        val dialog = Dialog(this)

        if (connectionClass.checkConnection()) {
            dialog.setContentView(R.layout.alert_dialog_layout)
            dialog.setCanceledOnTouchOutside(false)
            dialog.window?.setLayout(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.attributes?.windowAnimations = android.R.style.Animation_Dialog
            dialog.show()
            val okBtn = dialog.findViewById<Button>(R.id.ok_btn)
            okBtn.setOnClickListener {
                recreate()
            }
        } else {
            topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
            bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

            logo.animation = topAnim
            app_name_tv.animation = bottomAnim

            Handler().postDelayed({
                val i = Intent(this, HomeActivity::class.java)
                startActivity(i)
                finish()
            }, 4500)
        }


    }
}