/* 
 * A constante DEBUG deve ser usada para visualizar quando desejado todos os valores 
 * gerados para cada dia em cada sensor. 
 */

package globalsolut;

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class analisador {
	public static final int SENSORES = 20;
	public static final int DIAS = 3;
	public static final int ID_MIN = 4600;
	public static final int ID_MAX = 4800 - ID_MIN;
	public static final int IDS = 20;
	public static final int PH_MIN = 40;
	public static final int PH_MAX = 81 - PH_MIN;
	public static final int UMIDADE_MIN = 20;
	public static final int UMIDADE_MAX = 91 - UMIDADE_MIN;
	public static final float PH_OK_MIN = (float) 5.5;
	public static final float PH_OK_MAX = (float) 6.5;
	public static final int CRESCENTE = 1;
	public static final int DECRESCENTE = 2;
	public static final boolean DEBUG = true;
	
	// Método para gerar ids Aleatórios
	public static int[] ids_Aleatorios(int vetorSensor[], int quantidade)
	{
		Random r = new Random();
		
		for(int i = 0; i < quantidade; i++)
		{
			vetorSensor[i] = r.nextInt(ID_MAX) + ID_MIN;
		}
		
		return vetorSensor;
	}
	
	// Método para gerar pHs Aleatórios
	public static float[] pHs_Aleatorios(float vetorpH[], int quantidade)
	{
		Random r = new Random();
		
		for(int i = 0; i < quantidade; i++)
		{
			vetorpH[i] = (float) (r.nextInt(PH_MAX) + PH_MIN) / 10;
		}
		
		return vetorpH;
	}
	
	// Método para gerar umidades Aleatórias
	public static int[] umidades_Aleatorias(int vetorUmidade[], int quantidade)
	{
		Random r = new Random();
		
		for(int i = 0; i < quantidade; i++)
		{
			vetorUmidade[i] = r.nextInt(UMIDADE_MAX) + UMIDADE_MIN;
		}
		
		return vetorUmidade;
	}
	
	// Loop pergunta preferência de ordenação
	public static int preferenciaOrdenacao()
	{
		Scanner teclado = new Scanner(System.in);
		boolean fim = true;
		int resp = 0;
		
		while (fim)
		{
			System.out.print("Deseja ordenar por ID(1), pH(2) ou Umidade(3)?\n"
							 + "Digite '1' para ID e '2' para pH e '3' para Umidade: ");
			resp = teclado.nextInt();
	        if (resp != 1 && resp != 2 && resp != 3)
	        {
	        	System.out.println("\nPor favor, digite um valor válido ('1', '2' ou '3')");
	        }
	        else
	        {
	        	fim = false;
	        }
		}
		
        //teclado.close();
        
        return resp;
	}
	
	// Loop pergunta pesquisa por ID
	public static void pesquisa(int vetorSensor[])
	{
		Scanner teclado = new Scanner(System.in);
		boolean fim1 = true;
		boolean fim2 = true;
		String resp;
		int id = 0;
		int indice = -1;
		
		while (fim1)
		{
			System.out.print("Gostaria de pesquisar um ID?\n"
							 + "'SIM' ou 'NAO': ");
			resp = teclado.next();
	        if (resp.toUpperCase().equals("SIM"))
	        {
	        	fim1 = false;
	        	while (fim2)
				{
					System.out.print("Qual ID gostaria de pesquisar?\n"
									 + "Digite somente o numero: ");
					id = teclado.nextInt();
					
					for(int i = 0; i < vetorSensor.length; i++)
					{
						if(id == vetorSensor[i]) 
						{
							fim2 = false;
							indice = i;
							System.out.println("O indice de Registro do Sensor " + id + " e: " + indice);
							break;
						}
					}
					if(indice == -1) System.out.println("\nSensor nao disponivel!");
				}
	        	pesquisa(vetorSensor);
	        }
	        else if(resp.toUpperCase().equals("NAO"))
	        {
	        	return;
	        }
	        else System.out.println("\nPor favor, digite uma resposta valida ('SIM' ou 'NAO')");
		}
		return;
	}
	
	// Loop pergunta ordem Crescente / Decrescente
	public static int ordemIDs()
	{
		Scanner teclado = new Scanner(System.in);
		boolean fim = true;
		int resp = 0;
		
		while (fim)
		{
			System.out.print("\nDeseja ver em ordem CRESCENTE(1) ou DESCRESCENTE(2)?\n"
							 + "Digite '1' para CRESCENTE e '2' para DESCRESCENTE: ");
			resp = teclado.nextInt();
	        if (resp != 1 && resp != 2)
	        {
	        	System.out.println("Por favor, digite um valor válido ('1' ou '2')");
	        }
	        else
	        {
	        	fim = false;
	        }
		}
		
        //teclado.close();
        
        return resp;
	}
	
	// Método para atualizar os valores de pH da lista 'pH_Alterado'
	public static List<Integer> Atualiza_pH_Alterado(List<Integer> lista_pH_Alterado, int vetorSensor[], float vetorpH[], int quantidade)
	{
		for(int i = 0; i < quantidade; i++)
		{
			if(!(lista_pH_Alterado.contains(vetorSensor[i])) && 
				(vetorpH[i] < PH_OK_MIN || vetorpH[i] > PH_OK_MAX))
			{
				lista_pH_Alterado.add(vetorSensor[i]);
			}
		}
		return lista_pH_Alterado;
	}
	
	// Método para Debug de todos os valores dos Sensores, como 'ID', 'pH' e 'Umidade'
	public static void Debug_Sensores(int[] vetorSensor, float[]vetorpH, int[] vetorUmidade, 
									  int quantidade, 
									  boolean Mostrar_ID, boolean Mostrar_PH, boolean Mostrar_UM, 
									  int ordenacao)
	{
		if (ordenacao == 1)
		{
			for(int i = 0; i < quantidade; i++)
			{
				if(Mostrar_ID) System.out.print("ID=" + vetorSensor[i]);
				if(Mostrar_PH) System.out.print("\tpH=" + vetorpH[i]);
				if(Mostrar_UM) System.out.print("\tumidade=" + vetorUmidade[i] + "%\n");
			}
		}
		else
		{
			for(int i = quantidade-1; i >= 0; i--)
			{
				if(Mostrar_ID) System.out.print("ID=" + vetorSensor[i]);
				if(Mostrar_PH) System.out.print("\tpH=" + vetorpH[i]);
				if(Mostrar_UM) System.out.print("\tumidade=" + vetorUmidade[i] + "%\n");
			}
		}
	}
	
	// Método para Debug de todos os valores de pH alterados
	public static void Debug_pH_Alterado(List<Integer> vetorpH_Alterado)
	{
		int qtd = 0;
		
		for(int i = 0; i < vetorpH_Alterado.size(); i++)
		{
			System.out.println("ID=" + vetorpH_Alterado.get(i));
			qtd++;
		}
		System.out.println("Quantidade de pHs Alterados: " + qtd + "\n");
	}
	
	// Método QuickSort
	public static void quickSort(int vetorSensor[], float vetorpH[], int vetorUmidade[], int preferencia, int inicio, int fim)
	{
		int i = inicio;
		int j = fim - 1;
		int pivo = 0;
		float pivo_float = 0;
		
		if(preferencia == 1)
		{
			pivo = vetorSensor[(inicio + fim)/2];
			//System.out.println("case1");
		}
		else if (preferencia == 2)
		{
			pivo_float = vetorpH[(inicio + fim)/2];
			//System.out.println("case2");
		}
		else if (preferencia == 3)
		{
			pivo = vetorUmidade[(inicio + fim)/2];
			//System.out.println("case3");
		}
		
		while (i <= j) {
			if(preferencia == 1)
			{
				while (vetorSensor[i] < pivo && i < fim) 	{
					i++;
				}

				while (vetorSensor[j] > pivo && j > inicio) {
					j--;
				}
			}
			else if (preferencia == 2)
			{
				while (vetorpH[i] < pivo_float && i < fim) 	{
					i++;
				}

				while (vetorpH[j] > pivo_float && j > inicio) {
					j--;
				}
			}
			else if (preferencia == 3)
			{
				while (vetorUmidade[i] < pivo && i < fim) 	{
					i++;
				}

				while (vetorUmidade[j] > pivo && j > inicio) {
					j--;
				}
			}

			if (i <= j) {
				int aux1 = vetorSensor[i];
				float aux2 = vetorpH[i];
				int aux3 = vetorUmidade[i];
				
				vetorSensor[i] = vetorSensor[j];
				vetorpH[i] = vetorpH[j];
				vetorUmidade[i] = vetorUmidade[j];
				
				vetorSensor[j] = aux1;
				vetorpH[j] = aux2;
				vetorUmidade[j] = aux3;
				
				i++;
				j--;
			}
		}

		if(j > inicio)
			quickSort(vetorSensor, vetorpH, vetorUmidade, preferencia, inicio, j+1);

		if(i < fim)
			quickSort(vetorSensor, vetorpH, vetorUmidade, preferencia, i, fim);
	}
	
	public static void main(String[] args) 
	{		
		// Exercício 1)
		
		int[] mapaSensores = new int[SENSORES];
		float[] pH = new float[SENSORES];
		int[] umidade = new int[SENSORES];
		
		mapaSensores = ids_Aleatorios(mapaSensores, SENSORES);
		pH = pHs_Aleatorios(pH, SENSORES);
		umidade = umidades_Aleatorias(umidade, SENSORES);
		
		int pref = preferenciaOrdenacao();
		int ordem = ordemIDs();
		
		if(DEBUG)
		{
			System.out.println("\nPref: " + pref);
			System.out.println("Ordem: " + ordem);
		}
		
		quickSort(mapaSensores, pH, umidade, pref, 0, mapaSensores.length);
		
		if(DEBUG)
		{
			System.out.println("\n========= DIA 1: =========" );
			Debug_Sensores(mapaSensores, pH, umidade, SENSORES, true, true, true, ordem);
		}
		
		
		// Exercício 2)
		
		List<Integer> pH_Alterado = new ArrayList<>(); // Criando Lista dos pHs alterados
		
		Atualiza_pH_Alterado(pH_Alterado, mapaSensores, pH, SENSORES);
		
		pH_Alterado.sort(null);
		
		if (DEBUG) {
			System.out.println("\n====== pHs Alterados Dia 1: ======");
			Debug_pH_Alterado(pH_Alterado);
		}
		
		
		// Exercício 3)
		
		pH = pHs_Aleatorios(pH, SENSORES);
		umidade = umidades_Aleatorias(umidade, SENSORES);
		
		pref = preferenciaOrdenacao();
		ordem = ordemIDs();
		
		if(DEBUG)
		{
			System.out.println("\nPref: " + pref);
			System.out.println("Ordem: " + ordem);
		}
		
		quickSort(mapaSensores, pH, umidade, pref, 0, mapaSensores.length);
		
		if(DEBUG)
		{
			System.out.println("\n========= DIA 2: =========" );
			Debug_Sensores(mapaSensores, pH, umidade, SENSORES, true, true, true, ordem);
		}
		
		
		// Exercício 4)
		
		Atualiza_pH_Alterado(pH_Alterado, mapaSensores, pH, SENSORES);
		
		pH_Alterado.sort(null);
		
		if (DEBUG) {
			System.out.println("\n====== pHs Alterados Dia 2: ======");
			Debug_pH_Alterado(pH_Alterado);
		}
		
		
		// Exercício 5)
		
		pH = pHs_Aleatorios(pH, SENSORES);
		umidade = umidades_Aleatorias(umidade, SENSORES);
		
		Atualiza_pH_Alterado(pH_Alterado, mapaSensores, pH, SENSORES);
		
		pH_Alterado.sort(null);
		
		pref = preferenciaOrdenacao();
		ordem = ordemIDs();
		
		if(DEBUG)
		{
			System.out.println("\nPref: " + pref);
			System.out.println("Ordem: " + ordem);
		}
		
		quickSort(mapaSensores, pH, umidade, pref, 0, mapaSensores.length);
		
		if (DEBUG) {
			System.out.println("\n========= DIA 3: =========" );
			Debug_Sensores(mapaSensores, pH, umidade, SENSORES, true, true, true, ordem);
			
			System.out.println("\n====== pHs Alterados Dia 3: ======");
			Debug_pH_Alterado(pH_Alterado);
		}
		
		
		// Exercício 6, 7 e 8)
		
		pesquisa(mapaSensores);
		
		System.out.println("Fim do programa.");

	}
}