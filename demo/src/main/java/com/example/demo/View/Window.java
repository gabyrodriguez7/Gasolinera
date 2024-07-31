package com.example.demo.View;

import javax.swing.*;
import java.awt.*;
import com.example.demo.Controller.Controler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Window extends JFrame {

    @Autowired
    private Controler controler;

    public Window(Controler controler) {
        this.controler = controler;
        setTitle("Login");
        setUndecorated(true);
        setResizable(false);

        // Obtener el tamaño de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);

        // Configuración del JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Fondo de imagen
        ImageIcon backgroundImageIcon = new ImageIcon("src/main/resources/prueb.png");
        Image backgroundImage = backgroundImageIcon.getImage().getScaledInstance(screenSize.width, screenSize.height, Image.SCALE_SMOOTH);
        ImageIcon scaledBackgroundImageIcon = new ImageIcon(backgroundImage);
        JLabel backgroundLabel = new JLabel(scaledBackgroundImageIcon);
        backgroundLabel.setLayout(null);
        backgroundLabel.setBounds(0, 0, screenSize.width, screenSize.height);
        setContentPane(backgroundLabel);

        // Configurar el panel de inicio de sesión
        JPanel loginPanel = new JPanel();
        loginPanel.setOpaque(false); // Hacer el panel transparente para que se vea el fondo
        loginPanel.setLayout(null);
        loginPanel.setBounds(0, 0, screenSize.width, screenSize.height);
        int desplazamientoY = 100;
        // Tamaños y márgenes
        int anchoLabel = 150;
        int altoLabel = 30;
        int anchoCampoTexto = 300;
        int altoCampoTexto = 30;
        int anchoBoton = 150;
        int altoBoton = 30;

        // Etiqueta de Usuario
        JLabel usernameLabel = new JLabel("Usuario:");
        usernameLabel.setForeground(Color.BLACK); // Color del texto blanco
        usernameLabel.setBounds((screenSize.width - anchoCampoTexto) / 2 - anchoLabel, (screenSize.height - altoCampoTexto) / 2 - altoLabel + desplazamientoY, anchoLabel, altoLabel);        usernameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        loginPanel.add(usernameLabel);

        // Campo de texto de Usuario
        JTextField usernameField = new JTextField();
        usernameField.setBounds((screenSize.width - anchoCampoTexto) / 2, (screenSize.height - altoCampoTexto) / 2 - altoLabel + desplazamientoY, anchoCampoTexto, altoCampoTexto);        usernameField.setFont(new Font("Arial", Font.PLAIN, 20));
        usernameField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        loginPanel.add(usernameField);

        // Etiqueta de Contraseña
        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setForeground(Color.BLACK); // Color del texto blanco
        passwordLabel.setBounds((screenSize.width - anchoCampoTexto) / 2 - anchoLabel, (screenSize.height - altoCampoTexto) / 2 + altoCampoTexto + desplazamientoY, anchoLabel, altoLabel);        passwordLabel.setFont(new Font("Arial", Font.BOLD, 20));
        loginPanel.add(passwordLabel);

        // Campo de texto de Contraseña
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds((screenSize.width - anchoCampoTexto) / 2, (screenSize.height - altoCampoTexto) / 2 + altoCampoTexto, anchoCampoTexto, altoCampoTexto);
        passwordField.setBounds((screenSize.width - anchoCampoTexto) / 2, (screenSize.height - altoCampoTexto) / 2 + altoCampoTexto + desplazamientoY, anchoCampoTexto, altoCampoTexto);        passwordField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        loginPanel.add(passwordField);



        // Botón de Acceder
        JButton loginButton = new JButton("Acceder");
        loginButton.setBounds((screenSize.width - anchoBoton) / 2, (screenSize.height - altoCampoTexto) / 2 + 2 * altoCampoTexto + 20 + desplazamientoY, anchoBoton, altoBoton);        loginButton.setFont(new Font("Arial", Font.BOLD, 20));
        loginButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            controler.iniciarVistaSegunRol(username, password);
            this.setVisible(false);
        });
        loginPanel.add(loginButton);
        loginPanel.revalidate();
        loginPanel.repaint();
        // Agregar el panel de inicio de sesión al contenido del marco
        getContentPane().add(loginPanel);
        setVisible(true);
    }

}
