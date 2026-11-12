package com.unimib.worldnews;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import org.apache.commons.validator.routines.EmailValidator;

import java.io.IOException;


public class LoginActivity extends AppCompatActivity {

    /*--------- versione senza Fragment*/
    public final String TAG = LoginActivity.class.getName(); //da commentare nella versione con Fragment
    private TextView editTextEmail;
    private TextView editTextPassword;
    //intent implicito per caricamento immagine: da incollare prima di onCreate
    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>(){

        @Override
        public void onActivityResult(Uri uri){
            //Handle the returned Uri
            Log.d(TAG, "Eccomi");
            //modifica immagine di ImageView con quella scelta dall'utente: togliere il colore dal layout per vederla
            //altrimenti si vedrà un rettangolo nero
            try{
                Bitmap b = MediaStore.Images.Media.getBitmap(getContentResolver(), uri); //estra la bitmap dall'uri dell'immagine selezionata
                ((ImageView)findViewById(R.id.imageView)).setImageBitmap(b);
            }
            catch(IOException e){
                throw new RuntimeException(e);
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        editTextEmail = findViewById(R.id.textInputEmail);
        editTextPassword = findViewById(R.id.textInputPassword);

        editTextEmail.setEnabled(true);
        editTextEmail.setFocusable(true);
        editTextEmail.setFocusableInTouchMode(true);
        editTextEmail.requestFocus();
        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(view -> {



            if (isEmailOk(editTextEmail.getText().toString().trim())) {

                if (isPasswordOk(editTextPassword.getText().toString())) {
                    Log.d(TAG, "Launch new Activity");
                    // Credenziali formalmente corrette
                    //intent esplicitop
                    /*Intent intent = new Intent(this, PickACountryActivity.class);
                    intent.putExtra("EMAIL_KEY", editTextEmail.getText().toString()); //pickACountry riceverà un oggetto Bundle ocme parametro
                    startActivity(intent);*/
                    //Intent implicito: è il sistema operativo che deifnisce l'app che riceverà l'azione
                    /*Intent intent = new Intent();
                    intent.setAction(intent.ACTION_SEND);
                    intent.putExtra(intent.EXTRA_TEXT, "Ciao!");
                    intent.setType("text/plain");
                    try{
                        startActivity(intent);
                    }catch(ActivityNotFoundException e){}*/
                    /*un esempio da Google maps
                    // Create a Uri from an intent string. Use the result to create an Intent.
                    Uri gmmIntentUri = Uri.parse("google.streetview:cbll=46.414382,10.013988");
                    // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    // Make the Intent explicit by setting the Google Maps package
                    mapIntent.setPackage("com.google.android.apps.maps");
                    // Attempt to start an activity that can handle the Intent
                    startActivity(mapIntent);*/
                    // lancio dell'intent implicito per il caricamento dell'immagine
                    mGetContent.launch("image/*");
                } else {
                    editTextPassword.setError(
                            "The password must be at least 8 chars long"
                    );

                   /* Snackbar.make(
                            findViewById(android.R.id.content),
                            "Check your password",
                            Snackbar.LENGTH_LONG
                    ).show();*/
                }

            } else {

                editTextEmail.setError("Check your email");

               /* Snackbar.make(
                        findViewById(android.R.id.content),
                        "Check your email",
                        Snackbar.LENGTH_LONG
                ).show();*/
            }
        });

    }



    private boolean isEmailOk(String email) {
        //return true;
        return EmailValidator.getInstance().isValid(email);
    }

    private boolean isPasswordOk(String password) {
        return password.length() > 7;
    }

    /* fine versione senza Fragment*/

}
