package dao;

import java.sql.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tipedata.JenisKelamin;
import tipedata.Mahasiswa;
public class FormMahasiswaServletSimpan extends HttpServlet{

    private MahasiswaDao mahasiswaDao = new MahasiswaDao();

    public void doGet(){

    }

    public void doPost(HttpServletRequest req , HttpServletResponse res){
        try {
            Mahasiswa m = new Mahasiswa();
            m.setNpm(req.getParameter("npm"));
            m.setNama(req.getParameter("nama"));
            m.setTempatLahir(req.getParameter("tempat_lahir"));
            m.setTanggalLahir(Date.valueOf(req.getParameter("tanggal_lahir")));
            m.setJenisKelamin(JenisKelamin.valueOf(req.getParameter("jenis_kelamin").toUpperCase()) );
            m.setAlamat(req.getParameter("alamat"));
            mahasiswaDao.simpan(m);
            res.sendRedirect("form.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}