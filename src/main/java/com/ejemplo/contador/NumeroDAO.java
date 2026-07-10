package com.ejemplo.contador;

import java.sql.*;

public class NumeroDAO {

    public void guardarNumero(Numero numero) throws Exception {

        Connection con = Conexion.conectar();

        PreparedStatement ps = con.prepareStatement(
                "INSERT INTO numeros(numero) VALUES(?)");

        ps.setInt(1, numero.getNumero());

        ps.executeUpdate();

        ps.close();
        con.close();

    }

    public void listar() throws Exception {

        Connection con = Conexion.conectar();

        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery(
                "SELECT * FROM numeros");

        System.out.println();

        System.out.println("NUMEROS GUARDADOS");

        while (rs.next()) {

            System.out.println(
                    rs.getInt("id")
                            + " -> "
                            + rs.getInt("numero"));

        }

        rs.close();
        st.close();
        con.close();
    }
}