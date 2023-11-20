package analiseComparativa;

import java.util.Arrays;
import java.util.Random;

import arvores.ABBcidade;
import cidade.Cidade;
import arvores.AVLcidade;

public class Comparacao {
    ABBcidade abb = new ABBcidade();
AVLcidade avl = new AVLcidade();
    String cidades[] = { "Analandia", "Araraquara", "Dourado", "Ibitinga", "Matao", "S�o Carlos", "Tabatinga" };

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

  

for (int i = 0; i < cidade.length; i++) {
            if (cidade[i].getNumCasos() != 0) {
                System.out.println(
                        "Cidade: " + cidade[i].getNomeCity() + ", Porcentagem de Vacinação: " + cidade[i].getPorcentagemVacina()
                                + ", Numero de Casos: " + cidade[i].getNumCasos());
                avl.root = avl.inserirCidade(avl.root, cidade[i]);
                avl.atualizaAlturas(avl.root);
                avl.mostraFB(avl.root);
                // comparaçõesQuick[i] = comparisonCount;
            }
        }
        Cidade[] cidadesValidas = Arrays.stream(cidade)
            .filter(c -> c.getNumCasos() != 0)
            .toArray(Cidade[]::new);

    // Ordenar vetor usando Quicksort
     quicksort(cidadesValidas, 0, cidadesValidas.length - 1);

    for (int i = 0; i < cidade.length; i++) {
        if (cidade[i].getNumCasos() != 0) {
            System.out.println("#Cidade " + cidade[i].getNomeCity());
            System.out.println("Número de Comparação em QuickSort: " + cidade[i].getQuicksortComparisons());

        }
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
            }
            while (x[acima].getPorcentagemVacina() >= pivo && acima > abaixo) {
                acima--;
            }
            if (abaixo < acima) {
                // Trocar elementos
                Cidade temp = x[abaixo];
                x[abaixo] = x[acima];
                x[acima] = temp;
                x[abaixo].incrementQuicksortComparisons();
                x[acima].incrementQuicksortComparisons();
            }
        }
    // Trocar o pivô para a posição correta
    Cidade temp = x[ls];
    x[ls] = x[acima];
    x[acima] = temp;
    x[ls].incrementQuicksortComparisons();
    x[acima].incrementQuicksortComparisons();
    return acima;
}
    public static void main(String[] args) {
        // You can create an instance of Aplicacao and perform any further operations
        // here.
        Comparacao comparacao = new Comparacao();
    }
}