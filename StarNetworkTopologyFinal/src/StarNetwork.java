public class StarNetwork {
    public ServerNode serverNode;

    public StarNetwork(int port) {
        serverNode = new ServerNode(port);
    }

    public void startServer() {
        serverNode.startServer();
    }

    public void insertNode(String serverAddress, int port) {
        ClientNode clientNode = new ClientNode(serverAddress, port);
        Thread thread = new Thread(() -> {
            while (true) {
                String message = clientNode.receiveMessage();
                if (message != null) {
                    System.out.println("Received from server: " + message);
                }
            }
        });
        thread.start();
    }

    public static void main(String[] args) {
        int port = 3001;
        StarNetwork star = new StarNetwork(port);
        star.startServer();

        star.insertNode("localhost", port);

    }
}






