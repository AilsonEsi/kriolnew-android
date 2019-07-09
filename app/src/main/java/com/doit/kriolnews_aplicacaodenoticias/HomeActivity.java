package com.doit.kriolnews_aplicacaodenoticias;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.doit.kriolnews_aplicacaodenoticias.account.LoginActivity;
import com.doit.kriolnews_aplicacaodenoticias.enumeracao.FeedsProviders;
import com.doit.kriolnews_aplicacaodenoticias.fragments.FragmentNewContent;
import com.doit.kriolnews_aplicacaodenoticias.model.ReadRss;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //app
    private TextView name_display;
    private TextView email_display;
    private Button logout;
    private RecyclerView recyclerView;


    //firebase
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),PostsActivity.class));
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        //.setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_home,new FragmentNewContent()).commit();

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        logout = findViewById(R.id.action_logout);

        View mHeaderView = navigationView.getHeaderView(0);
        name_display = mHeaderView.findViewById(R.id.name_display);
        email_display = mHeaderView.findViewById(R.id.email_display);
        name_display.setText(currentUser.getDisplayName());
        email_display.setText(currentUser.getEmail());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            mAuth.signOut();
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        ReadRss readRss;
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            getSupportFragmentManager().beginTransaction().replace(R.id.content_home,new FragmentNewContent()).commit();
            setTitle("Kriolnews");
        } else if (id == R.id.nav_expre) {
            readRss = new ReadRss(this, recyclerView, FeedsProviders.EXPRE.getText());
            readRss.execute();
            setTitle("Expresso das Ilhas");
        } else if (id == R.id.nav_anacao) {
            //Call Read rss asyntask to fetch rss
            readRss = new ReadRss(this, recyclerView,FeedsProviders.ANACAO.getText());
            readRss.execute();
            setTitle("A Nação");
        } else if (id == R.id.nav_cesports) {
            readRss = new ReadRss(this, recyclerView,FeedsProviders.CE.getText());
            readRss.execute();
            setTitle("Criol Sports");
        } else if (id == R.id.nav_sma) {
            readRss = new ReadRss(this, recyclerView,FeedsProviders.SMA.getText());
            readRss.execute();
            setTitle("S.Magazine");
        } else if (id == R.id.nav_jeco) {
            readRss = new ReadRss(this, recyclerView,FeedsProviders.JE.getText());
            readRss.execute();
            setTitle("Jornal Economico");
        } else if (id == R.id.nav_opais) {
            readRss = new ReadRss(this, recyclerView,FeedsProviders.OPAIS.getText());
            readRss.execute();
            setTitle("O Pais");
        } else if (id == R.id.nav_gprofile) {
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        } else if (id == R.id.nav_conf) {
            startActivity(new Intent(getApplicationContext(),ConfigActivity.class));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
