package day04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.*;

public class ThreadedServerMain {
    public static void main(String[] args) throws IOException{

        // create a thread pool with 2 threads
        ExecutorService thrPool = Executors.newFixedThreadPool(2);

        String threadName = Thread.currentThread().getName();

        // set the default port to 3000
        int port = 3000;
        if (args.length > 0){
            port = Integer.parseInt(args[0]);
        }

        // create a server port, using TCP
        ServerSocket server = new ServerSocket(port);

        while (true){
            // wait for an incoming connection
            System.out.printf("[%s] Waiting for connection on port %d\n", threadName, port);
            // socket is created when server accepts a connection
            Socket sock = server.accept(); // only need 1 accept to generate multiple sockets
            // will not go to next line unless there is a client connection

            System.out.println("Got a new connection");
        
            // create the work
            // instantiate a new Runnable, Runnable should be self contained
            ClientHandler handler = new ClientHandler(sock);
            // pass the work to the worker
            // dispatch the Runnable to the thread pool -- will start executing if there are free thread
            thrPool.submit(handler);
            // main thread returns to server.accept() to wait for new connection

        }
    }
}