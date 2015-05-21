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

package core;

import object.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application
{
	//LicenseCheck items
	private Stage licenseStage;
	private BorderPane lPrimaryPane;
	private TextArea licenseArea;
	private Button acceptLicenseButton;
	private Button denyLicenseButton;
	private CheckBox doNotDisplayLicense;
	private HBox buttonPane;
	
	//Game items
	private Timeline animation;
	private byte velocity;
	private Pane primaryPane;
	private Paddle paddle;
	private Ball ball;
	private int ballDirection = 0;
	private boolean goingUp = true,
					goingLeft = true;

	public void start(Stage primaryStage)
	{
		licenseCheck();

		primaryPane = new Pane();
		primaryPane.setStyle("-fx-border-color: red");
		paddle = new Paddle();
		paddle.setLayoutY(594 - paddle.getHeight());
		ball = new Ball(Color.RED);
		ball.setLayoutX(200);
		ball.setLayoutY(200);
		primaryPane.getChildren().addAll(paddle, ball);
		
		/*Circle test = new Circle(0, 0, 10);
		primaryPane.getChildren().add(test);*/



		animation = new Timeline(new KeyFrame(Duration.millis(3), e -> 
		{
			if(goingUp)
				ball.moveY(-1);
			else
				ball.moveY(1);
			
			if(goingLeft)
				ball.moveX(-1);
			else
				ball.moveX(1);
			
			if(ball.getLayoutX() < 0)
			{
				goingLeft = false;
				System.out.println("bounce");
			}
			else if((ball.getLayoutX() + ball.getWidth()) > primaryPane.getWidth())
			{
				goingLeft = true;
				System.out.println("bounce");
			}
			
			if(ball.getLayoutY() < 0)
			{
				goingUp = false;
				System.out.println("bounce");
			}
			else if((ball.getLayoutY() + ball.getHeight()) > primaryPane.getHeight())
			{
				goingUp = true;
				System.out.println("bounce");
			}
			
			//System.out.println(ball.getLayoutY());
			
			//if(paddle.contains(ball.getCenterX(), ball.getLayoutY() + ball.getHeight()))
			//if(paddle.intersects(ball.getBoundsInParent()))
			if(ball.intersects(paddle.getBoundsInParent()))
			{
				System.out.println("dang");
				//goingUp = true;
			}
			
			primaryPane.setOnKeyPressed(k -> 
			{
				switch (k.getCode())
				{
				case RIGHT:
					velocity = 2;
					break;
				case LEFT:
					velocity = -2;
					break;
				}
			});
			primaryPane.setOnKeyReleased(k -> 
			{
				if(k.getCode() == KeyCode.RIGHT || k.getCode() == KeyCode.LEFT)
					velocity = 0;
			});
			paddle.move(velocity);
			if(paddle.getLayoutX() < 0 || paddle.getLayoutX() > (842 - paddle.getWidth()))
				paddle.move(-velocity);
		}));
		animation.setCycleCount(Timeline.INDEFINITE);


		primaryStage.setScene(new Scene(primaryPane, 832, 600));
		primaryStage.setTitle("game");
		primaryStage.setResizable(false);
		acceptLicenseButton.setOnAction(e -> 
		{
			primaryStage.show();
			licenseStage.hide();
			animation.play();
			primaryPane.requestFocus();
			System.out.println(primaryPane.getWidth() + ", " + primaryPane.getHeight());
		});
	}
	
	/*public void moveBall()
	{
		switch (ballDirection)
		{
		case NW:
			ball.setLayoutX(ball.getLayoutX() - 1);
			ball.setLayoutY(ball.getLayoutY() - 1);
			break;
		case NE:
			ball.setLayoutX(ball.getLayoutX() + 1);
			ball.setLayoutY(ball.getLayoutY() - 1);
			break;
		case SE:
			ball.setLayoutX(ball.getLayoutX() + 1);
			ball.setLayoutY(ball.getLayoutY() + 1);
			break;
		case SW:
			ball.setLayoutX(ball.getLayoutX() - 1);
			ball.setLayoutY(ball.getLayoutY() + 1);
			break;
		}
	}*/
	
	public void licenseCheck()
	{
		licenseStage = new Stage();
		licenseStage.setResizable(false);

		lPrimaryPane = new BorderPane();
		lPrimaryPane.setPadding(new Insets(10));
		licenseArea = new TextArea();
		acceptLicenseButton = new Button("I accept");
		denyLicenseButton = new Button("I do not accept");
		doNotDisplayLicense = new CheckBox("Don't show again");
		buttonPane = new HBox(20);

		Scanner fileRead = null;
		do
		{
			try
			{
				fileRead = new Scanner(new File("Misc/LICENSE.txt"));
			} catch (FileNotFoundException e)
			{
				GenerateLicense.generateLicense();
				continue;
			}
		}while(false);

		licenseArea.setEditable(false);
		licenseArea.setWrapText(true);
		while(fileRead.hasNext())
			licenseArea.setText(licenseArea.getText() + "\n" + fileRead.nextLine());/**/

		lPrimaryPane.setCenter(licenseArea);
		buttonPane.getChildren().addAll(acceptLicenseButton, denyLicenseButton, doNotDisplayLicense);
		denyLicenseButton.setOnAction(e -> System.exit(0));
		buttonPane.setAlignment(Pos.CENTER);
		buttonPane.setPadding(new Insets(15));
		lPrimaryPane.setBottom(buttonPane);

		Scene lScene = new Scene(lPrimaryPane, 475, 700);
		licenseStage.setScene(lScene);
		licenseStage.setTitle("LICENSE");
		licenseStage.show();
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
