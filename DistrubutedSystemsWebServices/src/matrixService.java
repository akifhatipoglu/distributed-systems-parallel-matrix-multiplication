
public class matrixService {
	
	//A = birinci  matrisinin sat�r� fonksiyona gelir 
	//B = ikinci matrisinin s�tunu fonksiyona gelir.
	//Fonksiyon birinci matrisin sat�r� ile ikinci matrisin sutununu carp�nca tek bir de�er geri d�ner. 
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
