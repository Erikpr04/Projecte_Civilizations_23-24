package interfaces;

import exceptions.MiSQLException;

public interface MainMenuListener {
    void startnewgame(String username, int photoIndex) throws MiSQLException;
    void loadgame() throws MiSQLException;
    
}
