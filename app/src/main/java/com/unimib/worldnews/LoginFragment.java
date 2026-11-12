package com.unimib.worldnews;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import androidx.navigation.NavController; //import per la navigazione
import androidx.navigation.fragment.NavHostFragment; //import per la navigazione

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    private TextInputEditText editTextEmail, editTextPassword;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() { //crea una nuoa istanza del frgament e lo ritoran, utile per passre informazioni
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //non esiste setContentView
    }

    @Override
    /*public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {*/
        // Inflate the layout for this fragment
        public View onCreateView(
                @NonNull LayoutInflater inflater,
                @Nullable ViewGroup container,
                @Nullable Bundle savedInstanceState) {

            return inflater.inflate(R.layout.fragment_login, container, false);
        }

        @Override
        public void onViewCreated(@NonNull View view,
                @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            editTextEmail = view.findViewById(R.id.textInputEmail);
            editTextPassword = view.findViewById(R.id.textInputPassword);

            Button loginButton = view.findViewById(R.id.loginButton);

            loginButton.setOnClickListener(v -> {

                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString();

                if (isEmailOk(email)) {

                    if (isPasswordOk(password)) {
                        /*Snackbar.make(view,
                                "Credenziali formalmente corrette",
                                Snackbar.LENGTH_SHORT).show();*/
                        NavController navController =
                                NavHostFragment.findNavController(this);

                        Log.d("LOGIN_TEST", "Sto navigando verso PickACountryActivity");
                        navController.navigate(
                                R.id.action_loginFragment_to_pickACountryActivity
                        );

                    } else {
                        editTextPassword.setError(
                                "The password must be at least 8 chars long");
                    }

                } else {
                    editTextEmail.setError("Check your email");
                }
            });
        }
        private boolean isEmailOk(String email) {
            return true;
        }

        private boolean isPasswordOk(String password) {
            return true;
        }
    }

