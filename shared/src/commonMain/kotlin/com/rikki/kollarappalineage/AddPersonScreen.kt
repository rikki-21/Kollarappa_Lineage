package com.rikki.kollarappalineage

import androidx.compose.runtime.Composable

@Composable
fun AddPersonScreen(vm: AddPersonViewModel = getViewModel()) {
    val name by vm.name.collectAsState()
    val relation by vm.relation.collectAsState()
    val saving by vm.saving.collectAsState()
    Column(Modifier.padding(16.dp)) {
        OutlinedTextField(name, onValueChange = vm.name::value::set, label={Text("Name")})
        RelationSelector(selected = relation) { vm.relation.value = it }
        Button(onClick = vm::save, enabled = !saving) {
            if (saving) CircularProgressIndicator(); else Text("Save")
        }
    }
}
