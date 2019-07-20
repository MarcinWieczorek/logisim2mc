package co.marcin.logisim2mc.parser;

import org.bukkit.Material;

public class PinOut extends ComplexElement {
	public PinOut(int x, int y) {
		super(x, y);

		this.blockData.add(new BlockData(0, 0, 0, Material.REDSTONE_LAMP));
	}
}
