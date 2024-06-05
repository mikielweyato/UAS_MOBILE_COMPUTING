package com.example.tugasmc4

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tugasmc4.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnlogin.setOnClickListener {

            val email: String = binding.Email.text.toString().trim()
            val password: String = binding.Password.text.toString().trim()

            if (email.isEmpty()) {
                binding.Email.error = "Input Email"
                binding.Email.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.Email.error = "invalid email"
                binding.Email.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty() || password.length < 6) {
                binding.Password.error = "password be more tthan 6 characters"
                binding.Password.requestFocus()
                return@setOnClickListener
            }


            loginUser(email, password)


        }






        binding.forgot.setOnClickListener {
            val builder = AlertDialog.Builder(this)

            val view = layoutInflater.inflate(R.layout.dialogforgot, null)

            val userEmail = view.findViewById<EditText>(R.id.editBox)

            builder.setView(view)
            val dialog = builder.create()
            view.findViewById<Button>(R.id.btnReset).setOnClickListener {
                compareEmail(userEmail)
                dialog.dismiss()

            }
            view.findViewById<Button>(R.id.btnCancel).setOnClickListener {
                dialog.dismiss()
            }

            if (dialog.window != null) {
                dialog.window!!.setBackgroundDrawable(ColorDrawable(0))
            }
            dialog.show()
        }





        binding.register.setOnClickListener {
            val intentHome = Intent(this, Register::class.java)
            startActivity(intentHome)
        }

    }


    private fun compareEmail(email : EditText){
        if (email.text.toString().isEmpty()){
            return
        }



    if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()){
        return
    }


    firebaseAuth.sendPasswordResetEmail(email.text.toString())
        .addOnCompleteListener { task ->
            if (task.isSuccessful){
                Toast.makeText(this, "Check your Email", Toast.LENGTH_SHORT).show()
            }
        }

    }






    private fun loginUser(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                Intent(this, Home::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            } else {
                Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser != null) {
            Intent(this, Home::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }
    }
}