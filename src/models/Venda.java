/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public class Venda {

    public Venda() {
        this.itens = new LinkedList<>();
        this.itens = org.jdesktop.observablecollections.ObservableCollections.observableList(itens);
    }
    
    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
    
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        int oldId = this.id;
        this.id = id;
        propertyChangeSupport.firePropertyChange("id", oldId, id);
    }

    private Date dataVenda;

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date data) {
        Date oldId = this.dataVenda;
        this.dataVenda = data;
        propertyChangeSupport.firePropertyChange("dataVenda", oldId, id);
    }
    
    private double total;

    public double getTotal() {
        total = 0;
        for(ItemVenda item : itens){
            total += item.getValor();
        }
        return total;
    }
    
    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        Cliente oldCliente = this.cliente;
        this.cliente = cliente;
        propertyChangeSupport.firePropertyChange("cliente", oldCliente, cliente);
    }
    
    private List<ItemVenda> itens;

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void addItem(ItemVenda item) {
        List<ItemVenda> oldItens = this.itens;
        double t = this.total;
        itens.add(item);
        propertyChangeSupport.firePropertyChange("itens", oldItens, itens);
        propertyChangeSupport.firePropertyChange("total", t, this.getTotal());
    }
}
