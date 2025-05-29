package android.training

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "home"){
                composable("home") {
                    HomeScreen(onNavigate = { navController.navigate("second")})
                }
                composable("second") {
                    SecondScreen(onBack = { navController.popBackStack()})
                }

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WelcomeScreen()
}

@Composable
fun WelcomeScreen(){

    var clicked by remember { mutableStateOf(false) }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        if(!clicked){
        Text(
            text = "Bienvenue dans mon app Android",
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            clicked = true
                }) {
            Text("Continuer")
        }
        } else {
            WelcomeMessage()
            }
    }
}

@Composable
fun WelcomeMessage() {
    Text(
        text = "Félicitations, vous êtes sur l'écran suivant",
        fontSize = 20.sp
    )
}

@Composable
fun HomeScreen(onNavigate: () -> Unit){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Ecran d'accueil", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onNavigate) {
            Text("Aller à l'écran suivant")
        }
    }
}


@Composable
fun SecondScreen(onBack: () -> Unit) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Bienvenue sur le deuxième écran", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onBack) {
            Text("Retour à l'accueil")
        }
    }
}
