package items.food;

import java.awt.Toolkit;

public class Paprica extends Food {
	public Paprica(){
		super();
		zvalue=45;
		picX=352;
		picY=128;
		with=64;
		String uri = "./res/pic/plants.png";
		pic = Toolkit.getDefaultToolkit().getImage(uri);
	}


}
