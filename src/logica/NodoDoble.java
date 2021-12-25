package logica;

public class NodoDoble<T>{
	
	private T t;
	private NodoDoble<T> next;
	private NodoDoble<T> prev;
	
	public NodoDoble(T t) {
		this.t = t;
		next = null;
		prev = null;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public NodoDoble<T> getNext() {
		return next;
	}

	public void setNext(NodoDoble<T> next) {
		this.next = next;
	}

	public NodoDoble<T> getPrev() {
		return prev;
	}

	public void setPrev(NodoDoble<T> prev) {
		this.prev = prev;
	}
	
}
