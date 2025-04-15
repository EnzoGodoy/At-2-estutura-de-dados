public class ListaDupla<T> {

    private No<T> inicio;

    private No<T> fim;

    private int tamanho;

    public ListaDupla() {

        this.inicio = null;

        this.fim = null;

        this.tamanho = 0;
    }

    public void adicionar(T elemento) {

        No<T> novo = new No<>(elemento);

        if (inicio == null) {

            inicio = fim = novo;

        } else {

            fim.proximo = novo;

            novo.anterior = fim;

            fim = novo;
        }
        tamanho++;
    }

    public boolean remover(T elemento) {

        No<T> atual = inicio;

        while (atual != null) {

            if (atual.dado.equals(elemento)) {

                if (atual.anterior != null) {

                    atual.anterior.proximo = atual.proximo;

                } else {

                    inicio = atual.proximo;
                }

                if (atual.proximo != null) {

                    atual.proximo.anterior = atual.anterior;

                } else {

                    fim = atual.anterior;
                }

                tamanho--;

                return true;
            }
            atual = atual.proximo;
        }
        return false;
    }

    public T buscar(T elemento) {

        No<T> atual = inicio;

        while (atual != null) {

            if (atual.dado.equals(elemento)) {

                return atual.dado;
            }
            atual = atual.proximo;
        }
        return null;
    }

    public int tamanho() {

        return tamanho;
    }

    public boolean estaVazia() {

        return tamanho == 0;
    }

    public No<T> getInicio() {
        
        return inicio;
    }
}
