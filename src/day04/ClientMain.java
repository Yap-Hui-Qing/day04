package day04;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientMain {

    public static void main(String[] args) throws IOException{

        // set the default port to 3000
        // port we are connecting to
        int port = 3000; 
        if (args.length > 0){
            port = Integer.parseInt(args[0]);
        }

        // create a connection to the server
        // connect to the port on the server
        // localhost = 127.0.0.1
        System.out.println("Connecting to the server");
        // once connected, client socket will be connected to the server's socket endpoint
        Socket sock = new Socket("localhost", port);

        System.out.println("Connected!");

        Console cons = System.console();
        // write a message to the server
        // first to connect, first to gets processed
        String theMessage = cons.readLine("input: ");

        // get the output stream
        OutputStream os = sock.getOutputStream();
        Writer writer = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(writer);

        // GET THE INPUT STREAM
        InputStream is = sock.getInputStream();
        Reader reader = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(reader);

        // write the message out
        bw.write(theMessage);
        bw.newLine();
        bw.flush();
        os.flush();
        // do not close 

        // read the result from the server
        String fromServer = br.readLine();

        System.out.printf(">>> SERVER: %s\n", fromServer);

        os.close();
        is.close();
        // client closes the connection (server can close the connection as well)
        sock.close();
        
    }
    
}
