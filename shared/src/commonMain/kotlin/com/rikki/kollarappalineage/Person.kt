package com.rikki.kollarappalineage

import com.android.identity.util.UUID
import com.google.firebase.dataconnect.LocalDate

@Serializable
data class Person(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val birthDate: LocalDate? = null,
    val profileUrl: String? = null,
    val relation: Relation = Relation.CHILD
)

enum class Relation { CHILD, SPOUSE, PARENT }
