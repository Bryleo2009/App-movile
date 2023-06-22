package com.example.ofsystem.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ofsystem.Api.AuthApi;
import com.example.ofsystem.Config.Config;
import com.example.ofsystem.FormProductActivity;
import com.example.ofsystem.ListProduct;
import com.example.ofsystem.MenuActivity;
import com.example.ofsystem.Model.LoginRequest;
import com.example.ofsystem.Model.LoginResponse;
import com.example.ofsystem.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment implements View.OnClickListener{

    Button btnLogin;
    private EditText usernameEditText;
    private EditText passwordEditText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        // Inicializar los elementos de la interfaz de usuario
        usernameEditText = view.findViewById(R.id.usernameEditText);
        passwordEditText = view.findViewById(R.id.passwordEditText);
        btnLogin = view.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v == btnLogin){
            // Obtener las credenciales ingresadas por el usuario
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            // Realizar la solicitud de inicio de sesión
            login(username, password);
        }
    }

    private void login(String username, String password) {
        // Crear un objeto Retrofit con la URL base de tu API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crear una instancia de la interfaz de tu API
        AuthApi authApi = retrofit.create(AuthApi.class);

        // Crear un objeto de solicitud de inicio de sesión con las credenciales del usuario
        LoginRequest loginRequest = new LoginRequest(username, password);

        // Realizar la solicitud de inicio de sesión en el backend
        Call<LoginResponse> call = authApi.login(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    // Obtener el token de autenticación de la respuesta
                    String authToken = response.body().getToken();

                    // Guardar el token en SharedPreferences o en otro mecanismo de almacenamiento
                    saveAuthToken(authToken);

                    // Redirigir a la siguiente actividad después de iniciar sesión
                    Intent intent = new Intent(getActivity(), MenuActivity.class);
                    startActivity(intent);
                } else {
                    // Manejar el caso de inicio de sesión fallido (credenciales inválidas, etc.)
                    Toast.makeText(getActivity(), "Inicio de sesión fallido", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // Manejar el caso de error de red u otro error en la solicitud
                Toast.makeText(getActivity(), "Error en la solicitud de inicio de sesión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveAuthToken(String authToken) {
        // Guardar el token en SharedPreferences o en otro mecanismo de almacenamiento
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("authToken", authToken);
        editor.apply();
    }
}