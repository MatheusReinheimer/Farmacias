/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author Rodrigo
 */
public class ItemVenda {
    
    private int idItem;

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        int oldIdItem = this.idItem;
        this.idItem = idItem;
        propertyChangeSupport.firePropertyChange("idItem", oldIdItem, idItem);
    }

    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
    
    private double valor;

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        double oldValor = this.valor;
        this.valor = valor;
        propertyChangeSupport.firePropertyChange("valor", oldValor, valor);
    }
    
    private Remedio remedio;

    public Remedio getRemedio() {
        return remedio;
    }

    public void setRemedio(Remedio remedio) {
        Remedio oldRemedio = this.remedio;
        this.remedio = remedio;
        propertyChangeSupport.firePropertyChange("remedio", oldRemedio, remedio);
    }
    
    private int quantidade;

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        int oldQuantidade = this.quantidade;
        if(remedio != null && quantidade != this.quantidade){
            this.setValor(quantidade*this.remedio.getPreco());
        }
        this.quantidade = quantidade;
        propertyChangeSupport.firePropertyChange("quantidade", oldQuantidade, quantidade);
    }

}
