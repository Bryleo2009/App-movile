package com.example.ofsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.ofsystem.databinding.ActivityNavMainBinding;
import com.google.android.material.navigation.NavigationView;

public class NavMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityNavMainBinding binding;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNavMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarNavMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        // Configuración del menú de navegación y la barra de acciones
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.listProductFragment, R.id.loginFragment, R.id.carritoFragment)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_nav_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        // Establecer el listener para el menú de navegación
        navigationView.setNavigationItemSelectedListener(this);

        // Inicializar SharedPreferences
        sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_nav_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_main, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_nav_main);

        if (id == R.id.logoutItem) {
            logout();
            hideNavigationView(); // Ocultar el menú lateral después de la navegación
            return true;
        }

        NavDestination destination = navController.getGraph().findNode(id);
        if (destination != null) {
            navController.navigate(destination.getId());
            hideNavigationView(); // Ocultar el menú lateral después de la navegación
            return true;
        }

        return false;
    }

    private void hideNavigationView() {
        DrawerLayout drawerLayout = binding.drawerLayout;
        drawerLayout.closeDrawer(GravityCompat.START);
    }



    private void logout() {
        // Limpiar SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        // Obtener referencia al NavController
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_nav_main);


        NavigationView navigationView = findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();
        MenuItem carritoItem = menu.findItem(R.id.carritoFragment);
        carritoItem.setVisible(false);
        MenuItem login = menu.findItem(R.id.loginFragment);
        login.setVisible(true);
        MenuItem logoutItem = menu.findItem(R.id.logoutItem);
        logoutItem.setVisible(false);
        // Navegar al fragmento ListProductFragment
        navController.navigate(R.id.listProductFragment);
    }

}
