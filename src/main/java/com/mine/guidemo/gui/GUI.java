package com.mine.guidemo.gui;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GUI {
    public static void showGUI(){
        //Main Frame
        JFrame jFrame= new JFrame("HealthCheck Report");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(300, 200);
        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));

        //Open Site Button Panel
        JPanel openSiteButtonPanel = new JPanel();
        openSiteButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton openSiteButton = new JButton("Open Site");
        openSiteButton.addActionListener(e -> {
            openURL("http://localhost:8080/hello");
        });
        openSiteButtonPanel.add(openSiteButton);

        //Add Panels to Frames
        jFrame.add(Box.createVerticalGlue());
        jFrame.add(openSiteButtonPanel);
        jFrame.add(Box.createVerticalGlue());

        //Display Main Frame
        jFrame.setVisible(true);
    }
    public static void openURL(String url) {
        String os = System.getProperty("os.name").toLowerCase();
        Runtime rt = Runtime.getRuntime();

        try {
            if (os.contains("win")) {
                // For Windows
                rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
            } else if (os.contains("mac")) {
                // For macOS
                rt.exec("open " + url);
            } else if (os.contains("nix") || os.contains("nux")) {
                // For Linux/Unix
                String[] browsers = {"xdg-open", "gnome-open", "kde-open", "firefox", "opera", "mozilla", "konqueror", "epiphany", "netscape"};
                for (String browser : browsers) {
                    if (Runtime.getRuntime().exec(new String[]{"which", browser}).getInputStream().read() != -1) {
                        rt.exec(new String[]{browser, url});
                        break;
                    }
                }
            } else {
                System.out.println("Unsupported operating system.");
            }
        } catch (IOException e) {
            System.out.println("Error opening browser: " + e.getMessage());
        }
    }
}
