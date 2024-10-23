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
import java.net.*;
import java.util.Date;

// work for a thread to perform
public class ClientHandler implements Runnable {

    // cannot change socket
    private final Socket sock;

    public ClientHandler(Socket s) {
        sock = s;
    }

    // entry point for the thread
    @Override
    public void run() {

        String threadName = Thread.currentThread().getName();

        try{
            // get the input stream
            InputStream is = sock.getInputStream();
            Reader reader = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(reader);

            // get the output stream
            OutputStream os = sock.getOutputStream();
            Writer writer = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(writer);

            // read the input from client
            String fromClient = br.readLine();

            // process the data
            System.out.printf(">>> CLIENT: %s\n", fromClient);
            fromClient = (new Date()).toString() + " " + fromClient.toUpperCase();

            // write result back to client
            bw.write(fromClient);
            bw.newLine();
            bw.flush();
            os.flush();

            os.close();
            is.close();
            sock.close();
        } catch (IOException ex){
            // exception handler
            ex.printStackTrace();
        }
    }

}
