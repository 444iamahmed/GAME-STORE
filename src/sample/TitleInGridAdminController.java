package sample;

import java.io.IOException;

public class TitleInGridAdminController extends TitleInGridController{

    @Override
    public void openTitlePage() throws IOException {
        ((MainPageAdminController) myController).openTitlePage(title);
    }
}
