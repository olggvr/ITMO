package ru.oleg.utility;

import ru.oleg.commandLine.Printable;
import ru.oleg.network.Request;
import ru.oleg.network.Response;
import ru.oleg.network.ResponseStatus;


import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;

public class Client {
    private final String host;
    private final int port;
    private final Printable console;

    public Client(String host, int port, Printable console) {
        this.host = host;
        this.port = port;
        this.console = console;
    }

    public Response sendAndAskResponse(Request request) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(outputStream);
            os.writeObject(request);
            os.flush();

            ByteBuffer requestData = ByteBuffer.wrap(outputStream.toByteArray());
            InetAddress serverAddress = InetAddress.getByName(host);

            // Создание сокета без указания локального порта
            ObjectInputStream is = getObjectInputStream(requestData.array(), serverAddress);

            try {
                Response response = (Response) is.readObject();


                is.close();
                os.close();
                outputStream.close();

                return response;
            } catch (EOFException e) {
                console.printError("Данные не влезают в буфер на клиенте");
                return new Response(ResponseStatus.ERROR, "Данные не влезают в буфер на клиенте");
            }
        } catch (IOException | ClassNotFoundException e) {
//            console.printError("Ошибка при отправке запроса или получении ответа: " + e.getMessage());
            return new Response(ResponseStatus.ERROR, "Ошибка при отправке запроса или получении ответа");
        }
    }

    private ObjectInputStream getObjectInputStream(byte[] requestData, InetAddress serverAddress) throws IOException {
        DatagramPacket receivePacket;
        try (DatagramSocket socket = new DatagramSocket()) {

            socket.setSoTimeout(1000);

            DatagramPacket sendPacket = new DatagramPacket(requestData, requestData.length, serverAddress, port);
            socket.send(sendPacket);

            ByteBuffer receivingBuffer = ByteBuffer.allocate(65_536);
            receivePacket = new DatagramPacket(receivingBuffer.array(), receivingBuffer.capacity());

            socket.receive(receivePacket);

            for (int i = 0; i < receivingBuffer.capacity(); i++) {
                if (receivingBuffer.get(i) == 0 && receivingBuffer.get(i + 1) == 0 && receivingBuffer.get(i + 2) == 0) {
                    receivePacket.setLength(i);
                    break;
                }
            }
        }

        ByteArrayInputStream inputStream = new ByteArrayInputStream(receivePacket.getData());
        return new ObjectInputStream(new BufferedInputStream(inputStream));
    }

}
