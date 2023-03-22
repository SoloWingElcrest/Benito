/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.benito.entidades;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Xylan
 */
@Entity
public class Relacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idCompra")
    private Compra idCompra;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "relacion",
            joinColumns = {
                @JoinColumn(name = "idCompra")},
            inverseJoinColumns = {
                @JoinColumn(name = "idArticulo")})
    private List<Articulo> idArticulo;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the idCompra
     */
    public Compra getIdCompra() {
        return idCompra;
    }

    /**
     * @param idCompra the idCompra to set
     */
    public void setIdCompra(Compra idCompra) {
        this.idCompra = idCompra;
    }

    /**
     * @return the idArticulo
     */
    public List<Articulo> getIdArticulo() {
        return idArticulo;
    }

    /**
     * @param idArticulo the idArticulo to set
     */
    public void setIdArticulo(List<Articulo> idArticulo) {
        this.idArticulo = idArticulo;
    }
    
}
