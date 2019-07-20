package co.marcin.logisim2mc.parser;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class Wire extends AbstractElement {
	private final int xTo;
	private final int yTo;

	public Wire(int xFrom, int yFrom, int xTo, int yTo) {
		super(xFrom, yFrom);
		this.xTo = xTo;
		this.yTo = yTo;
	}

	@Override
	public void build(Location location) {
		location = location.clone().add(this.x, 0, this.y);

		// Vertical
		if(this.yTo == this.y) {
			for(int xLoop = 0; xLoop <= Math.abs(this.xTo - this.x); xLoop++) {
				Block block = location.clone().add(xLoop, 0, 0).getBlock();
				block.setType(Material.REDSTONE_WIRE);
			}
		}
	}

	@Override
	public String toString() {
		return String.format("Wire: (%d, %d) -> (%d, %d)", x, y, xTo, yTo);
	}
}
