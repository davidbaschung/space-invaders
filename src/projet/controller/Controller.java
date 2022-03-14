package projet.controller;

import projet.model.SpaceInvaders;
import projet.model.spacecharacters.Player;
import projet.view.WorldGraphics;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller {

	static SpaceInvaders spaceInvaders;

	public Controller(SpaceInvaders spaceInvaders) {
		this.spaceInvaders = spaceInvaders;
		WorldGraphics worldGraphics = WorldGraphics.getWorldGraphics();
		worldGraphics.getWGFrame().getContentPane().addKeyListener(new PlayerListener(spaceInvaders.player)); //TODO
		worldGraphics.getWGFrame().getContentPane().setFocusable(true);
		worldGraphics.getWGFrame().getContentPane().requestFocusInWindow();
//		worldGraphics.getWGFrame().addKeyListener(new KeyAdapter()b {
//													  @Override
//													  public void keyTyped(KeyEvent e) {
//														  super.keyTyped(e);
//													  }
//												  });
	}

	class PlayerListener implements KeyListener {
		Player player;

		public PlayerListener(Player player) {
			this.player = player;
		}

		@Override
		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void keyPressed(KeyEvent e) {
			int code = e.getKeyCode();
			if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A)
				player.moveLeft();
			if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D)
				player.moveRight();
			if (code == KeyEvent.VK_SPACE || code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
				player.shoot();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			int code = e.getKeyCode();
			if (code==KeyEvent.VK_LEFT || code==KeyEvent.VK_RIGHT || code == KeyEvent.VK_A || code == KeyEvent.VK_D ) {
				player.stop();
			}
		}
	}
}
