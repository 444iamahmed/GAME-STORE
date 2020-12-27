package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;

public abstract class UsersPageController {

    @FXML
    ToggleGroup timeCreatedToggleGroup, sortOrderToggleGroup;

    @FXML
    TextField searchText;

    Store myStore;
    Filter filter;

    public void initialize()
    {
        myStore = Store.getInstance();
        filter = new Filter();

        setFilterToggleGroups();

    }



    private void setFilterToggleGroups() {
        for(Toggle i: timeCreatedToggleGroup.getToggles())
        {
            if(((RadioButton) i).getText().toLowerCase(Locale.ROOT).equals("all time"))
            {
                i.setUserData(TimePeriod.ALL_TIME);
            }
            else if(((RadioButton) i).getText().toLowerCase(Locale.ROOT).equals("this year"))
            {
                i.setUserData(TimePeriod.THIS_YEAR);
            }
            else if(((RadioButton) i).getText().toLowerCase(Locale.ROOT).equals("this month"))
            {
                i.setUserData(TimePeriod.THIS_MONTH);
            }
            else
            {
                i.setUserData(TimePeriod.THIS_WEEK);
            }
        }

        for(Toggle i: sortOrderToggleGroup.getToggles())
        {
            ((RadioButton) i).setUserData(((RadioButton) i).getText());
        }
        timeCreatedToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
        @Override
        public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
            filter.setTimePeriod((TimePeriod) t1.getUserData());
            search();
        }
    });

        sortOrderToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                filter.setOrder((String) t1.getUserData());
                search();
            }
        });
    }

    public abstract void search();
}
