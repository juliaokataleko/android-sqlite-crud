package com.example.androidsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DadosUtilizadorActivity extends AppCompatActivity {

    EditText etUsername, etName, etPassword, etAddress, etPhone;
    Button btVoltar, btAtualizar, btExcluir;
    Intent i;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_utilizador);

        etUsername = findViewById(R.id.etEditUsername);
        etName = findViewById(R.id.etEditName);
        etPassword = findViewById(R.id.etEditPassword);
        etAddress = findViewById(R.id.etEditAddress);
        etPhone = findViewById(R.id.etEditPhone);

        btVoltar = findViewById(R.id.btVoltar);
        btAtualizar = findViewById(R.id.btAtualizar);
        btExcluir = findViewById(R.id.btExcluir);

        db = new DBHelper(this);
        i = getIntent();
        String username = i.getExtras().getString("username");

        carregarDadosDoUtilizador(username);

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(0, i);
                finish();
            }
        });

        btAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!etUsername.getText().toString().trim().isEmpty() && !etPassword.getText().toString().trim().isEmpty()) {
                    long res = db.updateUser(
                            etName.getText().toString(),
                            etUsername.getText().toString(),
                            etAddress.getText().toString(),
                            etPhone.getText().toString(),
                            etPassword.getText().toString()
                    );

                    if(res > 0) {
                        Toast.makeText(DadosUtilizadorActivity.this, "Utilizador atualizado com sucesso", Toast.LENGTH_SHORT).show();
                        setResult(2, i);
                        finish();
                    } else {
                        Toast.makeText(DadosUtilizadorActivity.this, "Erro ao atualizar utilizador", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DadosUtilizadorActivity.this, "Preencha os campos obrigatórios", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!etUsername.getText().toString().trim().isEmpty()) {
                    long res = db.deleteUser(etUsername.getText().toString());
                    if(res > 0) {
                        Toast.makeText(DadosUtilizadorActivity.this, "Utilizador excluído", Toast.LENGTH_SHORT).show();
                        setResult(2, i);
                        finish();
                    } else {
                        Toast.makeText(DadosUtilizadorActivity.this, "Erro ao excluir utilizador", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DadosUtilizadorActivity.this, "O usuário está vazio", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void carregarDadosDoUtilizador(String username) {
        Cursor c = db.find(username);
        c.moveToFirst();

        if(c.getCount() == 1) {

            String name = c.getString(c.getColumnIndex("name"));
            String username2 = c.getString(c.getColumnIndex("username"));
            String phone = c.getString(c.getColumnIndex("phone"));
            String address = c.getString(c.getColumnIndex("address"));
            String password = c.getString(c.getColumnIndex("password"));

            etName.setText(name);
            etUsername.setText(username2);
            etPhone.setText(phone);
            etAddress.setText(address);
            etPassword.setText(password);

        } else if(c.getCount() == 0) {
            Toast.makeText(this, "Utilizador não encontrado.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Erro ao carregar utilizador.", Toast.LENGTH_SHORT).show();
        }
    }

}