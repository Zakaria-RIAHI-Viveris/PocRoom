package com.viveris.pocroom.metier

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = User.NOM_TABLE)
class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = NOM_COLONNE_ID)
    var id: Long = 0

    @ColumnInfo(name = NOM_COLONNE_NUM_CP)
    var numCp: String? = null

    @ColumnInfo(name = NOM_COLONNE_NOM)
    var nom: String? = null

    @ColumnInfo(name = NOM_COLONNE_PRENOM)
    var prenom: String? = null

    @ColumnInfo(name = NOM_COLONNE_MOT_DE_PASSE)
    var motDePasse: String? = null

    @ColumnInfo(name = NOM_COLONNE_PROFIL)
    var profil: String? = null

    @ColumnInfo(name = NOM_COLONNE_ACTIF)
    var isActif = false

    @ColumnInfo(name = NOM_COLONNE_RESPONSABLE)
    var responsable: String? = null

    @ColumnInfo(name = NOM_COLONNE_SECTEUR)
    var secteur: String? = null

    @ColumnInfo(name = NOM_COLONNE_VILLE)
    var ville: String? = null

    @ColumnInfo(name = NOM_COLONNE_REGION)
    var region: String? = null

    @ColumnInfo(name = NOM_COLONNE_DATE_CONNEXION)
    var dateConnexion: String? = null

    @ColumnInfo(name = NOM_COLONNE_EQUIPE)
    var equipe: Long = 0

    companion object {
        const val NOM_TABLE = "Utilisateur"
        private const val NOM_COLONNE_ID = "id"
        const val NOM_COLONNE_NUM_CP = "numCp"
        private const val NOM_COLONNE_NOM = "nom"
        private const val NOM_COLONNE_PRENOM = "prenom"
        private const val NOM_COLONNE_MOT_DE_PASSE = "motDePasse"
        private const val NOM_COLONNE_PROFIL = "profil"
        private const val NOM_COLONNE_ACTIF = "actif"
        private const val NOM_COLONNE_RESPONSABLE = "responsable"
        private const val NOM_COLONNE_SECTEUR = "secteur"
        private const val NOM_COLONNE_VILLE = "ville"
        private const val NOM_COLONNE_REGION = "region"
        private const val NOM_COLONNE_DATE_CONNEXION = "dateConnexion"
        private const val NOM_COLONNE_EQUIPE = "equipe"
    }
}