package com.manuel.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static Connection getConexion(){
        Connection conexion = null;
        var database = "estudiantes_db";
        var url = "jdbc:mysql://localhost:3306/"+ database;
        var usuario = "root";
        var password = "admin";
        //cargamos la clases del diver de mysql en memoria
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, password);
        }catch (ClassNotFoundException| SQLException e){
            System.out.println("ocurrio un error en la conexion" + e.getMessage());
        }
        return  conexion;
    }

    public static void main(String[] args) {
        var conexion = Conexion.getConexion();
        if (conexion != null){
            System.out.println("conexion exitosa "+conexion);
        }else {
            System.out.println("error al conectarse");
        }
    }
}
