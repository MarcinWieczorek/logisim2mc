package co.marcin.logisim2mc;

import co.marcin.logisim2mc.parser.LogisimFormatParser;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class Logisim2MCPlugin extends JavaPlugin {
	public Logger logger = getLogger();

	@Override
	public void onEnable() {
		getCommand("l2mc").setExecutor(this);
		logger.info("Enabled");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("l2mc")) {
			try {
				LogisimFormatParser parser = new LogisimFormatParser(new File(getDataFolder(), "2in1outNAND.circ"));
				parser.parse();
				parser.build(((Player) sender).getLocation());
//				sender.sendMessage("Done!");
			}
			catch(ParserConfigurationException | IOException | SAXException e) {
				e.printStackTrace();
			}

			return true;
		}

		return false;
	}

	@Override
	public void onDisable() {
		logger.info("Disabled");
	}
}
