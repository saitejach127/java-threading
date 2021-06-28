import java.time.*;
import java.util.Random;

public class MatrixClassic {
    public static void main(String[] args){
        System.gc();
        Random rand = new Random();
        int[][] matrix1 = new int[100][300];
        int[][] matrix2 = new int[300][100];
        for(int i=0;i<matrix1.length;i++){
            for(int j=0;j<matrix1[0].length;j++){
                matrix1[i][j] = rand.nextInt(1000);
            }
        }
        for(int i=0;i<matrix2.length;i++){
            for(int j=0;j<matrix2[0].length;j++){
                matrix2[i][j] = rand.nextInt(1000);
            }
        }
        int rx = matrix1.length;
        int ry = matrix2[0].length;
        int[][] result = new int[rx][ry];
        Instant start = Instant.now();
        for(int i=0;i<rx;i++){
            for(int j=0;j<ry;j++){
                for(int t=0;t<matrix1[0].length;t++){
                    result[i][j] = matrix1[i][t]*matrix2[t][j];
                }
            }
        }
        Instant end = Instant.now();
        Duration diff = Duration.between(start, end);
        System.out.println( diff.toMillis());
        // for(int i=0;i<rx;i++){
        //     for(int j=0;j<ry;j++){
        //         System.out.print(result[i][j] + " ");
        //     }
        //     System.out.print("\n");
        // }
        
    }
}