
package reparto.model;


public class Repartidor extends Persona {
    
    private String turno;
    
    public Repartidor(){
        this.id=-1;
    }

    public Repartidor(String turno) {
        this.id=-1;
        this.turno = turno;
        
    }
    
    public Repartidor(String nombre, String turno){
        this.id=-1;
        this.nombre=nombre;
        this.turno=turno;
    }
    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }


    @Override
    public String toString() {
        return super.toString()+"REPARTIDOR{" + "TURNO=" + turno + '}';
    }

    @Override
    public boolean equals(Object o) {
        boolean result=false;
        if(o!=null){
            if(this==o){
                result=true;
            }else if(o instanceof Repartidor){
                Repartidor aux=(Repartidor)o;    
                result= (this.id==aux.getId()
                && this.nombre.equals(aux.getNombre()))
                && this.turno.equals(aux.getTurno());
            }
        }
        return result;
    }
    
    
}
