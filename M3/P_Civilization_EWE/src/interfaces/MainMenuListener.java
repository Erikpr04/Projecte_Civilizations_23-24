package interfaces;

import exceptions.MiSQLException;

public interface MainMenuListener {
    void startnewgame(String username, int photoIndex);
    void loadgame() throws MiSQLException;
    
}
