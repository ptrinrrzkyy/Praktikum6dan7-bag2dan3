package com.example.praktikum6_13120220034;

import org.json.JSONException;
import org.json.JSONObject;

public class Mahasiswa {
    private String stb;
    private String nama;
    private String angkatan;

    private JSONObject jsonObject;
    private Mahasiswa mhs;

    public Mahasiswa(String stb, String nama, String angkatan) {
        this.stb = stb;
        this.nama = nama;
        this.angkatan = angkatan;
    }

    public String getStb() {
        return stb;
    }

    public String getNama() {
        return nama;
    }

    public String getAngkatan() {
        return angkatan;
    }
    public JSONObject toJSON() throws JSONException {
        jsonObject = new JSONObject();
        jsonObject.put("stb",stb);
        jsonObject.put("nama",nama);
        jsonObject.put("angkatan",angkatan);
        return jsonObject;
    }
}
