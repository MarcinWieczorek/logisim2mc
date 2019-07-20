package co.marcin.logisim2mc.parser;

import org.bukkit.Material;

public class NAND extends ComplexElement implements LogisimElement {
	// Layer 0
	// WS
	//  SWW
	// WS
	// Layer 1
	//  T
	//  W
	//  T

	public NAND(int x, int y) {
		super(x, y);

		originX = 4;
		originY = 1;

		// Layer 0
		blockData.add(new BlockData(0, 0, 0, Material.REDSTONE_WIRE)); // W

		blockData.add(new BlockData(0, 2, 0, Material.REDSTONE_WIRE)); // W
		blockData.add(new BlockData(1, 0, 0, Material.STONE));         //  S
		blockData.add(new BlockData(1, 1, 0, Material.STONE));         //  S
		blockData.add(new BlockData(1, 2, 0, Material.STONE));         //  S

		blockData.add(new BlockData(2, 1, 0, Material.REDSTONE_WIRE)); //   W

		// Layer 1
		blockData.add(new BlockData(1, 0, 1, Material.REDSTONE_TORCH)); // T
		blockData.add(new BlockData(1, 1, 1, Material.REDSTONE_WIRE));  // W
		blockData.add(new BlockData(1, 2, 1, Material.REDSTONE_TORCH)); // T
		blockData.add(new BlockData(3, 1, 0, Material.REDSTONE_WIRE)); //    W
	}

	@Override
	public String toString() {
		return String.format("NAND at (%d, %d)", x, y);
	}
}
