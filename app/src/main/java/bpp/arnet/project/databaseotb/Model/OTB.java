package bpp.arnet.project.databaseotb.Model;

import java.io.Serializable;

public class OTB {

    private String id;
    private String nama;
    private String tipe;
    private String arah;
    private String rak;
    private String kapasitas;
    private String data_port;
    private String foto;
    private String nama_lokasi;

    public OTB(){

    }

    public OTB(String id, String nama, String tipe, String arah, String rak, String kapasitas, String data_port, String foto, String nama_lokasi) {
        this.id = id;
        this.nama = nama;
        this.tipe = tipe;
        this.arah = arah;
        this.rak = rak;
        this.kapasitas = kapasitas;
        this.data_port = data_port;
        this.foto = foto;
        this.nama_lokasi = nama_lokasi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getArah() {
        return arah;
    }

    public void setArah(String arah) {
        this.arah = arah;
    }

    public String getRak() {
        return rak;
    }

    public void setRak(String rak) {
        this.rak = rak;
    }

    public String getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(String kapasitas) {
        this.kapasitas = kapasitas;
    }

    public String getData_port() {
        return data_port;
    }

    public void setData_port(String data_port) {
        this.data_port = data_port;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNama_lokasi() {
        return nama_lokasi;
    }

    public void setNama_lokasi(String nama_lokasi) {
        this.nama_lokasi = nama_lokasi;
    }

    //    public OTB(String nama, String tipe, String arah, String rak, String kapasitas, String data_port, String foto, String nama_lokasi) {
//        this.nama = nama;
//        this.tipe = tipe;
//        this.arah = arah;
//        this.rak = rak;
//        this.kapasitas = kapasitas;
//        this.data_port = data_port;
//        this.foto = foto;
//        this.nama_lokasi = nama_lokasi;
//    }
//
//    public String getTipe() {
//        return tipe;
//    }
//
//    public void setTipe(String tipe) {
//        this.tipe = tipe;
//    }
//
//    public String getArah() {
//        return arah;
//    }
//
//    public void setArah(String arah) {
//        this.arah = arah;
//    }
//
//    public String getRak() {
//        return rak;
//    }
//
//    public void setRak(String rak) {
//        this.rak = rak;
//    }
//
//    public String getKapasitas() {
//        return kapasitas;
//    }
//
//    public void setKapasitas(String kapasitas) {
//        this.kapasitas = kapasitas;
//    }
//
//    public String getData_port() {
//        return data_port;
//    }
//
//    public void setData_port(String data_port) {
//        this.data_port = data_port;
//    }
//
//    public String getFoto() {
//        return foto;
//    }
//
//    public void setFoto(String foto) {
//        this.foto = foto;
//    }
//
//    public String getNama_lokasi() {
//        return nama_lokasi;
//    }
//
//    public void setNama_lokasi(String nama_lokasi) {
//        this.nama_lokasi = nama_lokasi;
//    }
//
//
//
//    public String getNama() {
//        return nama;
//    }
//
//    public void setNama(String nama) {
//        this.nama = nama;
//    }

//    public OTB(String nama) {
//        this.nama = nama;
//    }




}
