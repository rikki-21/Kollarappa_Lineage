package com.rikki.kollarappalineage

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class AddPersonViewModel(
    private val repo: FamilyRepository
) : ViewModel() {
    val name = MutableStateFlow("")
    val relation = MutableStateFlow(Relation.CHILD)
    val saving = MutableStateFlow(false)

    fun save() = viewModelScope.launch {
        saving.value = true
        repo.addPerson(Person(name = name.value, relation = relation.value))
        saving.value = false
    }
}
