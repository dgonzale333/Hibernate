package service;

import models.Departamento;
import models.Empleado;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import service.DepartamentoService;
import service.EmpleadoService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Configura e inicializa el SessionFactory
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        // Instancia de servicios para empleados y departamentos
        DepartamentoService departamentoService = new DepartamentoService(sessionFactory);
        EmpleadoService empleadoService = new EmpleadoService(sessionFactory);

        // Crea un departamento
        Departamento departamento = new Departamento();
        departamento.setNombre("Desarrollo");
        departamento.setUbicacion("Sede Principal");

        // Crea una lista de empleados para asociar al departamento
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado("Pedro", LocalDate.of(2020, 5, 20), null)); // Temporariamente sin departamento
        empleados.add(new Empleado("Paula", LocalDate.of(2021, 6, 15), null)); // Temporariamente sin departamento

        // Invoca el método para crear el departamento con sus empleados asociados
        departamentoService.crearDepartamentoConEmpleados(departamento, empleados);

        // Ejecuta una consulta HQL para obtener empleados de un departamento específico
        List<Empleado> empleadosEnDesarrollo = empleadoService.getEmpleadosPorDepartamento(departamento.getNombre());
        System.out.println("Empleados en el departamento de " + departamento.getNombre() + ":");
        for (Empleado emp : empleadosEnDesarrollo) {
            System.out.println(emp.getNombre());
        }

        // Consulta HQL para obtener empleados cuyo nombre empieza con "P"
        List<Empleado> empleadosConP = empleadoService.getEmpleadosPorInicial("P");
        System.out.println("Empleados cuyo nombre empieza con 'P':");
        for (Empleado emp : empleadosConP) {
            System.out.println(emp.getNombre());
        }

        // Cierra el SessionFactory al final
        sessionFactory.close();
    }
}
