package com.ejemplo.contador;

import java.sql.*;

public class ResumenDAO {

    public void guardar(Resumen r) throws Exception {

        Connection con = Conexion.conectar();

        PreparedStatement ps = con.prepareStatement(

                "INSERT INTO resumen(cantidad,suma,promedio,mayor,menor) VALUES(?,?,?,?,?)"

        );

        ps.setInt(1, r.getCantidad());

        ps.setInt(2, r.getSuma());

        ps.setDouble(3, r.getPromedio());

        ps.setInt(4, r.getMayor());

        ps.setInt(5, r.getMenor());

        ps.executeUpdate();

        ps.close();
        con.close();

    }

    public void mostrar() throws Exception {

        Connection con = Conexion.conectar();

        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery("SELECT * FROM resumen");

        System.out.println();

        System.out.println("RESUMEN");

        while (rs.next()) {

            System.out.println("Cantidad : " + rs.getInt("cantidad"));
            System.out.println("Suma : " + rs.getInt("suma"));
            System.out.println("Promedio : " + rs.getDouble("promedio"));
            System.out.println("Mayor : " + rs.getInt("mayor"));
            System.out.println("Menor : " + rs.getInt("menor"));
            System.out.println("----------------------");

        }

        rs.close();
        st.close();
        con.close();    
    }
}