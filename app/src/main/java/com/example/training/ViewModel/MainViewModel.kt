package com.example.training.ViewModel

import ScoreRepository
import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.training.Fav
import com.example.training.FavDatabase
import com.example.training.Model.Message
import com.example.training.Retrofit.RequestBodyModel
import com.example.training.Retrofit.ResponseModel
import com.example.training.Retrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class MainViewModel(application: Application,): AndroidViewModel(application)  {
    var message = mutableStateOf("")

    val scoreRepository = ScoreRepository(application)
    var number = mutableIntStateOf(0)
    var highScore = mutableIntStateOf(0)
    var history = mutableStateListOf<Fav>()
    var responseMessage = mutableStateOf("")
    var messageList = mutableStateOf<List<Message>>(listOf(Message("Merhaba", false), Message("Selam", true)))

    init {
        // Başlangıçta en yüksek skoru almak için
        viewModelScope.launch {
            scoreRepository.highScoreFlow.collect { score ->
                highScore.intValue = score  // En yüksek skoru güncelle
            }
        }
    }

    fun increment() {
        number.intValue += 1
        if (number.intValue > highScore.intValue) {
            // Yeni rekor varsa kaydet
            viewModelScope.launch {
                scoreRepository.saveHighScore(number.intValue)
                highScore.intValue = number.intValue
            }
        }
    }

    fun resetHighScore() {
        viewModelScope.launch {
            scoreRepository.resetHighScore()  // Skoru sıfırla
            highScore.value = 0  // UI'de de sıfırla
        }
    }

    fun getFav(){
        viewModelScope.launch {
            val data = withContext(Dispatchers.IO){
                FavDatabase.getDatabase(context = getApplication()).contentDao().getFavNumber()


            }
            history.clear()
            history.addAll(data)
            Log.d("TAG", "getFav: ${data}")
        }
    }

    fun saveToDatabase(fav: Fav){
        viewModelScope.launch{
            withContext(Dispatchers.IO){
                FavDatabase.getDatabase(getApplication()).contentDao().favNumber(fav)
            }
        }
    }

    fun sendRequest(message: String) {
        messageList.value += Message(message, true)
        viewModelScope.launch {
            try {
                val response = RetrofitClient.instance.postRequest(RequestBodyModel("sophaAI", message))
                messageList.value += Message(response.msg, false)
                Log.d("TAG", "sendRequest: ${response} ")
//                val text = "CODE: ${response.code} \n\nMESSAGE: ${response.msg}"
//                responseMessage.value = text
            } catch (e: Exception) {

                Log.d("TAG", "sendRequest: exception ${e.message}")

            }
        }

    }
}