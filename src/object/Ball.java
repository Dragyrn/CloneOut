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

import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Ball extends Pane
{
	ImageView ballGraphic;
	Blend colorize;
	
	public Ball(Color color)
	{
		super();
		//ballGraphic = new ImageView(new Image("/ball_base.png"));
		ballGraphic = new ImageView(new Image("/ball_base2.png"));
		colorize = new Blend(BlendMode.SRC_ATOP, new ImageInput(ballGraphic.getImage()), 
				new ColorInput(0, 0, ballGraphic.getImage().getWidth(), ballGraphic.getImage().getHeight(), 
				new Color(color.getRed(), color.getGreen(), color.getBlue(), 0.5)));
		ballGraphic.setEffect(colorize);
		this.getChildren().add(ballGraphic);
	}
	
	public void moveX(int x)
	{
		setLayoutX(getLayoutX() + x);
	}
	
	public void moveY(int y)
	{
		setLayoutY(getLayoutY() + y);
	}
	
	public double getCenterX()
	{
		return (getLayoutX() + ballGraphic.getFitWidth()) / 2;
	}
	
	public double getCenterY()
	{
		return (getLayoutY() + ballGraphic.getFitHeight()) / 2;
	}
	
	/*public Ball()
	{
		super();
		
	}/**/
}
