/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.benito.persistencias;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.benito.entidades.Relacion;
import mx.itson.benito.utilerias.HibernateUtil;
import org.hibernate.Session;

/**
 * Clase de apoyo que relaciona las clases proveedor y articulo
 * @author Xylan
 */
public class RelacionDAO {
        /**
         * Genera la lista que relaciona las clases
         * @return la lista de relaciones
         */
        public static List<Relacion> obtenerTodos(){
        List<Relacion> relacion = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery<Relacion> criteriaQuery = 
                    session.getCriteriaBuilder().createQuery(Relacion.class);
            criteriaQuery.from(Relacion.class);
            
            relacion = session.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return relacion;
}
}

