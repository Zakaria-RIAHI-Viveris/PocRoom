package com.viveris.pocroom.db

import com.viveris.pocroom.metier.User
import com.viveris.pocroom.ui.IDatabaseCallback
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class RoomDatabaseManager(
        roomDatabase: AppRoomDatabase,
        private val compositeDisposable: CompositeDisposable,
        private val callback: IDatabaseCallback
) {

    private val userDao: UserDao = roomDatabase.utilisateurDao()

    fun insertUsers(nbEntree: Int) {
        compositeDisposable.add(
                userDao.insertUsers(createListToInsert(nbEntree))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe (
                                { callback.insertSuccess() },
                                { callback.insertFailure() }
                        )
        )
    }

    fun getAllUsers() {
        compositeDisposable.add(
                userDao.getAllUsers()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                { callback.queryAllSuccess() },
                                { callback.queryAllSuccess() }
                        )
        )
    }

    fun queryUsers(suffix: String) {
        compositeDisposable.add(
                userDao.getUsersBySuffix(suffix)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                { callback.querySucess() },
                                { callback.queryFailure() }
                        )
        )
    }

    fun deleteAllUsers() {
        compositeDisposable.add(
                userDao.deleteAllUsers()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                { callback.deleteSuccess() },
                                { callback.deleteFailure() }
                        )
        )
    }

    private fun createListToInsert(nbEntree: Int): List<User> {
        val res: MutableList<User> = ArrayList()
        for (i in 0 until nbEntree) {
            val user = User()
            user.nom = "nom$i"
            user.prenom = "prenom$i"
            user.numCp = "num cp$i"
            user.motDePasse = "mot de passe$i"
            user.isActif = true
            user.profil = "profil $i"
            user.dateConnexion = "date connexion "
            user.region = "region $i"
            user.responsable = "responsable $i"
            user.secteur = "secteur $i"
            user.ville = "ville $i"
            user.equipe = 1
            res.add(user)
        }
        return res
    }

}