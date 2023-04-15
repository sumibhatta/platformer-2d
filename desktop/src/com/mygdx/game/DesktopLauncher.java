package com.mygdx.game;



import static com.mygdx.game.MyGdxGame.SCALE;
import static com.mygdx.game.MyGdxGame.V_HEIGHT;
import static com.mygdx.game.MyGdxGame.V_WIDTH;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setIdleFPS(60);
		config.useVsync(true);
		config.setWindowedMode(V_WIDTH*SCALE,V_HEIGHT*SCALE);
		config.setTitle("Journey Through Pages");
		config.setWindowIcon(Files.FileType.Internal,"assets/asset/icon.png");
		new Lwjgl3Application(new MyGdxGame(), config);
	}
}
