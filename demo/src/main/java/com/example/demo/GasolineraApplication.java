package com.example.demo;

import com.example.demo.View.VistaEmpleado;
import com.example.demo.View.VistaJefe;
import com.example.demo.View.Window;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GasolineraApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(
				GasolineraApplication.class).headless(false).run(args);
		Window appFrame = context.getBean(Window.class);
		appFrame.setVisible(true);


	}


}



