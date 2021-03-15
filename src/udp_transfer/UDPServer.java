/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udp_transfer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author Monica Ciuchetti
 */

public class UDPServer {
    DatagramSocket socket = null;

    public UDPServer() {
        try {
        socket = new DatagramSocket(1789);
        } catch (SocketException e) {
            System.err.println("Errore nel socket!");
    }
}

public void sendAndReceive() {
    try {
        byte[] incomingData = new byte[1024];

        while (true) {
            
            //Ricezione dello stream di byte
            DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
            socket.receive(incomingPacket);
            
            byte[] data = incomingPacket.getData();
            
            //Deserializzazione: dallo stream di byte all'oggetto
            ByteArrayInputStream in = new ByteArrayInputStream(data);
            ObjectInputStream is = new ObjectInputStream(in);
            
            try {
                Studente studente = (Studente) is.readObject();
                System.out.println("Ricevuto dal client oggetto Studente: \n" + studente + "\n");
            } catch (ClassNotFoundException e) {
                System.err.println("Classe Studente non definita");
            }
        
            //Recupero dei dati del mittente
            InetAddress IPAddress = incomingPacket.getAddress();
            int port = incomingPacket.getPort();
            
            //Invio della risposta
            String risposta = "Grazie del messaggio!";
            byte[] replyByte = risposta.getBytes();
            DatagramPacket replyPacket = new DatagramPacket(replyByte, replyByte.length, IPAddress, port);
            socket.send(replyPacket);
            
            Thread.sleep(1000);
            
            //Terminazione del processo Server
            //System.exit(0);
    }

    } catch (SocketException e) {
        System.err.println("Errore nel socket!");
    } catch (IOException i) {
        System.err.println("Errore di I/O!");
    } catch (InterruptedException e) {
        System.err.println("Errore nel thread sleeping!");
    }
}

public static void main(String[] args) {
        UDPServer server = new UDPServer();
        server.sendAndReceive();
    }
}
