import java.io.*;

public class Grafo {
    ListaDobleEnlazada<Barrio> listaDeBarrios;

    public Grafo() {
        listaDeBarrios = new ListaDobleEnlazada<>();
        cargarBarriosDesdeArchivo();
    }

    private void cargarBarriosDesdeArchivo() {
        try (BufferedReader br = new BufferedReader(new FileReader("barrios.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String nombreBarrio = data[0];
                double distancia = 0.0;
                try {
                    distancia = Double.parseDouble(data[2]);
                } catch (NumberFormatException e) {
                    // Manejar la excepción aquí
                    System.err.println("Error al convertir la distancia a un número.");
                    e.printStackTrace();
                }
                listaDeBarrios.add(new Barrio(nombreBarrio, distancia));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public boolean add(Vertice v) {
        return true;
    }

    public void distanciaMasCorta(String origen, String destino) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("barrios.txt"));
            String line;
            listaDeBarrios = new ListaDobleEnlazada<>();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String nombreBarrio = data[0];
                double distancia = 0.0; // Siempre se inicializa a 0.0
                if (data.length >= 2) {
                    distancia = Double.parseDouble(data[1]);
                }
                listaDeBarrios.add(new Barrio(nombreBarrio, distancia));
            }

            br = new BufferedReader(new FileReader("barrios.txt")); // Reiniciar el BufferedReader

            int cantNodos = listaDeBarrios.getSize();
            GrafoDP grafo = new GrafoDP();
            grafo.inicializar(cantNodos);

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int indiceOrigen = listaDeBarrios.indiceDe(new Barrio(data[0]));
                int indiceDestino = listaDeBarrios.indiceDe(new Barrio(data[1]));
                double valor = 0.0; // Siempre se inicializa a 0.0
                if (data.length >= 3) {
                    valor = Double.parseDouble(data[2]);
                }
                grafo.agregarArista(indiceOrigen, indiceDestino, valor);
            }

            boolean[][] matrizAdyacencia = new boolean[cantNodos][cantNodos];
            for (int i = 0; i < cantNodos; i++) {
                for (int j = 0; j < cantNodos; j++) {
                    if (grafo.getGrafo()[i][j] != GrafoDP.INFINITO && grafo.getGrafo()[i][j] != 0) {
                        matrizAdyacencia[i][j] = true;
                    } else {
                        matrizAdyacencia[i][j] = false;
                    }
                }
            }

            boolean anterior, ik, kj, actual;

            // para cada iteración k del algoritmo
            for (int k = 0; k < cantNodos; k++) {
                // para cada fila de la matriz
                for (int i = 0; i < cantNodos; i++) {
                    // para cada columna de la matriz
                    for (int j = 0; j < cantNodos; j++) {

                        // si el pivot ij no está en la diagonal principal ni en la fila k o columna k
                        if (i != j && k != i && k != j) {
                            // existe camino en la iteración anterior
                            anterior = matrizAdyacencia[i][j];

                            // existe camino usando como intermediario al nodo k
                            ik = matrizAdyacencia[i][k];
                            kj = matrizAdyacencia[k][j];
                            actual = (ik && kj);

                            // existe algún camino
                            matrizAdyacencia[i][j] = (anterior || actual);
                        }
                    }
                }
            }

            // muestro solución en consola
            System.out.println("WARSHALL: Caminos entre todos los nodos ");
            for (int i = 0; i < cantNodos; i++) {
                for (int j = 0; j < cantNodos; j++) {
                    System.out.println("Nodo Inicial: " + i + " Nodo Final: " + j + " Existe camino: " + matrizAdyacencia[i][j]);
                }
            }

            // escribo la solución completa en un archivo
            FileWriter file = new FileWriter("solucion.out");
            BufferedWriter buffer = new BufferedWriter(file);

            buffer.write(String.valueOf(cantNodos));
            buffer.write(" ");
            buffer.write(String.valueOf(grafo.getCantAristas()));
            buffer.write(" ");
            buffer.write(String.valueOf(grafo.getPtajeAdyacencia()));
            buffer.write(" ");
            buffer.write(String.valueOf(grafo.getGradoMax()));
            buffer.write(" ");
            buffer.write(String.valueOf(grafo.getGradoMin()));
            buffer.newLine();

            for (int i = 0; i < cantNodos; i++) {
                for (int j = 0; j < cantNodos; j++) {
                    buffer.write(String.valueOf(i));
                    buffer.write(" ");
                    buffer.write(String.valueOf(j));
                    buffer.write(" ");
                    buffer.write(String.valueOf(matrizAdyacencia[i][j]));
                    buffer.newLine();
                }
            }

            buffer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
