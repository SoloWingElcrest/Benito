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
 *Clase de persistencia para la obtencion de datos de proveedores de la base de datos
 * @author Xylan
 */
public class ProveedorDAO {
    /**
     * Genera una lista de proveedores para su despliegue en la interfaz
     * @return la lista proveedores
     */
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
    /**
     * Funcion guardar que permite almacenar la informacion de datos 
     * @param nombre nombre del proveedor
     * @param direccion direccion de la ubicacion del proveedor
     * @param telefono telefono de contacto del proveedor
     * @param email e-mail de contacto del proveedor
     * @param contacto nombre de contacto del proveedor
     * @return el resultado de la funcion guardar
     */
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
    /**
     * Obtiene los datos ordenados por medio del id
     * @param idproveedor identificador del proveedor
     * @return las proveedores obtenidos
     */
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
    /**
     * Funcion para editar una lista de datos ya almacenada en la base de datos
     * @param idproveedor identificador del proveedor
     * @param nombre nombre del proveedor
     * @param direccion direccion de la ubicacion del proveedor
     * @param telefono telefono de contacto del proveedor
     * @param email e-mail de contacto del proveedor
     * @param contacto nombre de contacto del proveedor
     * @return el resultado de la funcion de edicion 
     */
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
    /**
     * Funcion que elimina una serie de datos de proveedores de la base de datos
     * @param idproveedor identificador de proveedor
     * @return el resultado de la funcion de eliminacion de datos
     */
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
