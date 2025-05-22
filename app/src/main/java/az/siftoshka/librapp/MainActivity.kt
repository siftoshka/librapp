package az.siftoshka.librapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import az.siftoshka.librapp.navigation.NavGraph
import az.siftoshka.presentation.navigation.onboarding.OnboardingSubGraph
import az.siftoshka.presentation.uikit.LibrappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()
        enableEdgeToEdge()

        setContent {
            LibrappTheme {
                val navController = rememberNavController()
                Scaffold(
                    contentWindowInsets = WindowInsets(0.dp),
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavGraph(
                            navController = navController,
                            startDestination = OnboardingSubGraph.Main
                        )
                    }
                }
            }
        }
    }
}