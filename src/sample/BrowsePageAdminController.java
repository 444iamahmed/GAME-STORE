package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BrowsePageAdminController extends BrowsePageController {

    @Override
    public void fillTitlesContainer() throws IOException {
        titlesContainer.getChildren().clear();
        int maxCol = 3, rowCnt = 0, colCnt = 0;
        for(Title i: myStore.searchTitles(browseFilter))
        {
            FXMLLoader titleLoader = new FXMLLoader(getClass().getResource("TitleInGridAdmin.fxml"));
            Parent titleInGrid = titleLoader.load();
            TitleInGridAdminController titleController = titleLoader.getController();
            titleController.fillData(i);
            titleController.setMainPageController(myController);
            titlesContainer.add(titleInGrid, colCnt, rowCnt);
            colCnt++;

            if(colCnt > maxCol)
            {
                rowCnt++;
                colCnt = 0;
            }
        }
    }

    public void addTitle() throws IOException {

        Stage newTitleInputWindow = new Stage();
        newTitleInputWindow.setTitle("New Title");
        FXMLLoader windowLoader = new FXMLLoader(getClass().getResource("NewTitleInput.fxml"));
        Parent newTitleParent = windowLoader.load();
        newTitleInputWindow.setScene(new Scene(newTitleParent));
        newTitleInputWindow.show();

    }

}
