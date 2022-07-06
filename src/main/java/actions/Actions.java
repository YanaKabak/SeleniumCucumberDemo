package actions;

public class Actions {

    private static MainActions mainActions;
    private static PlayersActions playersActions;

    public static MainActions mainActions() {
        if (mainActions == null) {
            mainActions = new MainActions();
        }
        return mainActions;
    }

    public static PlayersActions playersActions() {
        if (playersActions == null) {
            playersActions = new PlayersActions();
        }
        return playersActions;
    }

}
