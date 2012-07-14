package items.food;


	import java.awt.Toolkit;

	public class MushroomBad extends Food{
		
		
		public MushroomBad(){
			zvalue=66;
			picX=606;
			picY=445;
			String uri="./res/pic/tileset01.png";
			pic= Toolkit.getDefaultToolkit().getImage(uri);
			
		}
	}
