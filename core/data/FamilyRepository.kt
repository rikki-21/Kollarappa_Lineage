interface FamilyRepository {
    suspend fun getFamily(): List<Person>
    suspend fun addPerson(person: Person): Unit
}
