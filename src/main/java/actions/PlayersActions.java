package actions;

import pages.Pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayersActions {

    public boolean isTableSorted(){
        List<String> usernames = new ArrayList<>();
        for(int i=0;i<Pages.playersPage().tableSorted().size();i++){
            usernames.add(Pages.playersPage().tableSorted().get(i).getText());
        }
        List<String> tmp = new ArrayList<>(usernames);
        Collections.sort(tmp);
        return tmp.equals(usernames);
    }
}
