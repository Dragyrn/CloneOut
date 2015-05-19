/**
 * 
 * CloneOut - A Breakout Clone - VBISD Final Project
 * Copyright (C) 2015 Dragyrn
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or any 
 * later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package object;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Paddle extends Pane
{
	ImageView paddleGraphic;
	
	
	public Paddle()
	{
		super();
		paddleGraphic = new ImageView(new Image("/paddle.png"));
		getChildren().add(paddleGraphic);
	}
	
	public void move(int n)
	{
		setLayoutX(getLayoutX() + n);
	}
}
