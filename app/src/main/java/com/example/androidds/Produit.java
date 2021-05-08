package com.example.androidds;

public class Produit {
    private  String pid;
    private String imageUrl;
    private String nomfourni;
    private String label;
    private String prix;
    private String qte;
    private String total;


    public Produit(String pid, String imageUrl, String nomfourni, String label, String prix, String qte) {
        this.pid = pid;
        this.imageUrl = imageUrl;
        this.nomfourni = nomfourni;
        this.label = label;
        this.prix = prix;
        this.qte = qte;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNomfourni() {
        return nomfourni;
    }

    public void setNomfourni(String nomfourni) {
        this.nomfourni = nomfourni;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getQte() {
        return qte;
    }

    public void setQte(String qte) {
        this.qte = qte;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }
}
