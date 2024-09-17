package ru.oleg.managers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.oleg.network.Request;
import ru.oleg.network.Response;
import ru.oleg.network.ResponseStatus;
import ru.oleg.utility.RequestHandler;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ConnectionManager implements Runnable {
    private final CommandManager commandManager;
    private final DatabaseManager databaseManager;


    static InetAddress clientAddress;
    static int clientPort;
    static DatagramSocket serverSocket;


    private static final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    private final int port;
    static final Logger connectionManagerLogger = LogManager.getLogger(ConnectionManager.class);

    public ConnectionManager(CommandManager commandManager, DatabaseManager databaseManager, int port) {
        this.commandManager = commandManager;
        this.databaseManager = databaseManager;
        this.port = port;


    }


    @Override
    public void run() {

        Request userRequest;
        Response responseToUser;
        try {
            DatagramPacket receivePacket;
            serverSocket = new DatagramSocket(port);
            connectionManagerLogger.info("Сервер запущен на порту " + port);

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
                clientAddress = receivePacket.getAddress();
                clientPort = receivePacket.getPort();


                ByteArrayInputStream byteStream = new ByteArrayInputStream(receivePacket.getData());
                ObjectInputStream clientReader = new ObjectInputStream(new BufferedInputStream(byteStream));

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ObjectOutputStream clientWriter = new ObjectOutputStream(outputStream);


                try {
                    userRequest = (Request) clientReader.readObject();

                    connectionManagerLogger.info("Получен запрос с командой " + userRequest.getCommandName(), userRequest);
                    if (!databaseManager.confirmUser(userRequest.getUser())
                            && !userRequest.getCommandName().equals("register")) {
                        connectionManagerLogger.info("Юзер не одобрен");

                        responseToUser = new Response(ResponseStatus.LOGIN_FAILED, "Неверный пользователь!");
                        submitNewResponse(new ConnectionManagerPool(responseToUser, clientWriter));
                    } else {

                        Request finalUserRequest = userRequest;
                        cachedThreadPool.submit(() -> {
                            try {
                                PoolManager.addNewFixedThreadPoolFuture(
                                        Executors.newSingleThreadExecutor().submit(
                                                new RequestHandler(commandManager, finalUserRequest, clientWriter)
                                        )
                                );
                            } catch (Exception e) {
                                connectionManagerLogger.error("Ошибка при обработке запроса: " + e.getMessage());
                            }
                        });
                    }
                } catch (EOFException e) {
                    responseToUser = new Response(ResponseStatus.ERROR, "Данные не влезают в буфер на сервере");
                    submitNewResponse(new ConnectionManagerPool(responseToUser, clientWriter));
                    connectionManagerLogger.error("Данные не влезают в буфер на сервере");

                }
            }


        } catch (ClassNotFoundException exception) {
            connectionManagerLogger.fatal("Произошла ошибка при чтении полученных данных!");
        } catch (CancellationException exception) {
            connectionManagerLogger.warn("При обработке запроса произошла ошибка многопоточности!");
        } catch (InvalidClassException | NotSerializableException exception) {
            connectionManagerLogger.error("Произошла ошибка при отправке данных на клиент!");
        } catch (IOException exception) {
        }
    }

    public static void submitNewResponse(ConnectionManagerPool connectionManagerPool) {
        cachedThreadPool.submit(() -> {
            try {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ObjectOutputStream os = new ObjectOutputStream(outputStream);
                os.writeObject(connectionManagerPool.response());
                os.flush();
                ByteBuffer responseData = ByteBuffer.wrap(outputStream.toByteArray());


                DatagramPacket sendPacket = new DatagramPacket(responseData.array(), responseData.capacity(), clientAddress, clientPort);

                serverSocket.send(sendPacket);

                os.close();

            } catch (IOException e) {
                connectionManagerLogger.error("Не удалось отправить ответ");
                connectionManagerLogger.debug(e);
            }
        });
    }
}


