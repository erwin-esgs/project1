<html>
<%@ page import="dao.*" %>
<%@ page import="tipedata.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="javax.swing.ButtonGroup" %>
<%@ page import="javax.swing.JFrame" %>
<%@ page import="javax.swing.JPanel" %>
<%@ page import="javax.swing.JRadioButton" %>
<%@ page import="javax.swing.JScrollPane" %>
<%@ page import="javax.swing.JTextArea" %>
<%@ page import="javax.swing.JTextField" %>
<%@ page import="java.awt.*" %>

<link rel="stylesheet" href="bs/css/bootstrap.min.css" >

<div style="position:relative; float:left;">
    <table>
		<form method ="post" action="simpan">
            <tr>
                <td><label for="">NPM: </label></td>
                <td><input type="text" class="form-control" name="npm"></td>
            </tr>
            <tr>
                <td><label for="">Nama: </label></td>
                <td><input type="text" class="form-control" name="nama"></td>
            </tr>
            <tr>
                <td><label for="">Tempat Lahir: </label></td>
                <td><input type="text" class="form-control" name="tempat_lahir"></td>
            </tr>
            <tr>
                <td><label for="">Tanggal Lahir: </label></td>
                <td><input type="text" class="form-control" name="tanggal_lahir"></td>
            </tr>
            <tr>
                <td><label for="">Tanggal Lahir: </label></td>
                <td>
                    <input type="radio"  name="jenis_kelamin" value="Pria" checked="checked">Pria
                    <input type="radio"  name="jenis_kelamin" value="Wanita">Wanita
                </td>
            </tr>
            <tr>
                <td><label for="">Alamat: </label></td>
                <td><textarea class="form-control" name="alamat" rows="5" cols="20" style="resize: none;"></textarea></td>
            </tr>
            <tr>
                <td><input  class="btn btn-primary" type="reset" value="RESET"></td>
                <td><input  class="btn btn-primary" type="submit" value="SIMPAN"></td>
            </tr>
		</form>
		<form method ="post" action="hapus">
			<tr>
				<td>    <label for="">Click id on table </label><br>
                        <input type="text" name="id" id="idvalue" readonly/> </td>
				<td> <input  class="btn btn-primary" type="submit" value="HAPUS"/> </td>
			</tr>
		</form>
    </table>
</div>


<div style="position:relative; float:right;">
<table class="table" >
<tr>
    <th scope="col">ID</th>
    <th scope="col">NPM</th>
    <th scope="col">Nama</th>
    <th scope="col">Tempat Lahir</th>
    <th scope="col">Tanggal Lahir</th>
    <th scope="col">Jenis Kelamin</th>
    <th scope="col">Alamat</th>             
</tr>

<%
    MahasiswaDao mahasiswaDao = new MahasiswaDao();
    ArrayList<Mahasiswa> mList = mahasiswaDao.tampil();
    for (int i=0; i < mList.size();i++ ) {
%>  
    <tr>
        <td onclick="getidvalue('<%=mList.get(i).getId()%>')"> <%=mList.get(i).getId()%></td>
        <td><%=mList.get(i).getNpm()%></td>
        <td><%=mList.get(i).getNama()%></td>
        <td><%=mList.get(i).getTempatLahir()%></td>
        <td><%=mList.get(i).getTanggalLahir().toString()%></td>
        <td><%=mList.get(i).getJenisKelamin().toString()%></td>
        <td><%=mList.get(i).getAlamat()%></td>             
    </tr>
<%
    }
%>
</table>
</div>

</html>
<script>
function getidvalue(id) {
    document.getElementById("idvalue").value = id.toString();
}
</script>
