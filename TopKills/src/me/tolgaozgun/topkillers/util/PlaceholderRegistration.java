package me.tolgaozgun.topkillers.util;
import org.bukkit.entity.Player;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.tolgaozgun.topkillers.PluginMain;

/**
 * This class will be registered through the register-method in the 
 * plugins onEnable-method.
 */
public class PlaceholderRegistration extends PlaceholderExpansion {

    private PluginMain plugin;

    /**
     * Since we register the expansion inside our own plugin, we
     * can simply use this method here to get an instance of our
     * plugin.
     *
     * @param plugin
     *        The instance of our plugin.
     */
    public PlaceholderRegistration(PluginMain plugin){
        this.plugin = plugin;
    }

    /**
     * Because this is an internal class,
     * you must override this method to let PlaceholderAPI know to not unregister your expansion class when
     * PlaceholderAPI is reloaded
     *
     * @return true to persist through reloads
     */
    @Override
    public boolean persist(){
        return true;
    }

    /**
     * Because this is a internal class, this check is not needed
     * and we can simply return {@code true}
     *
     * @return Always true since it's an internal class.
     */
    @Override
    public boolean canRegister(){
        return true;
    }

    /**
     * The name of the person who created this expansion should go here.
     * <br>For convienience do we return the author from the plugin.yml
     * 
     * @return The name of the author as a String.
     */
    @Override
    public String getAuthor(){
        return plugin.getDescription().getAuthors().toString();
    }

    /**
     * The placeholder identifier should go here.
     * <br>This is what tells PlaceholderAPI to call our onRequest 
     * method to obtain a value if a placeholder starts with our 
     * identifier.
     * <br>This must be unique and can not contain % or _
     *
     * @return The identifier in {@code %<identifier>_<value>%} as String.
     */
    @Override
    public String getIdentifier(){
        return "pvptop";
    }

    /**
     * This is the version of the expansion.
     * <br>You don't have to use numbers, since it is set as a String.
     *
     * For convienience do we return the version from the plugin.yml
     *
     * @return The version as a String.
     */
    @Override
    public String getVersion(){
        return plugin.getDescription().getVersion();
    }

    /**
     * This is the method called when a placeholder with our identifier 
     * is found and needs a value.
     * <br>We specify the value identifier in this method.
     * <br>Since version 2.9.1 can you use OfflinePlayers in your requests.
     *
     * @param  player
     *         A {@link org.bukkit.Player Player}.
     * @param  identifier
     *         A String containing the identifier/value.
     *
     * @return possibly-null String of the requested identifier.
     */
    @Override
    public String onPlaceholderRequest(Player player, String identifier){

        if(player == null){
            return "";
        }

        if(identifier.contains("killplayer")){
        	String count = identifier.substring(identifier.indexOf("_")+1).trim();
        	int number = Integer.parseInt(count)- 1;
        	String name = SortScoreboard.getKillRanking(number);
            return name;
        }
        

        if(identifier.contains("killcount")){
        	String count = identifier.substring(identifier.indexOf("_")+1).trim();
        	int number = Integer.parseInt(count)- 1;
        	
            return SortScoreboard.getKillCount(number)+"";
        }
        

        if(identifier.contains("deathplayer")){
        	String count = identifier.substring(identifier.indexOf("_")+1).trim();
        	int number = Integer.parseInt(count)- 1;
        	return SortScoreboard.getDeathRanking(number);
        }
        

        if(identifier.contains("deathcount")){
        	String count = identifier.substring(identifier.indexOf("_")+1).trim();
        	int number = Integer.parseInt(count)- 1;
            return SortScoreboard.getDeathCount(number)+"";
        } 
        

        if(identifier.contains("expplayer")){
        	String count = identifier.substring(identifier.indexOf("_")+1).trim();
        	int number = Integer.parseInt(count)- 1;
        	return SortScoreboard.getExpRanking(number);
        }
        

        if(identifier.contains("expcount")){
        	String count = identifier.substring(identifier.indexOf("_")+1).trim();
        	int number = Integer.parseInt(count)- 1;
            return SortScoreboard.getExpCount(number)+"";
        } 
        

        if(identifier.contains("levelplayer")){
        	String count = identifier.substring(identifier.indexOf("_")+1).trim();
        	int number = Integer.parseInt(count)- 1;
        	return SortScoreboard.getLevelRanking(number);
        }
        

        if(identifier.contains("levelcount")){
        	String count = identifier.substring(identifier.indexOf("_")+1).trim();
        	int number = Integer.parseInt(count)- 1;
            return SortScoreboard.getLevelCount(number)+"";
        } 
        

        if(identifier.contains("ratioplayer")){
        	String count = identifier.substring(identifier.indexOf("_")+1).trim();
        	int number = Integer.parseInt(count)- 1;
        	return SortScoreboard.getRatioRanking(number);
        }
        

        if(identifier.contains("ratiocount")){
        	String count = identifier.substring(identifier.indexOf("_")+1).trim();
        	int number = Integer.parseInt(count)+ 1;;
            return SortScoreboard.getRatioCount(number)+"";
        } 
        

 
        // We return null if an invalid placeholder (f.e. %someplugin_placeholder3%) 
        // was provided
        return null;
    }
}
