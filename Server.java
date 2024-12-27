import java.net.*;
import java.rmi.ServerException;
import java.util.Scanner;
import java.io.*;

public class Server {
    public static void main(String[] args) throws ServerException, IOException
    {
        int               port   = 1234;
        String            line   = null;
        ServerSocket      server = null;
        Socket            socket = null;
        InputStream       socketInput;
        InputStreamReader socketReader;
        BufferedReader    socketBuffer;
        OutputStream      socketOutput;
        PrintWriter       socketWriter;
    
        try {
            server = new ServerSocket(port);
            System.out.println("Server is running");
            System.out.println("Server waiting for a connection...");
            socket = server.accept();
            System.out.println("Client accepted" + socket.getRemoteSocketAddress());
            socketInput = socket.getInputStream();
            socketReader = new InputStreamReader(socketInput);
            socketBuffer = new BufferedReader(socketReader);
            socketOutput = socket.getOutputStream();
            socketWriter = new PrintWriter(socketOutput, true);
            while (true)
            {
                line = socketBuffer.readLine();
                if (line.equals("bye"))
                break ;
                System.out.println("Server received the message: " + line);
                socketWriter.println(line.toUpperCase());
                System.out.println("The message has been sent back to the client.");

            }
            System.out.println("Closing connection");
            socket.close();
            server.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
