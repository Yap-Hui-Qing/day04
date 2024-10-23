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

public class ServerMain {
    public static void main(String[] args) throws IOException{

        String threadName = Thread.currentThread().getName();

        // set the default port to 3000
        int port = 3000;
        if (args.length > 0){
            port = Integer.parseInt(args[0]);
        }

        // create a server port, using TCP
        ServerSocket server = new ServerSocket(port); //opens a port to accept connection

        while (true){
            // wait for an incoming connection
            System.out.printf("[%s] Waiting for connection on port %d\n", threadName, port);
            // socket is created when server accepts a connection
            // once connected, server socket will be connected to the client's socket endpoint
            Socket sock = server.accept(); // only need 1 accept to generate multiple sockets
            // will not go to next line unless there is a client connection (block until a connection arrives)

            System.out.println("Got a new connection");

            // do the reverse for server
            // get the input stream from the server socket endpoint
            InputStream is = sock.getInputStream();
            Reader reader = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(reader);

            // get the output stream
            OutputStream os = sock.getOutputStream();
            Writer writer = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(writer);

            // read the input from client
            String fromClient = br.readLine();

            System.out.printf(">>> CLIENT: %s\n", fromClient);

            // process the data
            fromClient = (new Date()).toString() + " " + fromClient.toUpperCase();

            // write result back to client
            bw.write(fromClient);
            bw.newLine();
            bw.flush();
            os.flush();

            os.close();
            is.close();
            // clean up by closing the socket
            sock.close();
        }
    }
}