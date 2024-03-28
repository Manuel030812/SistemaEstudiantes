package com.manuel.datos;

import com.manuel.dominio.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static com.manuel.conexion.Conexion.getConexion;

public class EstudianteDAO {
    // hacer consulta de todos los estudiantes
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

    // buscar estudiante por id
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

    //creacion del metodo de agregar un estudiante a la base de datos
    public boolean agregarEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql = "insert into estudiante(nombre,apellido,telefono,email) values (?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,estudiante.getNombre());
            ps.setString(2,estudiante.getApellido());
            ps.setString(3,estudiante.getTelefono());
            ps.setString(4,estudiante.getEmail());
            ps.execute();
            return  true;
        }catch (Exception e){
            System.out.println("error al ingresar el estudiante: "+ e.getMessage());
        }finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("error al cerrar la conexion "+ e.getMessage());
            }
        }

        return false;

    }
    //modificar estudiante
    public boolean modificarEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "update estudiante set nombre=?,apellido=?,telefono=?,email=? where id_estudiante = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,estudiante.getNombre());
            ps.setString(2,estudiante.getApellido());
            ps.setString(3,estudiante.getTelefono());
            ps.setString(4,estudiante.getEmail());
            ps.setInt(5,estudiante.getIdEstudiante());
            ps.execute();
            return  true;
        }catch (Exception e){
            System.out.println("error al modificar el estudiante: "+e.getMessage());
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("error al cerrar la conexion "+ e.getMessage());
            }
        }
        return false;

    }
    //eliminar estudiante
    public boolean eliminarEstudiente(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "delete from estudiante where id_estudiante = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,estudiante.getIdEstudiante());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("error al eliminar estudiante: "+e.getMessage());
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("error al cerrar la conexion: "+e.getMessage());
            }
        }
        return false;
    }




    public static void main(String[] args) {
        var estudianteDao = new EstudianteDAO();

        var EliminarEstudiante = new Estudiante(11);
        var eliminado = estudianteDao.eliminarEstudiente(EliminarEstudiante);
        if (eliminado){
            System.out.println("estudiante eliminado: "+EliminarEstudiante);
        }else {
            System.out.println("estudiante no se elimino"+ EliminarEstudiante);
        }
        // modificar estudiande existente
       /* var estudianteModificar = new Estudiante(1,"juan carlos","juarez","34345","juan@mail.com");
        var modificado = estudianteDao.modificarEstudiante(estudianteModificar);
        if (modificado){
            System.out.println("estudiante modificado: "+estudianteModificar);
        }else {
            System.out.println("estudiane no se modifico" + estudianteModificar);
        }*/
        //agregar estudiante
        /*var nuevoEstudiante = new Estudiante("carlos","lara","495995","carlos@gmail.com");
        var agregado = estudianteDao.agregarEstudiante(nuevoEstudiante);
        if (agregado){
            System.out.println("estudiante agregado: "+ nuevoEstudiante);
        }else {
            System.out.println("no se agrego el estudiante: "+ nuevoEstudiante);
        }*/

        // listar los estudiantes
        System.out.println("listados de estudiantes: ");
        List<Estudiante> estudiantes = estudianteDao.listar();
        estudiantes.forEach(System.out::println);

        //buscar por id
        /*var estudiante1 = new Estudiante(2);
        System.out.println("estudiante antes de la busqueda");
        var encontrado = estudianteDao.buscarEstudiantePidId(estudiante1);
        if (encontrado){
            System.out.println("estudiante encontrado: "+ estudiante1);
        }else {
            System.out.println("no se encontro el estudiante: " +estudiante1.getIdEstudiante());
        }*/


    }
}
