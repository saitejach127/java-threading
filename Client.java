import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException,InterruptedException {
        Socket s = new Socket("127.0.0.1",8080);
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        BufferedReader inp = new BufferedReader(new InputStreamReader(s.getInputStream()));
        for(int i=0;i<10;i++){
            out.writeBytes("Hello Client " + i+ "\n");
            String str = inp.readLine();
            System.out.println(str);
        }
        Thread.sleep(2000);
        System.out.println("waiting done");
        out.close();
        inp.close();
        s.close();
    }
}