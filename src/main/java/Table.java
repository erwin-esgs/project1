
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.*;
import java.sql.Date;

import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import tipedata.*;
import dao.*;

public class Table extends JFrame{
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JTable jTable1;

    javax.swing.JLabel jLabel0 = new javax.swing.JLabel("(Klik cell id table)");
    javax.swing.JLabel jLabel1 = new javax.swing.JLabel("NPM");
    javax.swing.JLabel jLabel2 = new javax.swing.JLabel("Nama");
    javax.swing.JLabel jLabel3 = new javax.swing.JLabel("Tempat Lahir");
    javax.swing.JLabel jLabel4 = new javax.swing.JLabel("Tanggal Lahir");
    javax.swing.JLabel jLabel5 = new javax.swing.JLabel("Jenis Kelamin");
    javax.swing.JLabel jLabel6 = new javax.swing.JLabel("Alamat");
    JTextField id = new JTextField(20); 
    JTextField npm = new JTextField(20);
    JTextField nama = new JTextField(20);
    JTextField tempatlahir = new JTextField(20);
    ButtonGroup buttonGroup = new ButtonGroup();
    JRadioButton pria = new JRadioButton("Pria");
    JRadioButton wanita = new JRadioButton("Wanita");
    JDateChooser tanggallahir = new JDateChooser();
    JTextArea alamat = new JTextArea (5, 20);

     
    public Table(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pria.setActionCommand("PRIA");
        pria.setSelected(true);
        wanita.setActionCommand("PRIA");
        
        buttonGroup.add(pria);
        buttonGroup.add(wanita);


        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTable1 = new javax.swing.JTable();

        jButton1.setText("add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("delete");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        
        DefaultTableModel dtm = new DefaultTableModel(0, 0);
        String header[] = new String[] { "ID","NPM", "NAMA","Tempat Lahir","Tanggal Lahir", "Jenis Kelamin","Alamat" };
        dtm.setColumnIdentifiers(header);
        jTable1.setModel(dtm);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0;  
        gbc.weighty = 0;  
        gbc.gridx = 0;
        gbc.gridy = 0;

        panel.add(jLabel0, gbc);gbc.gridx++;
        id.setEditable(false);
        panel.add(id, gbc);
        gbc.gridy++;gbc.gridx--;

        panel.add(jLabel1, gbc);gbc.gridx++;
        panel.add(npm, gbc);
        gbc.gridy++;gbc.gridx--;

        panel.add(jLabel2, gbc);gbc.gridx++;
        panel.add(nama, gbc);
        gbc.gridy++;gbc.gridx--;

        panel.add(jLabel3, gbc);gbc.gridx++;
        panel.add(tempatlahir, gbc);
        gbc.gridy++;gbc.gridx--;

        panel.add(jLabel4, gbc);gbc.gridx++;
        panel.add(tanggallahir,gbc);
        gbc.gridy++;gbc.gridx--;

        panel.add(jLabel5, gbc);
        gbc.gridy++;
        panel.add(pria, gbc);gbc.gridx++;
        panel.add(wanita, gbc);
        gbc.gridy++;gbc.gridx--;

        panel.add(jLabel6, gbc);gbc.gridx++;
        panel.add(alamat, gbc);
        gbc.gridy++;gbc.gridx--;

        panel.add(jButton1 , gbc);gbc.gridx++;
        panel.add(jButton2 , gbc);

        displayTable();

        this.add(panel,BorderLayout.WEST);
        this.add(new JScrollPane(jTable1),BorderLayout.EAST);
        this.setTitle("Table Example");    
        this.pack();
        this.setVisible(true);

        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = jTable1.rowAtPoint(evt.getPoint());
                int col = jTable1.columnAtPoint(evt.getPoint());
                String idval = (String)jTable1.getModel().getValueAt(row, col);
                id.setText(idval); 
            }
        });

        pack();
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        MahasiswaDao mahasiswaDao = new MahasiswaDao();
        Mahasiswa m = new Mahasiswa();
        m.setNpm(npm.getText());
        m.setNama(nama.getText());
        m.setTempatLahir(tempatlahir.getText());
        Date sqldate = new java.sql.Date(tanggallahir.getDate().getTime());
        m.setTanggalLahir(sqldate);
        m.setJenisKelamin(JenisKelamin.valueOf(buttonGroup.getSelection().getActionCommand().toUpperCase()) );
        m.setAlamat(alamat.getText());
        mahasiswaDao.simpan(m);

        displayTable();
        
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        MahasiswaDao mahasiswaDao = new MahasiswaDao();
        Mahasiswa m = new Mahasiswa();
        m.setId(id.getText());
        mahasiswaDao.hapus(m);

        displayTable();
        
    }

    private void displayTable(){
        MahasiswaDao mahasiswaDao = new MahasiswaDao();
        ArrayList<Mahasiswa> mList = mahasiswaDao.tampil();
        DefaultTableModel tm = (DefaultTableModel)jTable1.getModel();
        tm.setRowCount(0);
        //System.out.println(Arrays.toString(mList.toArray()));
        for (int i=0; i < mList.size();i++ ) {
            Object o[]= {   mList.get(i).getId(),
                            mList.get(i).getNpm(), 
                            mList.get(i).getNama(), 
                            mList.get(i).getTempatLahir(),
                            mList.get(i).getTanggalLahir().toString(),
                            mList.get(i).getJenisKelamin().toString(),
                            mList.get(i).getAlamat()
                        } ;
        tm.addRow(o);
        }
    }

    

    public static void main (String[] args) {
        
        new Table().setVisible(true);
    }
}