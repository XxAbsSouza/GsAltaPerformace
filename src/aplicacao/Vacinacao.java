package aplicacao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import arvores.ABBcidade;
import arvores.AVLcidade;
import cidade.Cidade;

public class Vacinacao {


	public static void main(String[] args) {
		
		AVLcidade avlCidade = new AVLcidade();
		AVLcidade avlCidadeAbaixo80 = new AVLcidade();
		ABBcidade abbCidade = new ABBcidade();
		/*
		 * Os vetores criados s�o para que realizar testes solicitados 
		 */
		String cidades[]   = {"Analandia","Araraquara","Dourado","Ibitinga","Matao","S�o Carlos","Tabatinga"};
		double vacinacao[]  = {72.5, 88.4, 71.9, 76, 78.8, 96.7,66};
		int nCasos[]  = {1, 0, 2, 0, 0, 1, 1};
		/*
		 * 1) Insere na AVL organizada por nome da cidade
		 */
		
		Cidade[] cidade = new Cidade[cidades.length];
		for (int i = 0; i < cidades.length; i++) {
			cidade[i] = new Cidade(cidades[i], vacinacao[i], nCasos[i]);
		}
		for (Cidade cidad : cidade) {
			// System.out.println("Cidade: " + cidad.getNomeCity() + ", Porcentagem de Vacinação: " + cidad.getPorcentagemVacina()
			// 		+ ", Numero de Casos: " + cidad.getNumCasos());
			avlCidade.root = avlCidade.inserirCidade(avlCidade.root, cidad);
			avlCidade.atualizaAlturas(avlCidade.root);
			avlCidade.mostraFB(avlCidade.root);
		}
		/* 
		 * 2) Usando um metodo da classe AVL gerar vetor de cidades com vacinacao menor do 80% e pelo menos 1 caso
		 * 	  Depois, ordenar vetor usando quicksort.
		 */

		Cidade[] cidadeAbaixoDe80 = new Cidade[cidade.length];

		for (int i = 0; i < cidadeAbaixoDe80.length; i++) {
			cidadeAbaixoDe80[i] = new Cidade("", 0.0, 0);
		}

		for (int j = 0; j < cidadeAbaixoDe80.length; j++) {
			if (cidade[j].getPorcentagemVacina() < 80 && cidade[j].getNumCasos() >= 1) {
				cidadeAbaixoDe80[j] = new Cidade(cidade[j].getNomeCity(),
						cidade[j].getPorcentagemVacina(), cidade[j].getNumCasos());
			}
		}

		for (int i = 0; i < cidadeAbaixoDe80.length; i++) {
			if (cidadeAbaixoDe80[i].getNumCasos() != 0) {
				System.out.println(
						"Cidade: " + cidadeAbaixoDe80[i].getNomeCity() + ", Porcentagem de Vacinação: " + cidadeAbaixoDe80[i].getPorcentagemVacina()
								+ ", Numero de Casos: " + cidadeAbaixoDe80[i].getNumCasos());
				avlCidadeAbaixo80.root = avlCidadeAbaixo80.inserirCidade(avlCidadeAbaixo80.root, cidadeAbaixoDe80[i]);
				avlCidadeAbaixo80.atualizaAlturas(avlCidadeAbaixo80.root);
				avlCidadeAbaixo80.mostraFB(avlCidadeAbaixo80.root);
				// comparaçõesQuick[i] = comparisonCount;
			}
		}

		Cidade[] cidadesValidas = Arrays.stream(cidadeAbaixoDe80)
            .filter(c -> c.getNumCasos() != 0)
            .toArray(Cidade[]::new);

    // Ordenar vetor usando Quicksort
	quicksort(cidadesValidas, 0, cidadesValidas.length - 1);

	for (int i = 0; i < cidadeAbaixoDe80.length; i++) {
		if (cidadeAbaixoDe80[i].getNumCasos() != 0) {
			System.out.println("#Cidade " + cidadeAbaixoDe80[i].getNomeCity());
			System.out.println("Número de Comparação em QuickSort: " + cidadeAbaixoDe80[i].getQuicksortComparisons());
		
		}
	}

	System.out.println();
	System.out.println("CIDADES EM ALERTA:");
    // Exibir cidades ordenadas
	for (Cidade cidad : cidadesValidas) {
        System.out.println("Cidade: " + cidad.getNomeCity() +
                ", Porcentagem de Vacinação: " + cidad.getPorcentagemVacina() +
				", Numero de Casos: " + cidad.getNumCasos());

			}
		
		/* 3) Gerar ABB percorrendo AVL, usando um metodo da classe AVL. ABB � organizada pela cobertura vacinal.
		 * 
		 */
		Cidade[] cidadeAbaixoDe80ABB = new Cidade[cidade.length];

		for (int i = 0; i < cidadeAbaixoDe80ABB.length; i++) {
			cidadeAbaixoDe80ABB[i] = new Cidade("", 0.0, 0);
		}

		for (int j = 0; j < cidadeAbaixoDe80ABB.length; j++) {
			if (cidade[j].getPorcentagemVacina() < 80 && cidade[j].getNumCasos() >= 1) {
				cidadeAbaixoDe80ABB[j] = new Cidade(cidade[j].getNomeCity(), cidade[j].getPorcentagemVacina(),
						cidade[j].getNumCasos());
			}
		}
		
		System.out.println("ABB");
		int count = 1;
		for (int i = 0; i < cidadeAbaixoDe80ABB.length; i++) {
			if (cidadeAbaixoDe80ABB[i].getNumCasos() != 0) {
				abbCidade.root = abbCidade.inserir(abbCidade.root, cidadeAbaixoDe80ABB[i]);
				System.out.println("Comparações para o item #" + count + ": " + abbCidade.comparisons);
			}
		}

		abbCidade.listaEmOrdem(abbCidade.root);

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
}
