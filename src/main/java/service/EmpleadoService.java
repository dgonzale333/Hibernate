package service;

import models.Empleado;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class EmpleadoService {

    private SessionFactory sessionFactory;

    public EmpleadoService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Método para obtener empleados de un departamento específico por nombre
    public List<Empleado> getEmpleadosPorDepartamento(String nombreDepartamento) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Empleado e WHERE e.departamento.nombre = :nombreDepartamento";
            Query<Empleado> query = session.createQuery(hql, Empleado.class);
            query.setParameter("nombreDepartamento", nombreDepartamento);
            return query.list();
        }
    }

    // Método para obtener empleados cuyo nombre empieza con una letra específica
    public List<Empleado> getEmpleadosPorInicial(String inicial) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Empleado e WHERE e.nombre LIKE :inicial";
            Query<Empleado> query = session.createQuery(hql, Empleado.class);
            query.setParameter("inicial", inicial + "%");
            return query.list();
        }
    }

    // Método para guardar o actualizar un empleado
    public void guardarEmpleado(Empleado empleado) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(empleado);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
