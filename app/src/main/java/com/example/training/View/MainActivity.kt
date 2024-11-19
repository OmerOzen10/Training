package com.example.training.View

import Nav
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.training.Fav
import com.example.training.ViewModel.MainViewModel
import com.example.training.ui.theme.TrainingTheme

class MainActivity() : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        var viewModel = MainViewModel(application)
        super.onCreate(savedInstanceState)


        setContent {

            TrainingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Nav(viewModel = viewModel)
        
                }
            }
        }
    }
}

@Composable
fun Screen(navController: NavController, viewModel: MainViewModel) {
    // highScoreFlow'u collectAsState ile dinliyoruz
    val highScore by viewModel.scoreRepository.highScoreFlow.collectAsState(0)


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Zikirmatik",
            fontSize = 50.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )

        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.Black)
                .size(100.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = viewModel.number.value.toString(),
                fontSize = 50.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }

        Text(
            text = "High Score: $highScore", // High Score burada gösterilecek
            fontSize = 30.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        Button(
            onClick = { viewModel.increment() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
        ) {
            Text(text = "Allah")
        }

        Button(
            onClick = { viewModel.resetHighScore()
                      viewModel.number.intValue },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
        ) {
            Text(text = "Reset")
        }
        Button(
            onClick = { viewModel.saveToDatabase(Fav(viewModel.number.intValue))},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
        ) {
            Text(text = "Fav")
        }
        Button(
            onClick = { viewModel.getFav()},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
        ) {
            Text(text = "Show Fav")
        }
        
        Button(onClick = {navController.navigate("chatscreen")}) {
            Text(text = "Chat Screen")
            
        }
        if (!viewModel.history.isEmpty()){
            viewModel.history.forEach{
                Text(text = it.favNumber.toString())
            }
        }

    }
}
