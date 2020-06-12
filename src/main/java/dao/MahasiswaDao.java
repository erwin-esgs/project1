package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import tipedata.*;

public class MahasiswaDao {
    //private String timeStamp = new SimpleDateFormat("yyMMddHHmmss").format(new Date());

    private Connection koneksiDatabase;
    private String dbDriver = "com.mysql.cj.jdbc.Driver";
    private String dbName = "mahasiswa";
    private String dbUrl = "jdbc:mysql://localhost/"+dbName+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String dbUsername = "root";
    private String dbPassword = "";

    public void connect() {
        try {
            Class.forName(dbDriver);
            koneksiDatabase = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            if(koneksiDatabase !=null){
                koneksiDatabase.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void simpan(Mahasiswa m){
        try {
            connect();
            String timeStamp = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
            String sql = "INSERT INTO mahasiswa (id, npm , nama , tempat_lahir , tanggal_lahir , jenis_kelamin , alamat)  VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = koneksiDatabase.prepareStatement(sql);
            ps.setString(1, timeStamp);
            ps.setString(2, m.getNpm());
            ps.setString(3, m.getNama());
            ps.setString(4, m.getTempatLahir());
            ps.setDate(5, m.getTanggalLahir());
            ps.setString(6, m.getJenisKelamin().toString());
            ps.setString(7, m.getAlamat());
            ps.executeUpdate();

            disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hapus(Mahasiswa m){
        try {
            connect();

            String sql = "DELETE FROM mahasiswa WHERE id = ?";
            PreparedStatement ps = koneksiDatabase.prepareStatement(sql);
            ps.setString(1, m.getId());
            ps.executeUpdate();

            disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Mahasiswa> tampil(){
        ArrayList<Mahasiswa> mList = new ArrayList<>();
        //Mahasiswa mList[] = {};
        try {
            connect();
            
            String sql = "SELECT * FROM mahasiswa";
            PreparedStatement ps = koneksiDatabase.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mahasiswa m = new Mahasiswa();
                m.setId(rs.getString("id"));
                m.setNpm(rs.getString("npm"));
                m.setNama(rs.getString("nama"));
                m.setTempatLahir(rs.getString("tempat_lahir"));
                m.setTanggalLahir(rs.getDate("tanggal_lahir"));
                String jk = rs.getString("jenis_kelamin");
                m.setJenisKelamin(JenisKelamin.valueOf(jk));
                m.setAlamat(rs.getString("alamat"));
                mList.add(m);
            }

            disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mList;
    }
}