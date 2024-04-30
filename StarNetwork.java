import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StarNetwork {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create a star network with 3 clients
        Star network = new Star(3);

        // Create client nodes
        ClientNode client1 = new ClientNode("Client1", network.server);
        ClientNode client2 = new ClientNode("Client2", network.server);
        ClientNode client3 = new ClientNode("Client3", network.server);

        // Add clients to the network
        network.insertNode(client1);
        network.insertNode(client2);
        network.insertNode(client3);

        while (true) {
            System.out.print("Enter client ID (Client1, Client2, or Client3) to send a message (or 'exit' to quit): ");
            String senderID = scanner.nextLine();
            if (senderID.equalsIgnoreCase("exit")) {
                break;
            }

            ClientNode sendingClient = null;
            if (senderID.equalsIgnoreCase("Client1")) {
                sendingClient = client1;
            } else if (senderID.equalsIgnoreCase("Client2")) {
                sendingClient = client2;
            } else if (senderID.equalsIgnoreCase("Client3")) {
                sendingClient = client3;
            } else {
                System.out.println("Error: Invalid client ID");
                continue;
            }

            System.out.print("Enter message: ");
            String message = scanner.nextLine();

            sendingClient.send(message);
        }

        scanner.close();
    }

    public static class ServerNode {
        private List<ClientNode> clients; // List of connected client nodes

        public ServerNode() {
            this.clients = new ArrayList<>();
        }

        public void addClient(ClientNode client) {
            this.clients.add(client);
        }

        public void removeClient(ClientNode client) {
            this.clients.remove(client);
            broadcastMessage(client.getClientID() + " left the chat");
        }

        private void broadcastMessage(String s) {
        }

        public void broadcastMessage(String message, ClientNode sender) {
            for (ClientNode client : clients) {
                if (client != sender) {
                    client.receive(message, sender.getClientID());
                }
            }
        }
    }

    public static class ClientNode {
        private final String clientID;
        private ServerNode server;

        public ClientNode(String clientID, ServerNode server) {
            this.clientID = clientID;
            this.server = server;
            server.addClient(this);
        }

        public String getClientID() {
            return clientID;
        }

        public void send(String message) {
            server.broadcastMessage(message, this);
        }

        public void receive(String message, String senderID) {
            System.out.println("Message from " + senderID + ": " + message);
        }
    }

    public static class Star {
        private ServerNode server;

        public Star(int numClients) {
            this.server = new ServerNode();
        }

        public void insertNode(ClientNode client) {
            server.addClient(client);
        }
    }
}




