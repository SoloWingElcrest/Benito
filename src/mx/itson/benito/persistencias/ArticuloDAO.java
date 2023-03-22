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
public class ArticuloDAO {
    public static List<Articulo> obtenerTodos() {
        List<Articulo> articulos = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery<Articulo> criteriaQuery
                    = session.getCriteriaBuilder().createQuery(Articulo.class);
            criteriaQuery.from(Articulo.class);

            articulos = session.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            System.err.println("Ocurrio un error" + ex.getMessage());
        }
        return articulos;
    }

    public static boolean guardar(int idarticulo, String nombre, double precio, Proveedor proveedor) {
        boolean resultado = false;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Articulo c = new Articulo();
            c.setNombre(nombre);
            c.setPrecio(precio);
            c.setProveedor(proveedor);

            session.save(c);

            session.getTransaction().commit();

        } catch (Exception ex) {
            System.err.println("Ocurrio un error" + ex.getMessage());
        }
        return resultado;
    }

    public static Articulo obtenerPorId(int id) {
        Articulo articulo = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            articulo = session.get(Articulo.class, id);
        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return articulo;
    }

    public static boolean editar(int idArticulo, String nombre, double precio, Proveedor proveedor) {
        boolean resultado = false;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            Articulo articulo = obtenerPorId(idArticulo);
            if (articulo != null) {
                articulo.setNombre(nombre);
                articulo.setPrecio(precio);
                articulo.setProveedor(proveedor);
                session.saveOrUpdate(articulo);
                session.getTransaction().commit();

            }
        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return resultado;
    }

    public static boolean eliminar(int id) {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            Articulo articulo = obtenerPorId(id);
            if (articulo != null) {
                session.delete(articulo);
                session.getTransaction().commit();
            }
        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return false;
    }

}


