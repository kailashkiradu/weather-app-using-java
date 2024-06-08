import javax.swing.*;

public class AppLauncher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // display our weather app GUI
                new WeatherApp().setVisible(true);

                //System.out.println(App.getLocationData("Chennai"));

                //System.out.println(App.getCurrentTime());
            }
        });
    }
}
