package com.example.androidsqlite;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvUtilizadores;
    List<Utilizador> listaUtilizadores;
    DBHelper db;
    Button btNovoUtilizador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this);
        listaUtilizadores = new ArrayList<>();

        lvUtilizadores = findViewById(R.id.listUsers);
        btNovoUtilizador = findViewById(R.id.btNovoUtilizador);

        btNovoUtilizador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NovoUtilizadorActivity.class);
                startActivityForResult(intent, 1);

            }
        });

        lvUtilizadores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, DadosUtilizadorActivity.class);
                intent.putExtra("name", listaUtilizadores.get(i).getName());
                intent.putExtra("phone", listaUtilizadores.get(i).getPhone());
                intent.putExtra("username", listaUtilizadores.get(i).getUsername());
                intent.putExtra("address", listaUtilizadores.get(i).getAddress());
                intent.putExtra("password", listaUtilizadores.get(i).getPassword());
                startActivityForResult(intent, 2);
            }
        });

        listarUtilizadores();
    }

    private void listarUtilizadores() {
        listaUtilizadores.clear();
        Cursor c = db.allUsers();
        c.moveToFirst();

        if(c.getCount()>0) {
            do {
                @SuppressLint("Range") String username = c.getString(c.getColumnIndex("username"));
                @SuppressLint("Range") String name = c.getString(c.getColumnIndex("name"));
                @SuppressLint("Range") String password = c.getString(c.getColumnIndex("password"));
                listaUtilizadores.add(new Utilizador(name, username, password));
            } while(c.moveToNext());
        }

        ArrayAdapter<Utilizador> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaUtilizadores);

        lvUtilizadores.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == 1) {
            listarUtilizadores();
        } else if(requestCode == 2 && (resultCode == 1 || resultCode == 2)) {
            listarUtilizadores();
        }
    }
}