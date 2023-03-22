/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.benito.persistencias;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.benito.entidades.Articulo;
import mx.itson.benito.entidades.Proveedor;
import mx.itson.benito.utilerias.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Xylan
 */
public class ProveedorDAO {
    public static List<Proveedor> obtenerTodos() {
            List<Proveedor> proveedores = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery<Proveedor> criteriaQuery
                    = session.getCriteriaBuilder().createQuery(Proveedor.class);
            criteriaQuery.from(Proveedor.class);

            proveedores = session.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            System.err.println("Ocurrio un error" + ex.getMessage());
        }
        return proveedores;
    }
    public static boolean guardar(String nombre, String direccion, String telefono, String email, String contacto){
        boolean resultado = false;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Proveedor p = new Proveedor();
            p.setNombre(nombre);
            p.setDireccion(direccion);
            p.setTelefono(telefono);
            p.setEmail(email);
            p.setContacto(contacto);
            
            session.save(p);
            
            session.getTransaction().commit();
            
            resultado = p.getIdproveedor() != 0;
        } catch (Exception ex) {
            System.err.println("Ocurrio un error" + ex.getMessage());
        }
        return resultado;
    }
    public static Proveedor obtenerPorId(int idproveedor){
        Proveedor proveedor = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            proveedor = session.get(Proveedor.class, idproveedor);
        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return proveedor;
    }
    public static boolean editar(int idproveedor, String nombre, String direccion, String telefono, String email, String contacto){
        boolean resultado = false;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Proveedor proveedor = obtenerPorId(idproveedor);
            if(proveedor != null){
                proveedor.setNombre(nombre);
                proveedor.setDireccion(direccion);
                proveedor.setTelefono(telefono);
                proveedor.setEmail(email);
                proveedor.setContacto(contacto);

                
                session.saveOrUpdate(proveedor);              
                session.getTransaction().commit();
                resultado = true;
            }
        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return resultado;
    }
    public static boolean eliminar(int idproveedor){
        boolean resultado = false;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Proveedor proveedor = obtenerPorId(idproveedor);
            
            if(proveedor != null){
                session.delete(proveedor);
                HibernateUtil.getSessionFactory().openSession().delete(proveedor);
                session.getTransaction().commit();
                resultado = true;
            }
        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return resultado;
    }
}
