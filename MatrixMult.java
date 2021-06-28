import java.time.Instant;
import java.util.Random;
import java.time.Duration;

public class MatrixMult {
    static int[][] a;
    static int[][] b;
    static int[][] result;

    class thread1 extends Thread {

        int k = a.length/2;

        @Override
        public void run() {
            for(int i=0;i<k;i++){
                for(int j=0;j<b[0].length;j++){
                    int temp = 0;
                    for(int t=0;t<b.length;t++){
                        temp+=a[i][t]*b[t][j];
                    }
                    result[i][j] = temp;
                }
            }
        }
    }

    class thread2 extends Thread {

        int k = a.length/2;

        @Override
        public void run() {
            for(int i=k;i<a.length;i++){
                for(int j=0;j<b[0].length;j++){
                    int temp = 0;
                    for(int t=0;t<b.length;t++){
                        temp+=a[i][t]*b[t][j];
                    }
                    result[i][j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.gc();
        Random rand = new Random();
        MatrixMult insta = new MatrixMult();
        int[][] matrix1 = new int[100][300];
        int[][] matrix2 = new int[300][100];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[0].length; j++) {
                matrix1[i][j] = 2 + rand.nextInt(1000);
            }
        }
        for (int i = 0; i < matrix2.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                matrix2[i][j] = 2 + rand.nextInt(1000);
            }
        }
        a = matrix1;
        b = matrix2;
        int rx,ry;
        rx = a.length;
        ry = b[0].length;
        result = new int[rx][ry];
        Instant start = Instant.now();
        thread1 obj1 = insta.new thread1();
        thread2 obj2 = insta.new thread2();
        obj1.start();
        obj2.start();
        obj1.join();
        obj2.join();
        Instant end = Instant.now();
        Duration span = Duration.between(start, end);
        System.out.println(span.toMillis());
        // for(int i=0;i<result.length;i++){
        //     for(int j=0;j<result[i].length;j++){
        //         System.out.print(result[i][j] + " ");
        //     }
        //     System.out.print("\n");
        // }
    }
}