/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.benito.persistencias;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.benito.entidades.Compra;
import mx.itson.benito.utilerias.HibernateUtil;
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
}
