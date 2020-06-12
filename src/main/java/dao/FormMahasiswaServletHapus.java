package dao;

import java.sql.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tipedata.JenisKelamin;
import tipedata.Mahasiswa;
public class FormMahasiswaServletHapus extends HttpServlet{

    private MahasiswaDao mahasiswaDao = new MahasiswaDao();

    public void doGet(){

    }

    public void doPost(HttpServletRequest req , HttpServletResponse res){
        try {
            Mahasiswa m = new Mahasiswa();
            m.setId(req.getParameter("id"));
            mahasiswaDao.hapus(m);
            res.sendRedirect("form.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}