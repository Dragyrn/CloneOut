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
import java.util.Scanner;

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
import javafx.stage.Stage;

public class Main extends Application
{
	//LicenseCheck items
	Stage licenseStage;
	BorderPane lPrimaryPane;
	TextArea licenseArea;
	Button acceptLicenseButton;
	Button denyLicenseButton;
	CheckBox doNotDisplayLicense;
	HBox buttonPane;
	
	//Game items
	Pane primaryPane;
	Paddle paddle;

	public void start(Stage primaryStage)
	{
		licenseCheck();
		
		primaryPane = new Pane();
		paddle = new Paddle();
		primaryPane.getChildren().add(paddle);
		
		//primaryStage.setScene(new Scene(new Pane(new Button("game")), 832, 600));
		primaryStage.setScene(new Scene(primaryPane, 832, 600));
		primaryStage.setTitle("game");
		acceptLicenseButton.setOnAction(e -> 
		{
			primaryStage.show();
			licenseStage.hide();
		});
	}
	
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
