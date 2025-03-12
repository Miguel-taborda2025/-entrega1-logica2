public class ListaCircular {
    private Nodo cabeza = null;
    private Nodo cola = null;

    // esta es un clase interna para representar un nodo
    private class Nodo {
        int dato;
        Nodo siguiente;

        public Nodo(int dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    // Método para agregar un nodo al final
    public void agregar(int dato) {
        Nodo nuevo = new Nodo(dato);
        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
            nuevo.siguiente = cabeza; // aqui se cierra el ciclo
        } else {
            cola.siguiente = nuevo;
            cola = nuevo;
            cola.siguiente = cabeza; // con esto se mantiene circular, ya que conecta la cola 
            //con la cabeza volviendose circular
        }
    }

    // este es un método para eliminar un nodo por su valor
    public void eliminar(int dato) {
        if (cabeza == null) return;

        if (cabeza.dato == dato) { // solo si la cabeza es el nodo a eliminar
            if (cabeza == cola) { // solo si hay solo un nodo, el cual seria cabeza y cola al mismo tiempo
                cabeza = null;
                cola = null;
            } else {
                cabeza = cabeza.siguiente;
                cola.siguiente = cabeza;
            }
            return;
        }

        Nodo actual = cabeza;
        while (actual.siguiente != cola && actual.siguiente.dato != dato) {
            actual = actual.siguiente;
        }

        if (actual.siguiente.dato == dato) {
            actual.siguiente = actual.siguiente.siguiente;
            if (actual.siguiente == cabeza) {
                cola = actual;
            }
        }
    }

    // este método imprime la lista
    public void imprimir() {
        if (cabeza == null) {
            System.out.println("Lista vacía");
            return;
        }

        Nodo actual = cabeza;
        do {
            System.out.print(actual.dato + " -> ");
            actual = actual.siguiente;
        } while (actual != cabeza);
        System.out.println("(regresa al inicio)");
    }

    // método de ejemplo (prueba)
    public static void main(String[] args) {
        ListaCircular lista = new ListaCircular();

        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);
        lista.imprimir(); // 1 -> 2 -> 3 -> (regresa al inicio)

        lista.eliminar(2);
        lista.imprimir(); // 1 -> 3 -> (regresa al inicio)
    }
}
