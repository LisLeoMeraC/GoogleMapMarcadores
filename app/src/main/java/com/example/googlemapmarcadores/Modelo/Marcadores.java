package com.example.googlemapmarcadores.Modelo;

public class Marcadores {
    private String facultad;
    private String decano;
    private String email;
    private String logo;
    private double lat;
    private double longuitud;
    private String tel;

    public Marcadores(String facultad, String decano, String email, String logo, double lat, double longuitud, String tel) {
        this.facultad = facultad;
        this.decano = decano;
        this.email = email;
        this.logo = logo;
        this.lat = lat;
        this.longuitud = longuitud;
        this.tel = tel;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getDecano() {
        return decano;
    }

    public void setDecano(String decano) {
        this.decano = decano;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLonguitud() {
        return longuitud;
    }

    public void setLonguitud(double longuitud) {
        this.longuitud = longuitud;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
