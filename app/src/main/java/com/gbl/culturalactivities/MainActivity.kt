package com.gbl.culturalactivities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.gbl.culturalactivities.ui.NavScreen
import com.gbl.culturalactivities.ui.theme.CulturalActivitiesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CulturalActivitiesTheme {
                NavScreen()
            }
        }
    }
}