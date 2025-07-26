interface AuthService {
    suspend fun signInSilently(): User?
    suspend fun signInWithGoogle(): User?
    fun signOut()
    val currentUser: StateFlow<User?>
}
