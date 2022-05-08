package fr.be2.gsb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLHelper extends SQLiteOpenHelper {
    //declaration des variables
    public static final String DB_NAME = "GSB.db";
    public static final String DB_TABLE = "FRAIS";
    public static final String ID = "ID"; //ce sera les colonnes de la table frais
    public static final String TYPE = "TYPE";
    public static final String QUANTITE = "QUANTITE";
    public static final String DATE = "DATE";
    public static final String MONTANT = "MONTANT";
    public static final String DATEACTU = "DATEACTU";
    public static final String LIBELLE = "LIBELLE";


    /**
     * Crée une table par une requete SQL
     */
    private static final String CREATE_TABLE = "CREATE TABLE " + DB_TABLE + " (" + ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT," + TYPE + " TEXT," + QUANTITE + " INTEGER," + DATE
            + " TEXT," + MONTANT + " REAL," + LIBELLE + " TEXT," + DATEACTU + " DATETIME DEFAULT CURRENT_TIMESTAMP)";

    private static final String CREATE_Parametre = "CREATE TABLE PARAMETRE (id int primary key, codeV text, " +
            "nom text,prenom text,email text, urlserveur text)";


    /**
     * @param context
     */
    public SQLHelper(Context context) {

        super(context, DB_NAME, null, 1);//permet d'acceder aux membres de la classe mère

    }


    /**
     * constructeur de la classe
     * methode venant de SQLLite et permettant d'executer une requete SQL
     *
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE);
        sqLiteDatabase.execSQL(CREATE_Parametre);
       // init_PARAMETRE();

    }


    /**
     * destructeur de la classe
     *
     * @param sqLiteDatabase
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        onCreate(sqLiteDatabase);
    }

    /**
     * Insère dans le BDD les données type, quantité, date, montant et libellé renseignées par le visiteur
     *
     * @param type
     * @param quantite
     * @param date
     * @param montant
     * @param libelle
     * @return booleen
     */
    public boolean insertData(String type, Integer quantite, String date, double montant, String libelle) {
        //on cree une variable de type sqLitedatabase pour pouvoir y acceder
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TYPE, type);
        contentValues.put(QUANTITE, quantite);
        contentValues.put(DATE, date);
        contentValues.put(MONTANT, montant);
        contentValues.put(LIBELLE, libelle);
        //insert sert a insèrer des donnees, elle insère dans notre table contentValue les contenus
        // des variables que l'utilisateur renseigne
        long result = db.insert(DB_TABLE, null, contentValues);
        return result != -1;

    }

    public void  init_PARAMETRE() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", 1);
        contentValues.put("codeVisiteur", 0);
        contentValues.put("nom", "");
        contentValues.put("prenom", "");
        contentValues.put("email", "");
        contentValues.put("urlServeur", "");
        long result = db.insert(CREATE_Parametre, null, contentValues);
        return;
    }

    public boolean update_PARAMETRE(Integer codeVisiteur, String nom, String prenom, String email, String urlServeur) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("codeV", codeVisiteur);
        contentValues.put("nom", nom);
        contentValues.put("prenom", prenom);
        contentValues.put("email", email);
        contentValues.put("urlServeur", urlServeur);
        long result = db.update("PARAMETRES", contentValues,"ID=1",null);
        return result != -1;
    }

    public Cursor viewData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from " + DB_TABLE;
        //cursor: type, pointeur: pr parcourir les lignes ds les resultats de la requete. Null car pas de where
        Cursor pointeur = db.rawQuery(query, null);
        return pointeur;

    }
    /**
     * Supprime un frais ayant pour id l'id passé en paramètre
     *
     * @param ID
     * @return booleen
     */
    public boolean deleteData(Integer ID) {
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(DB_TABLE, "ID=" + ID, null);

        return result != -1;

    }
    /**
     * Interroge la BDD et retourne tous les frais enregistrés
     *
     * @return le curseur contenant les frais
     */
    public Cursor fetchAllFrais() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor Cursor = db.query(DB_TABLE, new String[]{"rowid _id", LIBELLE,
                        ID, DATE, DATEACTU, MONTANT, QUANTITE},
                null, null, null, null, null);

        if (Cursor != null) {
            Cursor.moveToFirst();
        }
        return Cursor;
    }
// pour rajouter un filtre dans la fonction
    public Cursor fetchFrais(String filtre) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor Cursor = db.query(DB_TABLE, new String[]{"rowid _id", LIBELLE,
                        ID, DATE, DATEACTU, MONTANT, QUANTITE},
                filtre, null, null, null, null);

        if (Cursor != null) {
            Cursor.moveToFirst();
        }
        return Cursor;
    }

    public SQLHelper open() throws SQLException {

        SQLiteDatabase db = this.getWritableDatabase();
        return this;

    }
}