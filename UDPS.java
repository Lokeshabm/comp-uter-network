import java.net.*; 
public class UDPS {
     public static void main(String[] args) { 
        DatagramSocket socket = null; 
 
        try { 
            // Create a DatagramSocket at port 6788 
            socket = new DatagramSocket(6788); 
            System.out.println("Server started. Waiting for client messages..."); 
 
            byte[] buffer = new byte[1024]; 
 
            while (true) { 
                // Receive packet from client 
                DatagramPacket request = new DatagramPacket(buffer, buffer.length); 
                socket.receive(request); 
 
                // Extract message 
                String message = new String(request.getData(), 0, request.getLength()); 
                System.out.println("Received from client: " + message); 
 
                // Process and prepare reply 
                String replyMessage = message + " (server processed)"; 
                byte[] sendMsg = replyMessage.getBytes(); 
 
                // Send reply back to client 
                DatagramPacket reply = new DatagramPacket(  sendMsg,  sendMsg.length,request.getAddress(), request.getPort() 
                ); 
 
                socket.send(reply); 
                System.out.println("Reply sent to client."); 
            } 
 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } finally { 
            if (socket != null) 
                socket.close(); 
        } 
    } 
    
}
