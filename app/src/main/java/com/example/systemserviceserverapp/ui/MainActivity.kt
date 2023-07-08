package com.example.systemserviceserverapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.systemserviceserverapp.services.RandomNumberService
import com.example.systemserviceserverapp.ui.theme.SystemServiceServerAppTheme

class MainActivity : ComponentActivity() {

    lateinit var serviceIntent : Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        serviceIntent = Intent(this, RandomNumberService::class.java)

        setContent {
            SystemServiceServerAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Column() {

                        Text(modifier = Modifier.padding(10.dp),
                            text = "Server Side!",
                            fontSize = 16.sp
                        )

                        Button(modifier = Modifier.padding(10.dp),
                            onClick = {
                            startService(serviceIntent)
                        }) {
                            Text(text = "Start Service")
                        }

                        Button(modifier = Modifier.padding(10.dp),
                            onClick = {
                                stopService(serviceIntent)
                            }) {
                            Text(text = "Stop Service")
                        }

                    }


                }
            }
        }
    }

}

