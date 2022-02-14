package com.autogenerado.ws;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.autogenerado.DBCon.*;
public class ObjetoService {
	ResultSet resultado;
	public void generaReporteGeneral() {
		Date fechaD = new Date();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat formatoTiempo = new SimpleDateFormat("HHmmss");
		String fechaS = formatoFecha.format(fechaD);
		String tiempo = formatoTiempo.format(fechaD);
		String carpeta = "/home/omar/eclipse-workspace/reporteAutogenerado/archivos/";
		String nombreArchivo = "reporte_ventas_"+fechaS+"_"+tiempo+".txt";
		String fullPath = "";
		DBConnection MsSql = new DBConnection();
		Connection con = MsSql.sqlConnect();
		String query = "select s.transaction_id, s.customer, s.date, s.cant, s.total, p.product_name from sales s, product p where s.product_id = p.product_id";
		try {
			Statement sentencia = con.createStatement();
			resultado = sentencia.executeQuery(query);
			while (resultado.next()) {
				try {
					File archivo = new File(carpeta+nombreArchivo);
					fullPath = carpeta+nombreArchivo;
					FileWriter escribir = new FileWriter(archivo,true);
					escribir.write(resultado.getString("transaction_id"));
					escribir.write("\t");
					escribir.write(resultado.getString("customer"));
					escribir.write("\t");
					escribir.write(resultado.getString("date"));
					escribir.write("\t");
					escribir.write(resultado.getString("cant"));
					escribir.write("\t");
					escribir.write(resultado.getString("total"));
					escribir.write("\r\n");
					escribir.close();
					}catch(Exception e) {
						e.printStackTrace();
					}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			String command = "insert into reporte_ventas values (?,?,?)";
			PreparedStatement pStatement = con.prepareStatement(command);
			pStatement.setString(1, nombreArchivo);
			pStatement.setString(2, fullPath);
			pStatement.setTimestamp(3,new java.sql.Timestamp(fechaD.getTime()));
			pStatement.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			}
		}
	public String getObjeto() {
		
		//String json = gson.toJson(ObjRespProd);
		return "";
		/*return json.replace("null", "\"\"").replace("-1.1", "\"Ilimitado\"").replace("-2.2", "\"Ilimitado\"")
				.replace("-3.3", "\"Ilimitado\"");*/
	}
}
