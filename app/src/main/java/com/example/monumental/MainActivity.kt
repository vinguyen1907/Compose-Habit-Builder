package com.example.monumental

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.monumental.presentation.screens.nav_graph.NavGraph
import com.example.monumental.presentation.screens.nav_graph.Route
import com.example.monumental.presentation.screens.sign_in.SignInEvent
import com.example.monumental.presentation.screens.sign_in.SignInViewModel
import com.example.monumental.ui.theme.MonumentalTheme
import com.example.monumental.utils.Constants.WEB_CLIENT_ID
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private val viewModel by viewModels<MainViewModel>()
    private val signInViewModel by viewModels<SignInViewModel>()
//    private lateinit var oneTapClient: SignInClient
//    private lateinit var signInRequest: BeginSignInRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
//        oneTapClient = Identity.getSignInClient(this)
//        signInRequest = BeginSignInRequest.builder()
//            .setPasswordRequestOptions(BeginSignInRequest.PasswordRequestOptions.builder()
//                .setSupported(true)
//                .build())
//            .setGoogleIdTokenRequestOptions(
//                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
//                    .setSupported(true)
//                    // Your server's client ID, not your Android client ID.
//                    .setServerClientId(WEB_CLIENT_ID)
//                    // Only show accounts previously used to sign in.
//                    .setFilterByAuthorizedAccounts(true)
//                    .build())
//            // Automatically sign in when exactly one credential is retrieved.
//            .setAutoSelectEnabled(true)
//            .build()

        splashScreen.setKeepOnScreenCondition {
            viewModel.showSplash
        }
        setContent {
            MonumentalTheme {
                NavGraph(startDestination = viewModel.startDestination)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val auth = Firebase.auth
        if (auth.currentUser == null) {
            signInViewModel.onEvent(SignInEvent.CreateAnonymousAccount)
        }
    }
}

@Preview
@Composable
fun MainActivityPRev() {
    MonumentalTheme {
        NavGraph(startDestination = Route.MainNavigation.route)
    }
}
