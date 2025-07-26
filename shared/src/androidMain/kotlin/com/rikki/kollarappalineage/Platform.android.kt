package com.rikki.kollarappalineage

import com.google.firebase.Firebase

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual class FamilyRepositoryImpl : FamilyRepository {
    private val col = Firebase.firestore.collection("families")
    override suspend fun getFamily() =
        col.document(HEAD_ID).collection("members").get().documents.map {
            it.data().toPerson()
        }
    override suspend fun addPerson(person: Person) {
        col.document(HEAD_ID).collection("members")
            .document(person.id).set(person)
    }
}
