package com.example.training.View

import MessageCard
import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.training.ViewModel.MainViewModel
import com.example.training.R



import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


private const val TAG = "chatscreen"


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChatScreen(viewModel: MainViewModel, navController: NavController) {
    val message by viewModel.message

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "AI HELP CHAT")},
                navigationIcon = {
                    Icon(
                        modifier = Modifier.clickable { navController.popBackStack()},
                        painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                        contentDescription = "arrow-back")
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .imePadding()
                .systemBarsPadding()
                .background(Color(134, 138, 135))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {

                    val listState = rememberLazyListState()

                    LaunchedEffect(viewModel.messageList.value.size) {
                        listState.animateScrollToItem(viewModel.messageList.value.size - 1)
                    }
                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        state = listState,
                        verticalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.Bottom)
                    ) {
                        items(viewModel.messageList.value.size) { msg ->
                            MessageCard(message = viewModel.messageList.value[msg])
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                    ) {
                        TextField(
                            value = message,
                            onValueChange = { viewModel.message.value = it },
                            label = { Text(text = "Your message") },
                            modifier = Modifier.weight(3.2f)
                        )

                        Button(onClick = { viewModel.sendRequest(message) },modifier = Modifier
                            .weight(0.8f)
                            .padding(start = 5.dp)) {
                            Icon(
                                painter = painterResource(id = R.drawable.send),
                                contentDescription = "Button Icon",
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }

            }
        }

    }


}


//@Preview
//@Composable
//fun MyPreview(){
//    ChatScreen(viewModel = MainViewModel(application = Application()), navController = NavHostControlle)
//}
//
//
