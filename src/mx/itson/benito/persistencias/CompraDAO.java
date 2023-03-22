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
import mx.itson.benito.entidades.Compra;
import mx.itson.benito.entidades.Proveedor;
import mx.itson.benito.utilerias.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * Clase de persistencia para la obtencion de datos de compra de la base de datos
 * @author Xylan
 */

public class CompraDAO {
    /**
     * Genera una lista de compras para su despliegue en la interfaz
     * @return la lista de compra
     */
public static List<Compra> obtenerTodos() {
            List<Compra> compras = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery<Compra> criteriaQuery
                    = session.getCriteriaBuilder().createQuery(Compra.class);
            criteriaQuery.from(Compra.class);

            compras = session.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            System.err.println("Ocurrio un error" + ex.getMessage());
        }
        return compras;
    }
        /**
         * Funcion guardar que permite almacenar la informacion de datos 
         * @param idProveedor identificador del proveedor
         * @param folio folio de compra
         * @param idArticulo identificador del articulo
         * @param cantidad cantidad de articulos a comprar
         * @return el resultado de la funcion guardar
         */
    public static boolean guardar(Proveedor idProveedor, String folio, Articulo idArticulo, int cantidad) {
        boolean resultado = false;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Compra com = new Compra();
            com.setProveedor(idProveedor);
            com.setFolio(folio);
            com.setArticulo(idArticulo);
            com.setCantidad(cantidad);

            session.save(com);

            session.getTransaction().commit();

            resultado = com.getIdcompra() != 0;
        } catch (Exception ex) {
            System.err.println("Ocurrio un error" + ex.getMessage());
        }
        return resultado;
    }
/**
 * Obtiene los datos ordenados por medio del id
 * @param idcompra identificador de compra
 * @return las compras obtenidas
 */
    public static Compra obtenerPorId(int idcompra) {
        Compra Compra = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Compra = session.get(Compra.class, idcompra);
        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return Compra;
    }
    /**
     * Funcion para editar una lista de datos ya almacenada en la base de datos
     * @param idcompra identficador de la compra
     * @param idProveedor identificador del proveedor
     * @param folio folio de la compra
     * @param idArticulo identificador del articulo
     * @param cantidad cantidad adquirida en la compra
     * @return el resultado de la funcion de edicion
     */
    public static boolean editar(int idcompra, Proveedor idProveedor, String folio, Articulo idArticulo, int cantidad){
        boolean resultado = false;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Compra compra = obtenerPorId(idcompra);
            if(compra != null){
                compra.setProveedor(idProveedor);
                compra.setFolio(folio);
                compra.setArticulo(idArticulo);
                compra.setCantidad(cantidad);
                
                session.saveOrUpdate(compra);              
                session.getTransaction().commit();
                resultado = true;
            }
        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return resultado;
    }
    /**
     * Funcion que elimina una serie de datos de compra de la base de datos
     * @param idcompra identificador de la compra
     * @return el resultado de la funcion de eliminacion de datos
     */
    public static boolean eliminar(int idcompra){
        boolean resultado = false;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Compra compra = obtenerPorId(idcompra);
            compra.getIdcompra();
            
            if(compra != null){
                session.delete(compra);
                HibernateUtil.getSessionFactory().openSession().delete(compra);
                session.getTransaction().commit();
                resultado = true;
            }
        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return resultado;
    }   
}
