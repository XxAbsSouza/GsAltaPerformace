package analiseComparativa;

import java.util.Arrays;
import java.util.Random;

import arvores.ABBcidade;
import arvores.AVLcidade;
import cidade.Cidade;

public class Comparacao {
    ABBcidade abb = new ABBcidade();
    AVLcidade avl = new AVLcidade();
    String cidades[] = { "Analandia", "Araraquara", "Dourado", "Ibitinga", "Matao", "S�o Carlos", "Tabatinga" };
 static int totalComp = 0;
   static int totalTroca = 0;
    Cidade[] cidade = new Cidade[cidades.length];

    public Comparacao() {
        for (int i = 0; i < cidades.length; i++) {
            cidade[i] = new Cidade(cidades[i], gerarNumeroAleatorio(1, 100), gerarNumeroAleatorio(1, 100));
        }
        for (Cidade cidad : cidade) {
            abb.root = abb.inserir(abb.root, cidad);
        }
        System.out.println("ABB");
        int count = 1;
        for (int i = 0; i < cidade.length; i++) {
            if (cidade[i].getNumCasos() != 0) {
                abb.root = abb.inserir(abb.root, cidade[i]);
                System.out.println("Comparações para o item #" + count + ": " + abb.comparisons);
            }
        }

        abb.listaEmOrdem(abb.root);

    }

    public static int gerarNumeroAleatorio(int min, int max) {
        Random random = new Random();
        return Math.round(random.nextInt((max - min) + 1) + min);
    }

    public static void quicksort(Cidade x[], int li, int ls) {
        int j;
        if (li < ls) {
            j = particiona(x, li, ls);
            quicksort(x, li, j - 1);
            quicksort(x, j + 1, ls);
        }
    }

    public static int particiona(Cidade x[], int li, int ls) {
        double pivo = x[ls].getPorcentagemVacina();
        int acima = ls;
        int abaixo = li;
        while (abaixo < acima) {
            while (x[abaixo].getPorcentagemVacina() < pivo && abaixo < ls) {
                abaixo++;
                totalComp++;
            }
            while (x[acima].getPorcentagemVacina() >= pivo && acima > abaixo) {
                acima--;
                                totalComp++;

            }
            if (abaixo < acima) {
                // Trocar elementos
                Cidade temp = x[abaixo];
                x[abaixo] = x[acima];
                x[acima] = temp;
                x[abaixo].incrementQuicksortComparisons();
                x[acima].incrementQuicksortComparisons();
                totalTroca++;
            }
        }
        // Trocar o pivô para a posição correta
        Cidade temp = x[ls];
        x[ls] = x[acima];
        x[acima] = temp;
        x[ls].incrementQuicksortComparisons();
        x[acima].incrementQuicksortComparisons();
        totalTroca++;
        return acima;
    }

    public static void main(String[] args) {
        // You can create an instance of Aplicacao and perform any further operations
        // here.
        Comparacao comparacao = new Comparacao();
        Cidade[] cidadeCopy = Arrays.copyOf(comparacao.cidade, comparacao.cidade.length);

        // Filter out cities with zero cases
        Cidade[] cidadesValidas = Arrays.stream(cidadeCopy)
                .filter(c -> c.getNumCasos() != 0)
                .toArray(Cidade[]::new);

        // Perform quicksort on the cidadesValidas array
        quicksort(cidadesValidas, 0, cidadesValidas.length - 1);
        
   
        // Display the sorted array
        System.out.println("\nCIDADES EM ALERTA (Ordenadas por Porcentagem de Vacinação):");
        for (Cidade cidad : cidadesValidas) {
            System.out.println("Cidade: " + cidad.getNomeCity() +
                    ", Porcentagem de Vacinação: " + cidad.getPorcentagemVacina() +
                    ", Numero de Casos: " + cidad.getNumCasos());
                    
        }
        System.out.println(totalComp+" e "+totalTroca);
    }

}