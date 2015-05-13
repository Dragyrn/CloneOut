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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application
{
	//private boolean licenseAccepted = false;
	//Scanner fileRead = new Scanner(new File("Misc/LICENSE"));
	private File license = new File("/CloneOut/Misc/LICENSE");
	private Scanner fileRead;
	
	public void start(Stage primaryStage)
	{
		{ //License check
			Stage licenseStage = new Stage();
			
			BorderPane primaryPane = new BorderPane();
			TextArea licenseArea = new TextArea();
			Button acceptButton = new Button("I accept these terms"), 
				   denyButton = new Button("I do not accept these terms");
			
			licLoop:
			do
			{
				try
				{
					fileRead = new Scanner(license);
				}
				catch(FileNotFoundException ex)
				{
					GenerateLicense.generateLicense();
					continue licLoop;
				}
			}while(false);
			
			//fileRead = new Scanner(license);
			licenseArea.setEditable(false);
			licenseArea.setWrapText(true);
			while(fileRead.hasNext())
				licenseArea.setText(licenseArea.getText() + "\n" + fileRead.nextLine());
		}
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
}
