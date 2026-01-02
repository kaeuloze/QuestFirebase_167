package com.example.myfirebase.repositori

import com.example.myfirebase.modeldata.Siswa

interface RepositorySiswa{
    suspend fun getAllSiswa(): List<Siswa>
    suspend fun insertSiswa(siswa: Siswa)
}
class FirebaseRepositorySiswa : RepositorySiswa {
    private val db = FirebaseFirestore.getInstance()
    private val collection = db.collection("siswa")

    override suspend fun getDataSiswa(): List<Siswa> {
        return try {
            collection.get().await().documents.map { doc ->
                Siswa(
                    id = doc.getLong("id") ?: 0,
                    nama = doc.getString("nama") ?: "",
                    alamat = doc.getString("alamat") ?: "",
                    telpon = doc.getString("telpon") ?: "",
                )
            }
        } catch (e: Exception) {
            emptyList()
        }

    }

}