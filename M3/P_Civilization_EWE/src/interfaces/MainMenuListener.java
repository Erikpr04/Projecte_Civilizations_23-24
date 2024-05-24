package interfaces;

import exceptions.MiSQLException;

public interface MainMenuListener {
    void startnewgame(String username, String photoIndex) throws MiSQLException;
    void loadgame() throws MiSQLException;
    
}
