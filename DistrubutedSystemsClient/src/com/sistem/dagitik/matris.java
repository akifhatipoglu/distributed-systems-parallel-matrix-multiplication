package com.sistem.dagitik;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;
import org.apache.ws.axis2.MatrixServiceStub;

public class matris {

	private int i;
	private int j;
	private int[] A;
	private int[] B;
	private int matrisBoyutu;
	private String endPoint;
	
	//				constructor
	//her threadin i�erisine konulmas� gerekli de�i�kenler constructor yard�m� ile set edilir.
	public matris(int i, int j, int A[], int B[], int matrisBoyutu, String endPoints) {
		this.i = i;
		this.j = j;
		this.A = A;
		this.B = B;
		this.matrisBoyutu = matrisBoyutu;
		this.endPoint = endPoints;
	}
	
	//Web servis e ba�lant�n�n a��l�p matris �arp�m�n�n yapt�r�ld��� nokta.
	public synchronized void dagitikSistem() throws InterruptedException {

		try {
			//matrix  web service'ine ba�lant� kurar
			MatrixServiceStub stub = new MatrixServiceStub(this.getEndPoint());

			//burada ise matrix servisinin local matrix multiply fonksiyonuna ba�lant� kurar.
			MatrixServiceStub.LocalMatrixMultiply multiply = new MatrixServiceStub.LocalMatrixMultiply();
			
			//baplant� kurdu�u servisin fonksunana gerekli de�erleri set eder
			multiply.setA(this.A);
			multiply.setB(this.B);
			multiply.setMatrisBoyutu(matrisBoyutu);

			//bu servisten cevap nesnesi olu�turur.
			MatrixServiceStub.LocalMatrixMultiplyResponse response = null;
			
			//gelen cevab� ait nesne olu�turur	
			response = stub.localMatrixMultiply(multiply);
			
			//gelen cevap nesnesine ait de�eri getirir.
			int myResult = response.get_return();
			
			//cevan� gerekli i ve j sonu� matrisine yazar 
			AnaSinif.result[i][j] = myResult;
			
			
			// ba�lanaca��m servisin sadece IP'sini substring ile ay�r�yor.
			String endpoits = this.getEndPoint().substring(this.getEndPoint().indexOf("http://")+7,this.getEndPoint().indexOf("8080")-1);
			//ekrana yazar 
			System.out.print("("+i+","+j+"):"+myResult + " "+endpoits+"  ");
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public int[] getA() {
		return A;
	}
	public int[] getB() {
		return B;
	}
	public int getMatrisBoyutu() {
		return matrisBoyutu;
	}
	public String getEndPoint() {
		return endPoint;
	}
}
