package com.manuel.presentacion;

import com.manuel.datos.EstudianteDAO;
import com.manuel.dominio.Estudiante;

import java.util.Scanner;

public class Presentacion {
    public static void main(String[] args) {
        var salir = false;
        var consola = new Scanner(System.in);
        //agregamos el serviso
        var estudianteDAO = new EstudianteDAO();
        while (!salir){
            try {
                mostrarMenu();
                salir = ejecutarOpciones(consola,estudianteDAO);
            }catch (Exception e){
                System.out.println("ocurrio un error: "+ e.getMessage());
            }
            System.out.println();
        }//fin while
    }
    private  static  void  mostrarMenu(){
        System.out.print("""
                ***control de estudiantes ***
                1. agregar estudiante
                2. listar estudiantes
                3. buscar estudiante
                4. modificar estudiante
                5. eliminar estudiante
                6. salir
                elige una opcion:  """);


    }

    private  static boolean ejecutarOpciones(Scanner consola,EstudianteDAO estudianteDAO){
        var opcion = Integer.parseInt(consola.nextLine());
        var salir= false;
        switch (opcion){
            case 1->{
                System.out.println("estamos en agregar estudiante");
                System.out.println("introduce el nombre del estudiante: ");
                var nombreEestudiante = consola.nextLine();
                System.out.println("introdu el apellido del estudiante: ");
                var apellido = consola.nextLine();
                System.out.println("introduce el telefono del estudiante: ");
                var telefono = consola.nextLine();
                System.out.println("introduce el email del estudiante: ");
                var email = consola.nextLine();
                //crear el estudiante
                var estudiante = new Estudiante(nombreEestudiante,apellido,telefono,email);
                var agregado = estudianteDAO.agregarEstudiante(estudiante);
                if (agregado){
                    System.out.println(" estudiante agregado: "+ estudiante);
                }else {
                    System.out.println("estudiante no se agrego: "+estudiante);
                }
            }
            case 2 -> {
                System.out.println("listar estudiantes");
                var estudiantes = estudianteDAO.listar();
                estudiantes.forEach(System.out::println);
            }
            case 3->{
                System.out.println("introduce el id del estudiante a buscar");
                var idestudiante = Integer.parseInt(consola.nextLine());
                var estudiante = new Estudiante(idestudiante);
                var encontrado = estudianteDAO.buscarEstudiantePidId(estudiante);
                if (encontrado) {
                    System.out.println("estudiante encontrado: " + estudiante);
                }else {
                    System.out.println("estudiante no encontrado "+estudiante);
                }

            }
            case 4 -> {
                System.out.println(" estamos en modificar estudiante");
                System.out.println("dame el id del estudiante a modificar");
                var idestudiante= Integer.parseInt(consola.nextLine());
                System.out.println("introduce el nuevo nombre del estudiante: ");
                var nombreEestudiante = consola.nextLine();
                System.out.println("introdu el nuevo apellido del estudiante: ");
                var apellido = consola.nextLine();
                System.out.println("introduce el nuevo telefono del estudiante: ");
                var telefono = consola.nextLine();
                System.out.println("introduce el nuevo email del estudiante: ");
                var email = consola.nextLine();
                //crear el objeto a estudiante a modificar
                var estudiante = new Estudiante(idestudiante,nombreEestudiante,apellido,telefono,email);
                var modificado = estudianteDAO.modificarEstudiante(estudiante);
                if (modificado){
                    System.out.println(" estudiante modificado: "+ estudiante);
                }else {
                    System.out.println("estudiante no se modificado: "+estudiante);
                }

            }
            case 5 -> {
                System.out.println("eliminar estudiante");
                System.out.println("dame el id del estudiante a eliminar: ");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                var estudiante = new Estudiante(idEstudiante);
                var elimindao = estudianteDAO.eliminarEstudiente(estudiante);
                if (elimindao){
                    System.out.println("estudiante eliminado: "+estudiante);
                }else{
                    System.out.println("estudiante no eliminado: "+estudiante);
                }

            }
            case 6 ->{
                System.out.println("hasta pronto");
                salir = true;
            }
            default -> System.out.println("opcion no reconocida: "+ opcion);
        }
        return salir;
    }
}

