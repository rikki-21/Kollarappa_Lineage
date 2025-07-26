package com.rikki.kollarappalineage

import android.content.Context
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

actual class AuthServiceImpl(context: Context) : AuthService {
    private val firebaseAuth = Firebase.auth
    private val _current = MutableStateFlow<User?>(firebaseAuth.currentUser?.toUser())
    override val currentUser: StateFlow<User?> = _current.asStateFlow()

    override suspend fun signInWithGoogle(): User? {
        val oneTap = Identity.getSignInClient(context)
        val result = oneTap.beginSignIn( /* GoogleIdOption… */ ).await()
        val googleCred = GoogleAuthProvider.getCredential(result.googleIdToken, null)
        val user = firebaseAuth.signInWithCredential(googleCred).await().user!!.toUser()
        _current.value = user; return user
    }
    override suspend fun signInSilently() = firebaseAuth.currentUser?.toUser()
    override fun signOut() { firebaseAuth.signOut(); _current.value = null }
}
