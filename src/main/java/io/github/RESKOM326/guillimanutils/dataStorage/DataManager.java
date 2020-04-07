package io.github.RESKOM326.guillimanutils.dataStorage;

public interface DataManager {
	/**
	 * Initializes the database or file in case this is the first time using it
	 * (creates the tables needed or the file itself)
	 * @return true if no errors found during the process
	 */
	public boolean initialize();
	
	/**
	 * Adds this player to the list of "ascended" players of the server
	 * @param playerName
	 * @return true if this user was added to the database correctly
	 */
	public boolean addGod(PlayerDB player);
	
	/**
	 * Deletes this player from the list of "ascended" players of the server
	 * @param playerName
	 * @return true if this user was deleted from the database correctly
	 */
	public boolean quitGod(PlayerDB player);
}
