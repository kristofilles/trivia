package com.adaptionsoft.games.trivia;

import static org.junit.Assert.*;

import com.adaptionsoft.games.trivia.runner.GameRunner;
import com.adaptionsoft.games.uglytrivia.Game;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.junit.Test;

import java.io.*;
import java.util.Random;

public class SomeTest {


	String getNthLine(ByteArrayOutputStream stream, int n)
	{
		String text = stream.toString();
		String lines[] = text.split("\\r?\\n");
		return lines[n];
	}

	@Test
	public void Given_a_new_game_with_one_player_When_rolls_a_number_Then_his_location_will_be_that_number() throws Exception {
		PrintStream stdout = System.out;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		System.setOut(new PrintStream(baos));

		Game game = new Game();
		game.add("player");

		game.roll(3);
		System.setOut(stdout);
		System.out.println(baos.toString());

		assertEquals('3', getNthLine(baos, 4).charAt(getNthLine(baos, 4).length()-1));
	}

	@Test
	public void asd() throws Exception {

		PrintStream stdout = System.out;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		System.setOut(new PrintStream(baos));

		for (int seed = 0; seed < 100; ++seed){

			GameRunner runner = new GameRunner();
			Random rand = new Random();
			rand.setSeed(seed);
			runner.runGame(rand);

			assertEquals(baos.toString(), fromFile(seed));
			baos.reset();
		}

	}

	/*@Test
	public void toFile() throws Exception {
		PrintStream stdout = System.out;


		for (int seed = 0; seed < 100; ++seed){

			FileOutputStream fos = new FileOutputStream(seed+".txt");
			System.setOut(new PrintStream(fos));

			GameRunner runner = new GameRunner();
			Random rand = new Random();
			rand.setSeed(seed);
			runner.runGame(rand);

			fos.close();
		}


		System.setOut(stdout);

	}*/
	private String fromFile(int seed) {
		try {
			File file = new File(seed+".txt");
			FileInputStream fis = new FileInputStream(file);
			byte[] data = new byte[(int) file.length()];
			fis.read(data);
			fis.close();
			return new String(data, "UTF-8");
		}catch (Exception e){}
		return "";
	}
}
