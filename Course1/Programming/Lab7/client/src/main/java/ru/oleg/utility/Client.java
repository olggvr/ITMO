package ru.oleg.utility;

import ru.oleg.commandLine.Printable;
import ru.oleg.network.Request;
import ru.oleg.network.Response;
import ru.oleg.network.ResponseStatus;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;

public class Client {
    private final String host;
    private final int port;
    private final Printable console;

    public Client(String host, int port, Printable console) {
        this.host = host;
        this.port = port;
        this.console=console;
    }

    public Response sendAndAskResponse(Request request) {
        while (true) {
            try {
                if (request.isEmpty()) return new Response(ResponseStatus.WRONG_ARGUMENTS, "Запрос пустой!");
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ObjectOutputStream serverWriter = new ObjectOutputStream(outputStream);
                serverWriter.writeObject(request);
                serverWriter.flush();
                ByteBuffer requestData = ByteBuffer.wrap(outputStream.toByteArray());
                InetAddress serverAddress = InetAddress.getByName(host);


                try {
                    ObjectInputStream serverReader = getObjectInputStream(requestData.array(), serverAddress);
                    Response response = (Response) serverReader.readObject();
                    serverWriter.close();
                    serverReader.close();
                    outputStream.close();
                    return response;
                } catch (EOFException e) {
                    console.printError("Данные не влезают в буфер на клиенте");
                    return new Response(ResponseStatus.ERROR, "Данные не влезают в буфер на клиенте");
                }
            } catch (ClassNotFoundException | IOException e) {
                console.printError("Ошибка при отправке запроса или получении ответа: " + e.getMessage());
                return new Response(ResponseStatus.ERROR, "Ошибка при отправке запроса или получении ответа");
            }
        }
    }


    private ObjectInputStream getObjectInputStream(byte[] requestData, InetAddress serverAddress) throws IOException {
        DatagramPacket receivePacket;
        try (DatagramSocket socket = new DatagramSocket()) {

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
