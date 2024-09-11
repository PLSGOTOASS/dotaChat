package ru.eblan;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("введи ip хоста (если введёшь неправильно мне похуй всё упадёт к хуям)");
        String hostIp = in.nextLine();

        try (Socket socket = new Socket(hostIp, 1337);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
             BufferedReader userReader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {

            String ip = socket.getInetAddress().getHostAddress();

            while (true) {
                int allChatInput;
                String allChatInputStr;

                System.out.println("что отправим этим уёбищам?");
                String quote = userReader.readLine();

                System.out.println("эщкере");

                if (quote == null || quote.isEmpty() || quote.isBlank()) {
                    quote = "я даун который не может написать сообщение отсосу бесплатно мой ip: " + ip;
                }

                writer.write(quote + "\n");
                writer.flush();

                System.out.println("0) команде 1) всем");
                allChatInputStr = userReader.readLine();
                System.out.println(allChatInputStr);

                try {
                    allChatInput = Integer.parseInt(allChatInputStr);
                } catch (NumberFormatException e) {
                    allChatInput = 1;
                }

                writer.write(allChatInput + "\n");
                writer.flush();

                String allMessage = allChatInput == 1 ? " всем" : " ";
                System.out.println("будет отправлено" + allMessage + " 2к червям: " + quote);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}