package com.viveris.pocroom.db

import androidx.room.*
import com.viveris.pocroom.metier.User
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface UserDao {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<User>): Completable

    @Transaction
    @Query("SELECT * FROM " + User.NOM_TABLE)
    fun getAllUsers(): Single<List<User>?>

    @Transaction
    @Query("SELECT * FROM " + User.NOM_TABLE + " WHERE " + User.NOM_COLONNE_NUM_CP + " LIKE :suffix")
    fun getUsersBySuffix(suffix: String): Single<List<User>?>

    @Transaction
    @Query("DELETE FROM " + User.NOM_TABLE)
    fun deleteAllUsers(): Completable
}