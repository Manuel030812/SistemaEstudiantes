package com.manuel.datos;

import com.manuel.dominio.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import static com.manuel.conexion.Conexion.getConexion;

public class EstudianteDAO {
    public List<Estudiante> listar(){
        List<Estudiante> estudiantes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql = "select * from estudiante order by id_estudiante";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                var estudiante = new Estudiante();
                estudiante.setIdEstudiante(rs.getInt("id_estudiante"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                estudiantes.add(estudiante);
            }
        }catch (Exception e){
            System.out.println("ocurrio un error al seleccionar los datos: "+e.getMessage());
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("ocurrio un error al cerrar la conexion "+ e.getMessage());
            }
        }
        return estudiantes;
    }

    public boolean buscarEstudiantePidId(Estudiante estudiante){
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql = "select * from estudiante where id_estudiante = ? ";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,estudiante.getIdEstudiante());
            rs = ps.executeQuery();
            if (rs.next()){
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                return true;
            }
        }catch (Exception e){
            System.out.println("ocurrio un error al buscar el estudiante: "+e.getMessage() );
        }
        finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("ocurrio un error al cerrar conexion: "+e.getMessage());
            }
        }
        return false;
    }


    public static void main(String[] args) {
        var estudianteDao = new EstudianteDAO();
        // listar los estudiantes
        System.out.println("listados de estudiantes: ");
        List<Estudiante> estudiantes = estudianteDao.listar();
        estudiantes.forEach(System.out::println);

        //buscar por id
        var estudiante1 = new Estudiante(2);
        System.out.println("estudiante antes de la busqueda");
        var encontrado = estudianteDao.buscarEstudiantePidId(estudiante1);
        if (encontrado){
            System.out.println("estudiante encontrado: "+ estudiante1);
        }else {
            System.out.println("no se encontro el estudiante: " +estudiante1.getIdEstudiante());
        }
    }
}
