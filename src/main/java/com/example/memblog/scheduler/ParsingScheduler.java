package com.example.memblog.scheduler;

import com.example.memblog.services.MemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;

@Service
public class ParsingScheduler {

    @Autowired
    private MemeService memeService;

    private boolean check = false;

    @Scheduled(initialDelay = 3000, fixedRate = 120000)
    public void pingToStartParser(){
        if(check) {
            System.out.println("=");
            try {

                int serverPort = 9090;
                String address = "127.0.0.1";
                InetAddress ip = InetAddress.getByName(address);
                var socket = new Socket(ip, serverPort);
                var socketOut = socket.getOutputStream();
                var outputStream = new DataOutputStream(socketOut);
                System.out.println("work");
                outputStream.writeUTF(memeService.findLastImage());
                outputStream.flush();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

