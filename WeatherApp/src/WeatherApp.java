import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WeatherApp extends JFrame {
    private JSONObject weatherData;

    public WeatherApp() {
        // title
        super("Weather Application");

        // stop the program when it is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // GUI size
        setSize(450, 650);

        // GUI at center
        setLocationRelativeTo(null);

        // manually position our component
        setLayout(null);

        // resize GUI
        setResizable(true);

        //GUI components
        addGuiComponents();
    }

    // GUI add method
    private void addGuiComponents() {

        // search field
        JTextField searchTextField = new JTextField();

        // size and location of search field
        searchTextField.setBounds(15, 15, 351, 45);

        // font
        searchTextField.setFont(new Font("Times New Roman", Font.PLAIN, 24));

        add(searchTextField);

        // Weather condition panel to center the image
        JLabel weatherConditionImage = new JLabel(loadImage("src/asserts/cloudy.png"));
        weatherConditionImage.setBounds(100, 125, 250, 217);
        weatherConditionImage.setHorizontalAlignment(SwingConstants.CENTER);
        add(weatherConditionImage);

        // temperature text
        JLabel temperatureText = new JLabel("10 C");
        temperatureText.setBounds(0, 350, 450, 54);
        temperatureText.setFont(new Font("Times New Roman", Font.BOLD, 48));

        // center the text
        temperatureText.setHorizontalAlignment(SwingConstants.CENTER);
        add(temperatureText);

        // weather condition description
        JLabel weatherConditionDesc = new JLabel("Cloudy");
        weatherConditionDesc.setBounds(0, 405, 450, 36);
        weatherConditionDesc.setFont(new Font("Times New Roman", Font.PLAIN, 32));
        weatherConditionDesc.setHorizontalAlignment(SwingConstants.CENTER);
        add(weatherConditionDesc);

        // Humidity image
        JLabel humidityImage = new JLabel(loadImage("src/asserts/humidity.png"));
        humidityImage.setBounds(15, 500, 74, 66);
        add(humidityImage);

        // humidity text
        JLabel humidityText = new JLabel("<html><b>Humidity: 100%</b></html>");
        humidityText.setBounds(90, 500, 85, 55);
        humidityText.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        add(humidityText);

        // windspeed image
        JLabel windSpeedImage = new JLabel(loadImage("src/asserts/windspeed.png"));
        windSpeedImage.setBounds(220, 500, 74, 66);
        add(windSpeedImage);

        // windspeed text
        JLabel windSpeedText = new JLabel("<html><b>Windspeed: 15 km/h</b></html>");
        windSpeedText.setBounds(300, 500, 85, 55);
        windSpeedText.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        add(windSpeedText);

        // search button
        JButton searchButton = new JButton(loadImage("src/asserts/search.png"));

        // change the cursor to a hand cursor when hovering over this button
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.setBounds(375, 13, 47, 45);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get location from user
                String userInput = searchTextField.getText();

                // validate input - remove whitespace to ensure non-empty list
                if (userInput.replaceAll("\\s", "").length() <= 0) {
                    return;
                }

                // retrieve weather data
                weatherData = App.getWeatherData(userInput);

                // update GUI
                if (weatherData == null) {
                    JOptionPane.showMessageDialog(null, "Error: Could not retrieve weather data.");
                    return;
                }

                // update weather image
                String weatherCondition = (String) weatherData.get("weather_condition");

                // depending on the condition, we will update the weather image that corresponds with the condition
                switch (weatherCondition) {
                    case "Clear":
                        weatherConditionImage.setIcon(loadImage("src/asserts/clear.png"));
                        break;
                    case "Cloudy":
                        weatherConditionImage.setIcon(loadImage("src/asserts/cloudy.png"));
                        break;
                    case "Rain":
                        weatherConditionImage.setIcon(loadImage("src/asserts/rain.png"));
                        break;
                    case "Snow":
                        weatherConditionImage.setIcon(loadImage("src/asserts/snow.png"));
                        break;
                }

                // update temperature
                double temperature = (double) weatherData.get("temperature");
                temperatureText.setText(temperature + " C");

                // update weather condition text
                weatherConditionDesc.setText(weatherCondition);

                // update humidity text
                long humidity = (long) weatherData.get("humidity");
                humidityText.setText("<html><b>Humidity: " + humidity + "%</b></html>");

                // update wind speed text
                double windspeed = (double) weatherData.get("windspeed");
                windSpeedText.setText("<html><b>Windspeed: " + windspeed + " km/h</b></html>");
            }
        });
        add(searchButton);
    }

    // load picture to the GUI
    private ImageIcon loadImage(String resourcePath){

        try{
            // read the image file from the given path
            BufferedImage image = ImageIO.read(new File(resourcePath));

            // returns an image icon so that our component can render it
            return new ImageIcon(image);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Could not found resource");
        return null;
    }
}
