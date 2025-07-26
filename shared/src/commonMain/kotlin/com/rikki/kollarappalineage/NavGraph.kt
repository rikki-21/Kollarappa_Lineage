package com.rikki.kollarappalineage

sealed interface Screen : Parcelable {
    @Serializable object Tree : Screen
    @Serializable object AddPerson : Screen
    @Serializable object Login : Screen
}

@Composable
fun AppRoot(auth: AuthService) {
    val nav = rememberNavController()
    val user by auth.currentUser.collectAsState()

    Scaffold(
        drawerContent = { AppDrawer(user, nav, auth) },
        topBar = { TopAppBar(title={ Text("FamilyTree") }) }
    ) {
        NavHost(nav, if (user==null) Screen.Login else Screen.Tree) {
            composable<Screen.Tree> { TreeScreen() }
            composable<Screen.AddPerson> { AddPersonScreen() }
            composable<Screen.Login> { LoginScreen(auth, nav) }
        }
    }
}
