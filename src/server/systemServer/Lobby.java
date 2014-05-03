/*
 * All source code is the work of Clinton Jeffery's Spring 2014 Software Engineering 
 * class at the University of Idaho consisting of the following members:
 * Brown, Clifford, Drage, Drew, Flake, Fuhrman, Goes, Goetsche, Higley,
 * Jaszkowiak, Klingenberg, Pearhill, Sheppard, Simon, Wang, Westrope, Zhang
 */
package systemServer;

import java.util.ArrayList;
import java.util.List;

/**
 * The Infamous Lobby Class
 */
public class Lobby {

    // TODO: private?
    protected List<ClientObject> lobbyClients;
    final private Integer lobbyID; // TODO: this should probably be used
    final private String name; // TODO: Ability to change lobby name?

    // Client who's turn it currently is
    protected ClientObject current;
    
    protected boolean gameStarted;

    /**
     * Lobby Constructor
     *
     * @param name    Name of the lobby being created
     * @param lobbyID
     */
    public Lobby( String name, Integer lobbyID ) {
        this.lobbyClients = new ArrayList<>( 0 );
        this.name = name;
        this.lobbyID = lobbyID;
        this.gameStarted = false;
    }

    /**
     *
     * @param handle
     *
     * @return
     */
    protected boolean isInLobby( String handle ) {
        for ( ClientObject client : lobbyClients ) {
            if ( client.getHandle().equals( handle ) ) {
                return true;
            }
        }
        return false;
    }

    protected void lobbyNotification( String notification ) {
        sendToEntireLobby( Flag.NOTIFICATION, Tag.LOBBY, null, notification );
    }

    protected void pokeEntireLobby( Flag flag, Tag tag ) {
        sendToEntireLobby( flag, tag, null, (Object[]) null );
    }

    /**
     *
     * @param flag
     * @param tag
     * @param sender
     * @param message
     */
    protected void sendToEntireLobby( Flag flag, Tag tag, String sender, Object... message ) {
        for ( ClientObject client : lobbyClients ) {
            client.send( flag, tag, sender != null ? sender : "", MessagePhoenix.
                    packMessageContents( message ) );
        }
    }

    /**
     *
     * @return
     */
    protected List<String> getUserNames() {
        List<String> handles = new ArrayList<>( 0 );
        for ( ClientObject client : lobbyClients ) {
            handles.add( client.getHandle() );
        }
        return handles;
    }

    /**
     *
     * @param client
     */
    public void join( ClientObject client ) {
        lobbyClients.add( client );
        client.setCurrentLobby( this );
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public Integer getLobbyID() {
        return lobbyID;
    }

    /**
     *
     * @param client
     */
    protected void leaveLobby( ClientObject client ) {
        if ( client != null && isInLobby( client.getHandle() ) ) {
            lobbyClients.remove( client );
        }
    }

    /**
     *
     */
    public void beginGame() {
        if(!gameStarted) {
            //start with the first player in list:
            current = lobbyClients.get( 0 );
            sendToEntireLobby( Flag.GAME, Tag.NEXT_TURN_INFO, current.getHandle(), current.
                    getClientID() ); // What does this do?
            gameStarted = true;
        }
        else {
            System.out.println("Game already started");
        }
    }

    /**
     * Moves the game turn in a "cycle" using the lobbyClients list
     */
    public void advanceGameTurn() {
        //TODO: Set order of turn by rearranging the order of the lobbyClient list
        int nextIndex = lobbyClients.indexOf( current ) + 1;
        if ( nextIndex == lobbyClients.size() ) {
            nextIndex = 0;
            //TODO: This means we have a finished an entire "game pass"! Do something special?
        }

        current = lobbyClients.get( nextIndex );
        sendToEntireLobby( Flag.GAME, Tag.NEXT_TURN_INFO, current.getHandle(), current.getClientID() );
    }
} // end class
