
package models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Remedio {
        
    private String descricao;

    public static final String PROP_DESCRICAO = "descricao";

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        String oldDescricao = this.descricao;
        this.descricao = descricao;
        propertyChangeSupport.firePropertyChange(PROP_DESCRICAO, oldDescricao, descricao);
    }

    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
    
    private String bula;

    public static final String PROP_BULA = "bula";

    public String getBula() {
        return bula;
    }

    public void setBula(String bula) {
        String oldBula = this.bula;
        this.bula = bula;
        propertyChangeSupport.firePropertyChange(PROP_BULA, oldBula, bula);
    }
    
    private int id;

    public static final String PROP_ID = "id";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        int oldId = this.id;
        this.id = id;
        propertyChangeSupport.firePropertyChange(PROP_ID, oldId, id);
    }
    
    private double preco;

    public static final String PROP_PRECO = "preco";

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        double oldPreco = this.preco;
        this.preco = preco;
        propertyChangeSupport.firePropertyChange(PROP_PRECO, oldPreco, preco);
    }
    
    private int estoque;

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        int oldEstoque = this.estoque;
        this.estoque = estoque;
        propertyChangeSupport.firePropertyChange("estoque", oldEstoque, estoque);
    }

}
