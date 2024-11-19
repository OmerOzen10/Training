import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.training.View.ChatScreen
import com.example.training.ViewModel.MainViewModel
import com.example.training.ScreenNames
import com.example.training.View.Screen

@Composable
fun Nav(viewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ScreenNames.HOME_SCREEN) {
        // İlk sayfa
        composable(ScreenNames.HOME_SCREEN) {
            Screen(viewModel = viewModel, navController = navController)
        }
        // İkinci sayfa
        composable(ScreenNames.CHAT_SCREEN) {
            ChatScreen(viewModel,navController)
        }
    }
}
