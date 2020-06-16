
package reparto.model;

public class Producto {
    private int id;
    private int codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    public int cantidad;
    public double subtotal;

    public Producto() {
        this.id=-1;
    }
    
    public Producto(int codigo, String nombre, String descripcion, double precio) {
        
        this.id = -1;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad=1;
        this.subtotal=precio;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
        public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        this.subtotal = this.precio * this.cantidad;
        return subtotal;
    }
    @Override
    public String toString() {
        return "PRODUCTO{" + "ID=" + id + ", CODIGO=" + codigo + ", NOMBRE=" + nombre + ", DESCRIPCION=" + descripcion + ", PRECIO=" + precio + ", CANTIDAD=" + cantidad +'}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Producto other = (Producto) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
    
}
