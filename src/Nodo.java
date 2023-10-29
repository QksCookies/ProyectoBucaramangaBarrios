public class Nodo<T> implements NodeInterface<T> {
        T objeto;

public Nodo() {
        objeto = null;
        }

public Nodo(T objeto) {
        this.objeto = objeto;
        }

@Override
public boolean setObject(T object) {
        if (object != null) {
        try {
        this.objeto = object;
        return true;
        } catch (Exception e) {

        }
        }
        return false;
        }

@Override
public T getObject() {
        return this.objeto;
        }

@Override
public boolean isEquals(T object) {
        if (object != null) {
        try {
        return objeto.toString().equals(object.toString());
        } catch (Exception e) {

        }
        }
        return false;
        }

@Override
public Nodo<T> getClone() {
        try {
        if (objeto != null) {
        return (Nodo<T>) this.clone();
        }
        } catch (Exception e) {

        }
        return null;
        }

        }

