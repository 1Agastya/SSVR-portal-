package com.example.ssrv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Deshboard extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deshboard);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        // Setup navigation item selection handling
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
              //  Fragment selectedFragment = null;
             /*   switch (item.getItemId()) {
                    case R.id.nav_home:
                        // Navigate to Home fragment
                        Toast.makeText(Deshboard.this, "jhd", Toast.LENGTH_SHORT).show();
                     //   selectedFragment = new HomeFragment();
                        break;

                    case R.id.nav_profile:
                        // Navigate to Profile fragment\
                        Toast.makeText(Deshboard.this, "jhd", Toast.LENGTH_SHORT).show();

                        //   selectedFragment = new ProfileFragment();
                        break;

                    case R.id.nav_settings:
                        // Navigate to Settings fragment
                        Toast.makeText(Deshboard.this, "jhd", Toast.LENGTH_SHORT).show();

                        // selectedFragment = new SettingsFragment();
                        break;

                    case R.id.nav_logout:
                        // Handle logout (clear session, navigate to login screen)
                        Toast.makeText(Deshboard.this, "jhd", Toast.LENGTH_SHORT).show();

                        //    handleLogout();
                        return true; // No fragment to replace
                }

            /*    if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_content_view, selectedFragment)
                            .commit();
                } */
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
