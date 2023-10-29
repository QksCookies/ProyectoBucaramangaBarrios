public class NodoDobleEnlazado<T> extends Nodo<T> {
        NodoDobleEnlazado<T> siguiente;
        NodoDobleEnlazado<T> anterior;

public NodoDobleEnlazado() {
        super();
        this.siguiente = null;
        this.anterior = null;
        }

public NodoDobleEnlazado(T object) {
        super(object);
        this.siguiente = null;
        this.anterior = null;
        }

    public NodoDobleEnlazado(T object, NodoDobleEnlazado<T> siguiente) {
        super(object);
        this.siguiente = siguiente;
        this.anterior = null;
        }

    public NodoDobleEnlazado(T object, NodoDobleEnlazado<T> siguiente,
        NodoDobleEnlazado<T> anterior) {
        super(object);
        this.siguiente = siguiente;
        this.anterior = anterior;
        }

    public NodoDobleEnlazado<T> getNext() {
        return this.siguiente;
        }

    public void setNext(NodoDobleEnlazado<T> siguiente) {
        this.siguiente = siguiente;
        }

    public NodoDobleEnlazado<T> getPrevious() {
        return anterior;
        }

    public void setPrevious(NodoDobleEnlazado<T> anterior) {
        this.anterior = anterior;
}
}