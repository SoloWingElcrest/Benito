/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.benito.persistencias;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.benito.entidades.Proveedor;
import mx.itson.benito.utilerias.HibernateUtil;
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
}
