package com.wn.ft

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.wn.ft.ui.theme.FantasticTheme

class MainActivity : ComponentActivity() {
    private lateinit var mAuth: FirebaseAuth

    override fun onStart() {
        super.onStart()

        // 检查用户是否已登录,没登陆跳登录页面
        if (mAuth.currentUser == null){
            Toast.makeText(this, "当前用户未登录", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "用户名：${mAuth.currentUser?.email}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mAuth = Firebase.auth

        setContent {
            FantasticTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(this){
                        requestLogin()
                    }
                }
            }
        }
    }

    private fun requestLogin(){
        mAuth.signInWithEmailAndPassword("412960979@qq.com", "aa091916")
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "login success", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "login failed", Toast.LENGTH_SHORT).show()
                }
            }
    }
}

@Composable
fun Greeting(context: Context?, requestLogin: () -> Unit) {
    Text(text = "click to log in to the flowing account：412960979@qq.com", Modifier.clickable {
        requestLogin()
    })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FantasticTheme {
        Greeting(null){}
    }
}