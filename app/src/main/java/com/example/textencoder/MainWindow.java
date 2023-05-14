package com.example.textencoder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;


public class MainWindow extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    Button encrypt, decrypt, btnActivate;
    String password = "tekstas";

    public static EditText pswInput;
    public static EditText messageInput;
    public static TextView textEnDe;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_window);

        EditText inputPsw = (EditText) findViewById(R.id.pswArea);
        encrypt = (Button) findViewById(R.id.btnEncrypt);
        decrypt = (Button) findViewById(R.id.btnDecrypt);
        EditText message = (EditText) findViewById(R.id.inputMessage);
        textEnDe = (TextView) findViewById(R.id.textEnDe);
        btnActivate = (Button) findViewById(R.id.btnActivate);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:{
                        Toast.makeText(MainWindow.this, "Home selected", Toast.LENGTH_SHORT).show();
                        break;}
                    case R.id.aboutMe:{
                        Toast.makeText(MainWindow.this, "sbout Me selected", Toast.LENGTH_SHORT).show();
                        break;}
                    case R.id.exit: {
                        // Finish the current activity and remove it from the back stack
                        finish();
                        // Remove all activities from the back stack and exit this app
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        System.exit(0);
                        break;
                    }
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        btnActivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputPassword pass = new InputPassword(inputPsw, message);
                pass.unclockTextArea();
            }
        });

        encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Encoder encode = new Encoder(message);
                encode.encode();
                encode.pasteMessage();
            }
        });

        decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Decoder decode = new Decoder(message);
                decode.decode();
                decode.pasteMessage();

            }
        });



        textEnDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence text = textEnDe.getText();
                ClipboardManager clipboard = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                }
                ClipData clip = ClipData.newPlainText("Copied Text", text);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getApplicationContext(), "Text copied", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }
}