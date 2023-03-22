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
 *
 * @author Xylan
 */
public class CompraDAO {
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
