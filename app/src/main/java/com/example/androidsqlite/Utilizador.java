package com.example.androidsqlite;

public class Utilizador {

    String name;
    String username;
    String email;
    String phone;
    String address;
    String password;

    public Utilizador(String name, String username, String email, String phone, String address, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;
    }

    public Utilizador(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public Utilizador(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Utilizador() {
        name = "";
        username = "";
        password = "";

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return name  +
                "\n" + username;
    }
}
