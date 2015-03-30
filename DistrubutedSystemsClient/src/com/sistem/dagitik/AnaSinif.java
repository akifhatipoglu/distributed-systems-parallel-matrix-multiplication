package com.sistem.dagitik;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class AnaSinif {
	//EndPoints:Ba�lan�lacak olan serverlar
	//Ba�lan�lacak olan web servisin IP leri uzant�lar�
	public String endPointOne = "http://30.10.22.64:8080/DistrubutedSystemsWebServices/services/matrixService.matrixServiceHttpEndpoint/";
	public String endPointTwo = "http://30.10.22.62:8080/DistrubutedSystemsWebServices/services/matrixService.matrixServiceHttpEndpoint/";
	public String endPointThree = "http://30.10.22.65:8080/DistrubutedSystemsWebServices/services/matrixService.matrixServiceHttpEndpoint/";
	
	//En sonda olu�an sonuc matrisi: �arp�m�n sonucu
	public static int[][] result;

	public static void main(String[] args) {

		AnaSinif test = new AnaSinif();
		
		// �arp�m� yap�lacak matrisin boyutunu giriniz:
		int matrisBoyutu = Integer.parseInt(JOptionPane.showInputDialog(null,
				"�arp�m� yap�lacak matrisin boyutunu giriniz: ",
				"Matris �arp�m�", JOptionPane.INFORMATION_MESSAGE));

		//matrisin ba�lat�lmas� i�in gerekli sorgu
		int answer = JOptionPane.showConfirmDialog(null,
				"Matris �arp�m� ba�las�n m� ?");

		if (answer == JOptionPane.YES_OPTION) {

			int A[][] = new int[matrisBoyutu][matrisBoyutu];
			int B[][] = new int[matrisBoyutu][matrisBoyutu];
			
			// matrislerin i�erisine random say�lar yerle�tiriyor [1-10]
			for (int i = 0; i < matrisBoyutu; i++) {
				for (int j = 0; j < matrisBoyutu; j++) {
					A[i][j] = (int) (Math.random() * 10) + 1;
					B[i][j] = (int) (Math.random() * 10) + 1;
				}
			}

			// Burada A ve B matrisi hem ekranda (consolda) hemnde input txt ye yaz�yor.
			try {
				// System.out.println ekrana yaz�yor
				// out.println dosyaya yaz�yor
				FileWriter outFile = new FileWriter(new File("input.txt"));
				PrintWriter out = new PrintWriter(outFile);

				System.out.println("A matrisi : ");
				out.println("A matrisi :");
				for (int i = 0; i < matrisBoyutu; i++) {
					for (int j = 0; j < matrisBoyutu; j++) {
						System.out.print(A[i][j]);
						System.out.print(" ");
						out.print(A[i][j]);
						out.print(" ");
					}
					out.println();
					System.out.println();
				}

				System.out.println("B matrisi : ");
				out.println("B matrisi : ");
				for (int i = 0; i < matrisBoyutu; i++) {
					for (int j = 0; j < matrisBoyutu; j++) {
						System.out.print(B[i][j]);
						System.out.print(" ");
						out.print(B[i][j]);
						out.print(" ");
					}
					out.println();
					System.out.println();
				}

				out.close();

			} catch (Exception e) {
				System.err.println("Hata: " + e.getMessage());
			}

			// Matris �arp�m�n�n sonucu yani  result onu haz�rl�yor
			test.result = new int[matrisBoyutu][matrisBoyutu];

			// Ay�rma i�leminin ba�lang�c� : sistemden saati al�yor
			long startTime = System.currentTimeMillis();

			/*
			 * Ana matris i�lemi ba�lar -matris ay�rma -thread ler ile
			 * matrisleri ilgili endpointslerle birlikte (ba�ka pc lerdeki web
			 * servisimiz) olu�turma -olu�an thread i ba�latma -olu�an
			 * threadleri havuz gibi bir yerde s�raya sokma
			 */
			for (int i = 0; i < matrisBoyutu; i++) {
				for (int j = 0; j < matrisBoyutu; j++) {
					int[] columnB = new int[matrisBoyutu];
					
					// B matrisinin sutununu alma
					for (int j2 = 0; j2 < matrisBoyutu; j2++) {
						columnB[j2] = B[j2][j];
					}
					
					try {
						// her matris s�t�n ve sat�r� i�in yeni bir endpoint
						// belirler
						String endpoints = test.getEndPoint();

						matris m = new matris(i, j, A[i], columnB,matrisBoyutu, endpoints);

						// her thread i�in ko�abilecekleri s�n�f olu�turulur.
						ThreadRunnable d = new ThreadRunnable(m);


						// her makina ba��na bir thread ile farkl� bir deneyim
						if (endpoints.contains("30.10.22.64")) {
							
							//her thread ko�uca�� runnable s�n�f� al�r.
							Thread t1 = new Thread(d);
							//ko�maya ba�lar
							t1.start();
							// ilk ko�an�n ilk s�raya girmesini sa�lar
							t1.join();
						}
						if (endpoints.contains("30.10.22.62")) {
							Thread t2 = new Thread(d);
							t2.start();
							t2.join();
						}
						if (endpoints.contains("30.10.22.65")) {
							Thread t3 = new Thread(d);
							t3.start();
							t3.join();
						}

					} catch (Exception e) {
						e.printStackTrace();
					}

				}
				System.out.println();
			}

			long endTime = System.currentTimeMillis();
			long estimatedTime = endTime - startTime; // Ge�en s�reyi milisaniye
														// cinsinden elde
														// ediyoruz
			double seconds = (double) estimatedTime / 1000;
			//bu k�s�mda ge�en s�re saniye cinsinden hesaplan�r.

			try {
				
				//Bu k�s�mda matris �arp�m�n�n sonucu hem txt dosyas�na yaz�l�r hem de ekrana yaz�l�r.
				// System.out.println ekrana yaz�yor
				// out.println dosyaya yaz�yor
				FileWriter outFile = new FileWriter(new File("output.txt"));
				PrintWriter out = new PrintWriter(outFile);

				System.out.println("End: " + seconds);
				System.out.println();
				System.out.println("Sonuc matrisi : ");
				out.println();
				out.println();
				out.println("End: " + seconds);
				out.println("Sonuc matrisi : ");
				for (int i = 0; i < matrisBoyutu; i++) {
					for (int j = 0; j < matrisBoyutu; j++) {
						System.out.print(test.result[i][j]);
						System.out.print(" ");
						out.print(test.result[i][j]);
						out.print(" ");
					}
					System.out.println();
					out.println();
				}
				out.close();
			} catch (Exception e) {
				System.err.println("Hata: " + e.getMessage());
			}

		}
		if (answer == JOptionPane.NO_OPTION) {
			System.exit(0);
		}
		if (answer == JOptionPane.CANCEL_OPTION) {
			System.exit(0);
		}

	}

	// endpoints yani ba�lan�lacak olan server'�n IP si burada RANDOM olarak// atan�r
	public String getEndPoint() {

		String endpoints = "";
		int j = (int) (Math.random() * 3) + 1;
		if (j % 3 == 0) {
			endpoints = endPointOne;
		}
		if (j % 3 == 1) {
			endpoints = endPointTwo;
		}
		if (j % 3 == 2) {
			endpoints = endPointThree;
		}
		/*
		 * if (j % 4 == 3) { endpoints = endPointFour; }
		 */
		// System.out.println(endpoints);
		return endpoints;
	}

}
