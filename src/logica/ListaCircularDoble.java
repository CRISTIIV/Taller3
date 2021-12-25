package logica;

public class ListaCircularDoble<T> implements Lista<T>{

	private NodoDoble<T> first;
	private int size;
	
	public ListaCircularDoble() {
		first = null;
		size = 0;
	}

	@Override
	public void add(T t) {
        NodoDoble<T> nuevo = new NodoDoble<T>(t);
        if(first==null){
            first = nuevo;
            first.setNext(first);
            first.setPrev(first);  
            size++;
        } else {
            //agregar al principio
            nuevo.setNext(first); 
            nuevo.setPrev(first.getPrev()); 
            first.setPrev(nuevo); 
            nuevo.getPrev().setNext(nuevo);
            first = nuevo;
            size++;
        }	
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public T get(int i) {
        if(i > size())return null;
        
        int contador = 0;
        NodoDoble<T> current = first;
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

}
