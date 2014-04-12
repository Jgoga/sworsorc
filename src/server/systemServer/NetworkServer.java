package systemServer;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NetworkServer {

    private static List<ClientObject> clientObjects; //"Packaged sockets"
    protected static List<Lobby> lobbies;

    private static final int DEFAULT_PORT = 25565;
    private static final String DEFAULT_IP = "76.178.139.129";

    public static boolean canCreateNewLobby(String name) {
        //check if lobby name is unique.
        //we might have to add other conditions?

        for (Lobby lobby : lobbies) {
            if (lobby.name.equals(name)) {
                return false;
            }
        }
        return true;
    }

    public static void createNewLobby(String name) {
        //TODO: Enforce unique names?
        Lobby lobby = new Lobby(name);
        lobbies.add(lobby);
    }

    // TODO: There is a netbeans warning "exporting non-public type through public api"
    // Meaning ClientObject is private, but method is public, so paramter can't be seen by caller
    // Might want to reorganize either as subclasses of server, or into seperate .java files?
    public static void joinLobby(String lobbyName, ClientObject client) {
        for (Lobby l : lobbies) {
            if (l.name.equals(lobbyName)) {
                l.join(client);
                return;
            }
        }
        //If we're here, we didn't find the name!
        //TODO: make a canJoinLobby() function or request/deny messages
        //(e.g. what if the game is in session?)
        System.err.println("Error: Couldn't find lobby: " + lobbyName + " to join.");
    }

    public static void leaveLobby(ClientObject client) {

        for (Lobby l : lobbies) {
            if (l.lobbyClients.contains(client)) {
                l.leave(client);
                if (l.lobbyClients.isEmpty()) {
                    //For now, just kill lobbies when everyone leaves
                    lobbies.remove(l);
                }
                return;
            }
        }
        //If we're here, we didn't find the name!
        System.err.println("Requested to leave lobby from client not in lobby");
    }

    public static List<String> getAllUserNames() {
        List<String> handles = new ArrayList<>();
        for (ClientObject obj : NetworkServer.clientObjects) {
            handles.add(obj.getHandle());
        }
        return handles;
    }

    /**
     * Forward the message to all ClientObjects, which will send to their Clients
     * @param message 
     */
    public static void sendToAllClients(List<String> message) {
        for (ClientObject client : clientObjects) {
            client.send(message);
        }
    }

    /**
     * clientObject will call this on a planned or unplanned disconnection
     * @param clientId 
     */
    public static void clientDisconnected(int clientId) {

        ClientObject dearlyDeparted = null;

        for (int i = 0; i < clientObjects.size(); i++) {
            if (clientObjects.get(i).clientID == clientId) {
                dearlyDeparted = clientObjects.get(i);
                break;
            }
        }

        leaveLobby(dearlyDeparted);
        clientObjects.remove(dearlyDeparted);
        sendToAllClients(MessageUtils.makeDisconnectAnnouncementMessage(dearlyDeparted.getHandle()));

    }

    public static void main(String args[]) {
        clientObjects = new ArrayList<>();
        lobbies = new ArrayList<>();

        System.out.println("Server starting. . .");

        System.out.println("Binding port " + DEFAULT_PORT + " . . .");

        try {
            System.out.println("Server started (" + InetAddress.getLocalHost() + ")");
        } catch (UnknownHostException e) {
            System.err.println("Error when starting server!\nException: " + e );
        }

        ServerSocket listen = null;
        try {
            listen = new ServerSocket(DEFAULT_PORT);
            //listen = new ServerSocket(DEFAULT_PORT, 0, InetAddress.getByName(DEFAULT_IP));
        } catch (IOException e) {
            System.err.println("Error : While creating ServerSocket\n" + e);
            System.exit(-1);
        }

        //Spins off new client connections:
        while (true) {
            try {
                System.err.println("Waiting for next client...");
                Socket socket = listen.accept(); //Get socket (blocking)

                //The constructor of ClientObject will create the new threads:
                clientObjects.add(new ClientObject(socket));
            } catch (IOException e) {
                System.err.println("Server failed to accept client!\nException: " + e );
                break;
            }
        }

    }

} // end NetworkServer