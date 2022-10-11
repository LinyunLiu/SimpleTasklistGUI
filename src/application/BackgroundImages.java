// AUTHOR: LINYUN LIU
// DATE: MARCH 15th, 2021

package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class BackgroundImages {

	public static Background getBackground(String url) throws FileNotFoundException {
		FileInputStream file  = new FileInputStream(url);
		Image image = new Image(file);
		BackgroundImage bgimage = new BackgroundImage(image,BackgroundRepeat.NO_REPEAT, 
                BackgroundRepeat.NO_REPEAT, 
                BackgroundPosition.DEFAULT, 
                   BackgroundSize.DEFAULT);
		Background background = new Background(bgimage);
		return background;
	}
}
