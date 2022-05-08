package fr.be2.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Parametres extends MainActivity {
    SQLHelper database;
    EditText CodeVisiteur;
    EditText nom;
    EditText prenom;
    EditText email;
    EditText urlServeur;
    EditText MotDepasse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);
        database = new SQLHelper(this);
        init();
    }

    public void init() {
        CodeVisiteur = findViewById(R.id.codeVisiteur);
        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        email = findViewById(R.id.email);
        urlServeur = findViewById(R.id.urlServeur);
        MotDepasse = findViewById(R.id.MotdePasse);
    }


    public void ClickValider(View v) {
        switch (v.getId()) {
            case R.id.Valider:
                if (CodeVisiteur.getText().toString().trim().length() == 0 || nom.getText().toString().trim().length() == 0 ||
                        prenom.getText().toString().trim().length() == 0 || email.getText().toString().trim().length() == 0 ||
                        urlServeur.getText().toString().trim().length() == 0||MotDepasse.getText().toString().trim().length() == 0) {
                    //teste si le champ quantite est renseigné ou si le champ type n'est pas vide
                    // et qu'on a selectionne l'une des 4 possibilités et si la date est renseignée
                    afficherMessage("Erreur!" , "Champ vide");
                    return;

                } else {
                    Integer codeVisiteur = Integer.parseInt(String.valueOf(CodeVisiteur.getText()));
                    String Nom = nom.getText().toString();
                    String Prenom = prenom.getText().toString();
                    String Email = email.getText().toString();
                    String UrlServeur = urlServeur.getText().toString();
                String MyMotDepasse = MotDepasse.getText().toString();

                }
                break;
        }

    }
}