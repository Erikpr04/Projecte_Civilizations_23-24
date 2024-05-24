package interfaces;

import exceptions.MiSQLException;

public interface MainMenuListener {
    void startnewgame(String username, String photoIndex);
    void loadgame() throws MiSQLException;
    
}
