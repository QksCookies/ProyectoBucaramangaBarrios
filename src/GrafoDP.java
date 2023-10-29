public class GrafoDP {
    public static final int INFINITO = Integer.MAX_VALUE;
    private int[][] grafo;
    private double ptajeAdyacencia;
    private int gradoMax;
    private int gradoMin;
    private int cantNodos;
    private int cantAristas;
    
    public GrafoDP(){
    }

    public GrafoDP(int cantNodos) {
        this.cantNodos = cantNodos;
        this.grafo = new int[cantNodos][cantNodos];
    }

    public int[][] getGrafo() {
        return grafo;
    }

    public double getPtajeAdyacencia() {
        return ptajeAdyacencia;
    }

    public int getGradoMax() {
        return gradoMax;
    }

    public int getGradoMin() {
        return gradoMin;
    }

    public int getCantNodos() {
        return cantNodos;
    }

    public int getCantAristas() {
        return cantAristas;
    }

    public void inicializar(int cantNodos) {
        this.cantNodos = cantNodos;
        this.grafo = new int[cantNodos][cantNodos];
    }

    public void agregarArista(int indiceOrigen, int indiceDestino, double valor) {
        grafo[indiceOrigen][indiceDestino] = (int) valor;
    }
}

