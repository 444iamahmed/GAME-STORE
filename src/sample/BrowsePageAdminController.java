package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

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


}
