package interfaces;

import classes.Civilization;

public interface BattleListener {
	
	Civilization getCV_Battle();
	void updatecv_after_battle(int[] wasteresources);

}
