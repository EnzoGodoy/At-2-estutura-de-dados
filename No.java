public class No<T> {

    public T dado;

    public No<T> anterior;

    public No<T> proximo;

    public No(T dado) {

        this.dado = dado;

        this.anterior = null;
        
        this.proximo = null;
    }
}
