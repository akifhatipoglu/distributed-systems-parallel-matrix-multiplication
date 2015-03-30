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
	//her threadin içerisine konulmasý gerekli deðiþkenler constructor yardýmý ile set edilir.
	public matris(int i, int j, int A[], int B[], int matrisBoyutu, String endPoints) {
		this.i = i;
		this.j = j;
		this.A = A;
		this.B = B;
		this.matrisBoyutu = matrisBoyutu;
		this.endPoint = endPoints;
	}
	
	//Web servis e baðlantýnýn açýlýp matris çarpýmýnýn yaptýrýldýðý nokta.
	public synchronized void dagitikSistem() throws InterruptedException {

		try {
			//matrix  web service'ine baðlantý kurar
			MatrixServiceStub stub = new MatrixServiceStub(this.getEndPoint());

			//burada ise matrix servisinin local matrix multiply fonksiyonuna baðlantý kurar.
			MatrixServiceStub.LocalMatrixMultiply multiply = new MatrixServiceStub.LocalMatrixMultiply();
			
			//baplantý kurduðu servisin fonksunana gerekli deðerleri set eder
			multiply.setA(this.A);
			multiply.setB(this.B);
			multiply.setMatrisBoyutu(matrisBoyutu);

			//bu servisten cevap nesnesi oluþturur.
			MatrixServiceStub.LocalMatrixMultiplyResponse response = null;
			
			//gelen cevabý ait nesne oluþturur	
			response = stub.localMatrixMultiply(multiply);
			
			//gelen cevap nesnesine ait deðeri getirir.
			int myResult = response.get_return();
			
			//cevaný gerekli i ve j sonuç matrisine yazar 
			AnaSinif.result[i][j] = myResult;
			
			
			// baðlanacaðým servisin sadece IP'sini substring ile ayýrýyor.
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
