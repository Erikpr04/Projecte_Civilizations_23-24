package interfaces;

import exceptions.MiSQLException;
import exceptions.ResourceException;

public interface MainMenuListener {
    void startnewgame(String username, int photoIndex) throws MiSQLException, ResourceException;
    void loadgame() throws MiSQLException;
    
}
