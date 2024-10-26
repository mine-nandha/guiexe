package com.mine.guidemo;

import com.mine.guidemo.gui.GUI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class GuidemoApplication{

	public static void main(String[] args) {
		new Thread(() -> {
			SpringApplicationBuilder builder = new SpringApplicationBuilder(GuidemoApplication.class);
			builder.headless(false);
			ConfigurableApplicationContext context = builder.run(args);
		}).start();
		SwingUtilities.invokeLater(GUI::showGUI);
	}
}
