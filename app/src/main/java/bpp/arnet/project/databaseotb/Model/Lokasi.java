package bpp.arnet.project.databaseotb.Model;

import java.io.Serializable;

public class Lokasi implements Serializable {

    private String nama_lokasi;

    public Lokasi(String nama_lokasi) {
        this.nama_lokasi = nama_lokasi;

    }

    public Lokasi() {


    }

    public String getNama_lokasi() {
        return nama_lokasi;
    }

    public void setNama_lokasi(String nama_lokasi) {
        this.nama_lokasi = nama_lokasi;
    }
}
