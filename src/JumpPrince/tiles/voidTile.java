package JumpPrince.tiles;

import JumpPrince.graphics.Sprite;

public class voidTile extends Tile{

	public voidTile(Sprite sprite) {
		super(sprite);
	}
	
	public boolean solid() {
		return false;
	}
}

