package com.rikki.kollarappalineage

@Composable
private fun AppDrawer(user:User?, nav:NavHostController, auth:AuthService) {
    Column {
        if (user!=null) {
            Row(Modifier.fillMaxWidth().padding(16.dp)) {
                AsyncImage(user.photoUrl, null, Modifier.size(48.dp).clip(CircleShape))
                Spacer(16.dp); Text(user.name, style = MaterialTheme.typography.titleMedium)
            }
            Divider()
            DrawerItem("Add person") { nav.navigate(Screen.AddPerson) }
            DrawerItem("Logout") { auth.signOut(); nav.navigate(Screen.Login) }
        } else {
            DrawerItem("Login") { nav.navigate(Screen.Login) }
        }
    }
}
