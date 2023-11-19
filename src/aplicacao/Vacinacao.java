package aplicacao;

import arvores.AVLcidade;
import cidade.Cidade;

public class Vacinacao {

	public static void main(String[] args) {
		
		AVLcidade avlCidade = new AVLcidade();
		/*
		 * Os vetores criados s�o para que realizar testes solicitados 
		 */
		String cidades[]   = {"Analandia","Araraquara","Dourado","Ibitinga","Matao","S�o Carlos","Tabatinga"};
		double vacinacao[]   = {72.5, 88.4, 71.9, 76, 78.8, 96.7,66};
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
		 * 3) Gerar ABB percorrendo AVL, usando um metodo da classe AVL. ABB � organizada pela cobertura vacinal.
		 * 
		 */
			
		}
		
	public static void quicksort(int x[],int li,int ls)
	{
		int j;
		if (li<ls){
			j = particiona(x, li, ls);
			quicksort(x, li, j-1);
			quicksort(x, j+1,ls);
		}
	}
	public static int particiona (int x[], int li, int ls)
	{
		int abaixo,acima;
		int pivo,temp;
		pivo=x[ls];
		acima=ls;
		abaixo=li;
		while(abaixo<acima)
		{
			while(x[abaixo]<pivo && abaixo<ls) {
				abaixo++;
			}
			while (x[acima]>=pivo && acima > abaixo) {
				acima--;
			}
			if (abaixo<acima){
				temp=x[abaixo];
				x[abaixo]=x[acima];
				x[acima]=temp;
			}
		}
		x[ls]=x[acima];
		x[acima]=pivo;
		return acima;
	}

}
