package pages;

public class Pages {

    private static LoginPage loginPage;
    private static NavigationPage navigationPage;
    private static PlayersPage playersPage;

    public static LoginPage loginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public static NavigationPage navigationPage() {
        if (navigationPage == null) {
            navigationPage = new NavigationPage();
        }
        return navigationPage;
    }

    public static PlayersPage playersPage() {
        if (playersPage == null) {
            playersPage = new PlayersPage();
        }
        return playersPage;
    }
}
