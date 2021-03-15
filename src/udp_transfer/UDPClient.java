/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udp_transfer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;

/**
 *
 * @author Monica Ciuchetti
 */
public class UDPClient {
    DatagramSocket Socket;
    InetAddress IPAddress;
    
    public UDPClient() {
         try {
             Socket = new DatagramSocket();
             IPAddress = InetAddress.getByName("localhost");
         }
         catch (UnknownHostException e) {
            System.err.println("Errore DNS!");
        } catch (SocketException e) {
            System.err.println("Errore nel socket!");
        }
        
}

public void sendAndReceive() {
    try {

        byte[] incomingData = new byte[1024];
        
        Studente studente = new Studente(1, "Nome Cognome", "nome.cognome@avolta.pg.it");
        
        //Serializzazione: dall'oggetto allo stream di byte
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(outputStream);
        os.writeObject(studente);
        
        
        
        byte[] data = outputStream.toByteArray();
        
        //Invio dello stream di byte
        DatagramPacket sendPacket = new DatagramPacket(data, data.length, IPAddress, 1789);
        Socket.send(sendPacket);
        System.out.println("Messaggio spedito al Server");
        
        //Ricezione della risposta
        DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
        Socket.receive(incomingPacket);
        String risposta = new String(incomingPacket.getData());
        System.out.println("Risposta dal server: " + risposta);
        Thread.sleep(2000);

        } catch (UnknownHostException e) {
            System.err.println("Errore DNS!");
        } catch (SocketException e) {
            System.err.println("Errore nel socket!");
        } catch (IOException e) {
            System.err.println("Errore di I/O!");
        } catch (InterruptedException e) {
            System.err.println("Errore nel thread sleeping!");
    }
}

public static void main(String[] args) {
        UDPClient client = new UDPClient();
        client.sendAndReceive();
    }
}