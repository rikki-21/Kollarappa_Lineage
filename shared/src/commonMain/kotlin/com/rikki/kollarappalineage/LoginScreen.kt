package com.rikki.kollarappalineage

@Composable
fun LoginScreen(auth: AuthService, nav: NavHostController) {
    val user by auth.currentUser.collectAsState()
    if (user != null) LaunchedEffect(Unit) { nav.navigate(Screen.Tree) }

    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        Button(onClick = { coroutineScope.launch { auth.signInWithGoogle() } }) {
            Icon(GoogleLogo(),""); Spacer(8.dp); Text("Sign in with Google")
        }
        TextButton(onClick = { nav.navigate(Screen.Tree) }) { Text("Skip for now") }
    }
}
