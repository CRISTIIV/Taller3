package logica;
import dominio.*;

public class ListaCircularDobleEntrega {
	private NodoDoble<Entrega> first;
	private int size;
	
	public ListaCircularDobleEntrega() {
		first = null;
		size = 0;
	}
	
	public void add(Entrega e) {
        NodoDoble<Entrega> nuevo = new NodoDoble<Entrega>(e);
        if(first==null){
            first = nuevo;
            first.setNext(first);
            first.setPrev(first);  
            size++;
        } else {
            nuevo.setNext(first); 
            nuevo.setPrev(first.getPrev());
            first.setPrev(nuevo); 
            nuevo.getPrev().setNext(nuevo);
            first = nuevo;
            size++;
        }
	}
	
	public Entrega buscarEntrega(String codigo) {
        NodoDoble<Entrega> current = first;
        if(current ==null){
            return null;
        }
        while(current.getNext() != first && !current.getT().getCodigo().equals(codigo)){
            current=current.getNext();
        }
        return current.getT();
	}
	
	public boolean eliminar(String codigo) {
        NodoDoble<Entrega> current = first;
        if(current ==null){
            return false;
        }
        while(current.getNext() != first && current.getT().getCodigo().equals(codigo)){
            current=current.getNext();
        }
        if(current.getT().getCodigo().equals(codigo)){
            if(current.getNext()==first){
                first.setPrev(current.getPrev());
                current.getPrev().setNext(first);
                return true;
            }
            if(current == first){
                first.getPrev().setNext(first.getNext());
                first = first.getNext();
                first.setPrev(current.getPrev());
                return true;
            }
            current.getPrev().setNext(current.getNext());
            current.getNext().setPrev(current.getPrev());
            return true;
            
        }
        return false;
	}
	
	public int size() {
		return size;
	}
	
	public Entrega get(int i) {
        if(i > size())return null;
        
        int contador = 0;
        NodoDoble<Entrega> current = first;
        if(current ==null){
            return null;
        }
        while(current.getNext() != first){
            if(contador==i){
                break;
            }
            contador++;
            current=current.getNext();
        }
        return current.getT();
	}
	
	public boolean contains(Entrega e) {
        NodoDoble<Entrega> current = first;
        if(first==null){
            return false;
        }
        while(current.getNext()!=first){
            if(current.getT()==e){
                return true;
            }
            current=current.getNext();
        }
        return false;
	}
}
