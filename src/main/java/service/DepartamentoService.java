package service;

import models.Departamento;
import models.Empleado;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class DepartamentoService {

    private SessionFactory sessionFactory;

    public DepartamentoService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Método para crear un departamento y varios empleados asociados
    public void crearDepartamentoConEmpleados(Departamento departamento, List<Empleado> empleados) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            // Guarda el departamento
            session.save(departamento);

            // Asocia cada empleado al departamento y los guarda
            for (Empleado empleado : empleados) {
                empleado.setDepartamento(departamento); // Asocia el empleado con el departamento
                session.save(empleado); // Guarda el empleado
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error al crear departamento o empleados: " + e.getMessage());
        }
    }
}
