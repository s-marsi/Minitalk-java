import java.net.*;
import java.rmi.ServerException;
import java.util.Scanner;
import java.io.*;

public class Client{
    public static void main(String[] args) throws ServerException, IOException
    {
        int               port   = 1234;
        String            line   = null;
        String            host   = "localhost";
        Socket            socket;
        InputStream       socketInput;
        InputStreamReader socketReader;
        BufferedReader    socketBuffer;
        OutputStream      socketOutput;
        PrintWriter       socketWriter;
        Scanner           userInput;
        try {
            socket = new Socket(host, port);
            System.out.println("Client ready");
            socketInput = socket.getInputStream();
            socketReader = new InputStreamReader(socketInput);
            socketBuffer = new BufferedReader(socketReader);
            socketOutput = socket.getOutputStream();
            socketWriter = new PrintWriter(socketOutput, true);
            userInput = new Scanner(System.in);
            while (true)
            {
                System.out.print("Please enter a message to send: ");
                line = userInput.nextLine();
                socketWriter.println(line);
                if (line.equals("bye"))
                    break ;
                System.out.println("The message has been sent to the server.");
                line = socketBuffer.readLine();
                System.out.println("Client received the message: " + line);
                
            }
            System.out.println("Closing connection");
            socket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}