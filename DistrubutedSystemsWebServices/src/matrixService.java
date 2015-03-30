
public class matrixService {
	
	//A = birinci  matrisinin satýrý fonksiyona gelir 
	//B = ikinci matrisinin sütunu fonksiyona gelir.
	//Fonksiyon birinci matrisin satýrý ile ikinci matrisin sutununu carpýnca tek bir deðer geri döner. 
	public  int localMatrixMultiply(int[] A, int[] B, int matrisBoyutu) {
		int multipliedMatrix = 0;
            for (int j=0; j<matrisBoyutu; j++)
            {
            	multipliedMatrix = multipliedMatrix + (A[j] * B[j]);
            	System.out.println("A[j]= "+A[j]+" B[j]= "+B[j]+" (A[j] * B[j]):"+ (A[j] * B[j]));
            }
        return multipliedMatrix;
    }
	
}
