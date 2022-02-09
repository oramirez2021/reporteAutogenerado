package com.autogenerado.ws;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.autogenerado.DBCon.*;
public class ObjetoService {
	ResultSet resultado;
	public void generaReporteGeneral() {
		DBConnection MsSql = new DBConnection();
		Connection con = MsSql.sqlConnect();
		String query = "select * from sales";
		try {
			Statement sentencia = con.createStatement();
			resultado = sentencia.executeQuery(query);
			while (resultado.next()) {
				try {
					File archivo = new File("reporteGeneral.txt");
					FileWriter escribir = new FileWriter(archivo,true);
					escribir.write(resultado.getInt("transaction_id"));
					escribir.write(resultado.getString("customer"));
					escribir.close();
					}catch(Exception e) {
						e.printStackTrace();
					}
			}
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
