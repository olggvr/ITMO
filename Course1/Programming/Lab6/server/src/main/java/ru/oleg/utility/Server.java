package ru.oleg.utility;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.oleg.network.Request;
import ru.oleg.network.Response;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;

public class Server {

    private final int port;
    private final Printable console;
    private final RequestHandler requestHandler;

    static final Logger serverLogger = LogManager.getLogger(Server.class);


    public Server(int port, RequestHandler handler) {
        this.port = port;
        this.console = new PrintConsole();
        this.requestHandler = handler;
    }


    public void run() {
        try {
            DatagramPacket receivePacket;
            DatagramSocket serverSocket = new DatagramSocket(port);
            serverLogger.info("Сервер запущен на порту " + port);


            while (true) {

                ByteBuffer receivingBuffer = ByteBuffer.allocate(65_536);

                receivePacket = new DatagramPacket(receivingBuffer.array(), receivingBuffer.capacity());

                serverSocket.receive(receivePacket);
                for (int i = 0; i < receivingBuffer.capacity(); i++) {
                    if (receivingBuffer.get(i) == 0 && receivingBuffer.get(i + 1) == 0 && receivingBuffer.get(i + 2) == 0) {
                        receivePacket.setLength(i);
                        break;
                    }
                }
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();


                ByteArrayInputStream byteStream = new ByteArrayInputStream(receivePacket.getData());
                ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(byteStream));


                try {
                    Request userRequest = (Request) is.readObject();


                    Response responseToUser = requestHandler.handle(userRequest);

                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    ObjectOutputStream os = new ObjectOutputStream(outputStream);
                    os.writeObject(responseToUser);
                    os.flush();

                    ByteBuffer responseData = ByteBuffer.wrap(outputStream.toByteArray());


                    DatagramPacket sendPacket = new DatagramPacket(responseData.array(), responseData.capacity(), clientAddress, clientPort);

                    serverSocket.send(sendPacket);


                    serverLogger.info("Отправлен ответ клиенту: " + responseToUser);


                    os.close();
                    is.close();
                } catch (EOFException e) {
                    Response responseToUser = requestHandler.bufferError();


                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    ObjectOutputStream os = new ObjectOutputStream(outputStream);
                    os.writeObject(responseToUser);
                    os.flush();

                    ByteBuffer responseData = ByteBuffer.wrap(outputStream.toByteArray());


                    DatagramPacket sendPacket = new DatagramPacket(responseData.array(), responseData.capacity(), clientAddress, clientPort);

                    serverSocket.send(sendPacket);


                    serverLogger.info("Отправлен ответ клиенту: " + responseToUser);


                    os.close();
                    is.close();


                    serverLogger.error("Данные не влезают в буфер на сервере");
                }
            }
        } catch (IOException | ClassNotFoundException e) {

            console.printError("Произошла ошибка: " + e.getMessage());

            serverLogger.error("Произошла ошибка: " + e.getMessage());
        }
    }
}
