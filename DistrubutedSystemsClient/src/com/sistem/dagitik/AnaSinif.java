package com.sistem.dagitik;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class AnaSinif {
	//EndPoints:Baðlanýlacak olan serverlar
	//Baðlanýlacak olan web servisin IP leri uzantýlarý
	public String endPointOne = "http://30.10.22.64:8080/DistrubutedSystemsWebServices/services/matrixService.matrixServiceHttpEndpoint/";
	public String endPointTwo = "http://30.10.22.62:8080/DistrubutedSystemsWebServices/services/matrixService.matrixServiceHttpEndpoint/";
	public String endPointThree = "http://30.10.22.65:8080/DistrubutedSystemsWebServices/services/matrixService.matrixServiceHttpEndpoint/";
	
	//En sonda oluþan sonuc matrisi: çarpýmýn sonucu
	public static int[][] result;

	public static void main(String[] args) {

		AnaSinif test = new AnaSinif();
		
		// Çarpýmý yapýlacak matrisin boyutunu giriniz:
		int matrisBoyutu = Integer.parseInt(JOptionPane.showInputDialog(null,
				"Çarpýmý yapýlacak matrisin boyutunu giriniz: ",
				"Matris Çarpýmý", JOptionPane.INFORMATION_MESSAGE));

		//matrisin baþlatýlmasý için gerekli sorgu
		int answer = JOptionPane.showConfirmDialog(null,
				"Matris çarpýmý baþlasýn mý ?");

		if (answer == JOptionPane.YES_OPTION) {

			int A[][] = new int[matrisBoyutu][matrisBoyutu];
			int B[][] = new int[matrisBoyutu][matrisBoyutu];
			
			// matrislerin içerisine random sayýlar yerleþtiriyor [1-10]
			for (int i = 0; i < matrisBoyutu; i++) {
				for (int j = 0; j < matrisBoyutu; j++) {
					A[i][j] = (int) (Math.random() * 10) + 1;
					B[i][j] = (int) (Math.random() * 10) + 1;
				}
			}

			// Burada A ve B matrisi hem ekranda (consolda) hemnde input txt ye yazýyor.
			try {
				// System.out.println ekrana yazýyor
				// out.println dosyaya yazýyor
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

			// Matris çarpýmýnýn sonucu yani  result onu hazýrlýyor
			test.result = new int[matrisBoyutu][matrisBoyutu];

			// Ayýrma iþleminin baþlangýcý : sistemden saati alýyor
			long startTime = System.currentTimeMillis();

			/*
			 * Ana matris iþlemi baþlar -matris ayýrma -thread ler ile
			 * matrisleri ilgili endpointslerle birlikte (baþka pc lerdeki web
			 * servisimiz) oluþturma -oluþan thread i baþlatma -oluþan
			 * threadleri havuz gibi bir yerde sýraya sokma
			 */
			for (int i = 0; i < matrisBoyutu; i++) {
				for (int j = 0; j < matrisBoyutu; j++) {
					int[] columnB = new int[matrisBoyutu];
					
					// B matrisinin sutununu alma
					for (int j2 = 0; j2 < matrisBoyutu; j2++) {
						columnB[j2] = B[j2][j];
					}
					
					try {
						// her matris sütün ve satýrý için yeni bir endpoint
						// belirler
						String endpoints = test.getEndPoint();

						matris m = new matris(i, j, A[i], columnB,matrisBoyutu, endpoints);

						// her thread için koþabilecekleri sýnýf oluþturulur.
						ThreadRunnable d = new ThreadRunnable(m);


						// her makina baþýna bir thread ile farklý bir deneyim
						if (endpoints.contains("30.10.22.64")) {
							
							//her thread koþucaðý runnable sýnýfý alýr.
							Thread t1 = new Thread(d);
							//koþmaya baþlar
							t1.start();
							// ilk koþanýn ilk sýraya girmesini saðlar
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
			long estimatedTime = endTime - startTime; // Geçen süreyi milisaniye
														// cinsinden elde
														// ediyoruz
			double seconds = (double) estimatedTime / 1000;
			//bu kýsýmda geçen süre saniye cinsinden hesaplanýr.

			try {
				
				//Bu kýsýmda matris çarpýmýnýn sonucu hem txt dosyasýna yazýlýr hem de ekrana yazýlýr.
				// System.out.println ekrana yazýyor
				// out.println dosyaya yazýyor
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

	// endpoints yani baðlanýlacak olan server'ýn IP si burada RANDOM olarak// atanýr
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
