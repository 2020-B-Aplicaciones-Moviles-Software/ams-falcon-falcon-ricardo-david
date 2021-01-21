package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    val CODIGO_INGRESO =102
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val botonIngresar = findViewById<Button>(R.id.btn_ingresar)
        botonIngresar
            .setOnClickListener {
                pedidoIngresar()
            }

        val botonSalir = findViewById<Button>(R.id.btn_salir)
        botonSalir
            .setOnClickListener {
                pedidoSalir()

            }
        val texto = findViewById<TextView>(R.id.textView)
        val instanciaAuth = FirebaseAuth.getInstance()
        if(instanciaAuth.currentUser != null){
            texto.text="Bienvenido ${instanciaAuth.currentUser?.email}"
        }else{
            texto.text="Dale clic al boton ingresar"
        }

    }

    fun pedidoIngresar(){
        // Choose authentication providers
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setLogo(R.drawable.liga)
                .setTosAndPrivacyPolicyUrls(
                    "https://example.com/terms.html",
                    "https://example.com/privacy.html"
                )
                .build(),
            CODIGO_INGRESO)
    }

    fun pedidoSalir(){
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                Log.i("firebase-firestore","Salio")
            }
            .addOnFailureListener{
                Log.i("firebase-firestore","Hubo problemas en Salir")
            }


    }
}