package com.example.androidsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NovoUtilizadorActivity extends AppCompatActivity {

    EditText etUsername, etName, etPassword, etAddress, etPhone;
    Button btCadastrar, btCancelar;
    Intent i;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_utilizador);

        etUsername = findViewById(R.id.etUsername);
        etName = findViewById(R.id.etName);
        etPassword = findViewById(R.id.etPassword);
        etAddress = findViewById(R.id.etAddress);
        etPhone = findViewById(R.id.etPhone);

        btCadastrar = findViewById(R.id.btCadastrar);
        btCancelar = findViewById(R.id.btCancelar);

        db = new DBHelper(this);

        i = getIntent();

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = etName.getText().toString();
                String username = etUsername.getText().toString();
                String address = etAddress.getText().toString();
                String phone = etPhone.getText().toString();
                String password = etPassword.getText().toString();

                if(!username.trim().isEmpty() && !name.trim().isEmpty() && !password.trim().isEmpty()) {
                    long res = db.insertUser(name, username, address, phone, password);

                    if(res > 0) {
                        Toast.makeText(NovoUtilizadorActivity.this, "Utilizador inserido com sucesso", Toast.LENGTH_SHORT).show();
                        setResult(1, i);
                        finish();
                    } else {
                        Toast.makeText(NovoUtilizadorActivity.this, "Houve um erro ao inserir o utilizador", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(0, i);
                finish();
            }
        });

    }
}