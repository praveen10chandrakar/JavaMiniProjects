package com.Networking.SerializationDeserialization;


import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

class FileRead implements Serializable {
    private StringBuffer fileContent = new StringBuffer("");

    FileRead(String fileName) {
        try {
            String path = System.getProperty("user.dir") + "\\src\\com\\company\\";
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path + fileName)));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                fileContent.append("\n" + line);
            }
        } catch (FileNotFoundException f) {
            System.out.println(f);
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public String getFileContent() {
        return fileContent.toString();
    }
}

/*
Serializing of file and sending the packet through UDP.
*/
class UDPSerialize implements Serializable{

    public static void main(String[] args) {
        String fileName = "Demo.csv";
        FileRead read = new FileRead(fileName);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try{
            DatagramSocket socket = new DatagramSocket();
            ObjectOutputStream outputStream = new ObjectOutputStream(baos);
            outputStream.writeObject(read);
            InetAddress address = InetAddress.getLocalHost();

            byte [] buffer = baos.toByteArray();
            System.out.println("Before Serialization...");
            System.out.println(read.getFileContent());
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 8080);
            socket.send(packet);
        } catch (IOException e){
            System.out.println(e);
        }
    }
}

/*
Receiving the packet through UDP and deserializing of packet to retrieve original file contents .
*/
class UDPDeserialize {
    public static void main(String[] args) {
        try{
            DatagramSocket socket = new DatagramSocket(8080);
            byte [] buffer = new byte[2000];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
            ObjectInputStream inputStream = new ObjectInputStream(bais);
            FileRead obj = (FileRead)inputStream.readObject();
            System.out.println("After Deserialization..");
            System.out.println(obj.getFileContent());

        } catch (SocketException e){
            System.out.println( e);
        } catch (IOException e){
            System.out.println(e);
        } catch (ClassNotFoundException e){
            System.out.println(e);
        }
    }
}


