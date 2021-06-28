import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class ClientThread extends Thread{

    Socket s;

    ClientThread(Socket s) {
        this.s = s;
    }

    public void run(){
        try{
            PrintStream out = new PrintStream(s.getOutputStream());
            BufferedReader inp = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String str;
            while(true){
                str = inp.readLine();
                System.out.println(str);
                if(str==null){
                    break;
                }
                out.println(str + "Server \n");
            }
            Thread.sleep(2000);
            System.out.println("waiting done");
            out.close();
            inp.close();
            s.close();
        } catch(IOException e){
            System.out.println(e);
        } catch (InterruptedException e){
            System.out.println(e);
        }

    }
}

class ServerThread extends Thread{
    public void run(){
        try{
            ServerSocket ss = new ServerSocket(8080);
            String str;
            while(true){
                Socket s = ss.accept();
                ClientThread obj = new ClientThread(s);
                Thread t = new Thread(obj);
                t.start();
            }
        } catch(IOException e){
            System.out.println(e);
        }
    }
}

class Server{
    public static void main(String[] args){
        ServerThread obj = new ServerThread();
        Thread t = new Thread(obj);
        t.start();
    }
}