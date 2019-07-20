package co.marcin.logisim2mc.parser;

import org.bukkit.Material;
import org.bukkit.block.data.type.Switch;

public class PinIn extends ComplexElement {
	public PinIn(int x, int y) {
		super(x, y);

		BlockData leverBD = new BlockData(0, 0, 1, Material.LEVER);
		leverBD.data = Material.LEVER.createBlockData();
		((Switch) leverBD.data).setFace(Switch.Face.FLOOR);

		blockData.add(leverBD);
		blockData.add(new BlockData(0, 0, 0, Material.WHITE_WOOL));
	}
}
