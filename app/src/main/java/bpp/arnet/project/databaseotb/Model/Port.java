package bpp.arnet.project.databaseotb.Model;

public class Port {

    String id_port;
    String core;
    String user;
    String direction;
    String keterangan;
    String nama;

    public Port(){


    }

    public String getId_port() {
        return id_port;
    }

    public void setId_port(String id_port) {
        this.id_port = id_port;
    }

    public String getCore() {
        return core;
    }

    public void setCore(String core) {
        this.core = core;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
