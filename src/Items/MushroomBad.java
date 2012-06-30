package Items;


	import java.awt.Graphics;
	import java.awt.Image;
	import java.awt.Toolkit;
	import java.awt.image.ImageObserver;

	import domain.backGroundTile.Dirt;
	import domain.backGroundTile.Grass;
	import domain.backGroundTile.Water;

	public class MushroomBad extends Item{
		private static Image pic;
		
		public MushroomBad(){
			yvalure=66;
			canNotBeWhit.add(new Water());
			haveToBeWhit.add(new Grass());
			String uri="./res/pic/tileset01.png";
			if(pic==null)
				pic= Toolkit.getDefaultToolkit().getImage(uri);
			
		}
		 public void drawItSelf( Graphics g,ImageObserver io, int x,int y,float zoom){
				int picX=606;
				int picY=445;
				g.drawImage(pic, x,y,x+(int) (32*zoom),y+(int)(32*zoom),picX, picY, picX+32, picY+32, io);
		 }
		@Override
		public Image getImage() {
			// TODO Auto-generated method stub
			return null;
		}
	}
