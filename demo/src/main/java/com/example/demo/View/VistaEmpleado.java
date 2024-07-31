package com.example.demo.View;

import com.example.demo.Controller.Controler;
import com.example.demo.Model.Cliente;
import com.example.demo.Model.Producto;
import com.example.demo.Model.Usuario;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class VistaEmpleado extends JFrame {

    private JTextField txtCedulaRUC;
    private JTextField txtPlaca;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtCorreo;
    private JTextField txtTelefono;
    private JComboBox<String> cmbMetodoPago;
    private JTextArea txtFactura;
    private JButton btnGuardarCliente;
    private JLabel txtAreaNotificaciones;
    private Timer timer;
    JLabel lblGasolinaSuper;
    JLabel lblGasolinaExtra;
    JLabel lblDiesel;
    JLabel lblProducto;
    JLabel lblCantidad;
    JTextField txtDieselCantidad;
    JTextField txtExtraCantidad;
    JTextField txtSuperCantidad;
    JTextField txtSuperCantidadDinero;
    JTextField txtExtraCantidadDinero;
    JTextField txtDieselCantidadDinero;
    JTextField txtProducto;
    JTextField txtCantidad;
    JTextField txtProductoCantidadDinero;

    DecimalFormat df = new DecimalFormat("#.00");


    private JList<String> listaProductos;
    private DefaultListModel<String> modeloListaProductos;

    private JLabel lblTotal;

    private final Controler controler;
    private Cliente clienteEncontrado;
    private Usuario usuario;

    public VistaEmpleado(Controler controler) {
        this.controler = controler;
        this.usuario = usuario;

        setUndecorated(true);
        GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = graphics.getDefaultScreenDevice();
        setSize(device.getDisplayMode().getWidth(), device.getDisplayMode().getHeight());
        device.setFullScreenWindow(this);

        initComponents();
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowIconified(java.awt.event.WindowEvent windowEvent) {
                // Evitar que se minimice
                setState(JFrame.NORMAL);
            }
        });
        this.setVisible(true);
    }

    private void initComponents() {
        // Configuración del JFrame
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Fondo
        setContentPane(new JLabel(new ImageIcon("src/main/resources/caf.jpg")));
        // Cargar la imagen
        ImageIcon iconoUsuario = new ImageIcon("src/main/resources/fondo1.png");

        // Ajustar tamaño de la imagen (opcional)
        Image imagenUsuario = iconoUsuario.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        iconoUsuario = new ImageIcon(imagenUsuario);

        // Crear el JLabel con la imagen
        JLabel lblImagenUsuario = new JLabel(iconoUsuario);
        lblImagenUsuario.setBounds(20, 5, 60, 50);


        // Barra superior con nombre de usuario y botón de salir
        JPanel barraSuperior = new JPanel();
        barraSuperior.setLayout(null);
        barraSuperior.setBackground(new Color(0, 0, 0, 150));
        barraSuperior.setBounds(0, 0, getWidth(), 55);
        add(barraSuperior);

        JLabel lblNombreUsuario = new JLabel("Usuario: " + controler.nameUser(), JLabel.CENTER);
        lblNombreUsuario.setFont(new Font("Arial", Font.BOLD, 16));
        lblNombreUsuario.setForeground(Color.WHITE);
        lblNombreUsuario.setBounds(55, 10, 200, 30);


        // Cargar la imagen para el "botón" salir
        ImageIcon iconoSalir = new ImageIcon("src/main/resources/Salir.png");

        // Ajustar tamaño de la imagen (opcional)
        Image imagenSalir = iconoSalir.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        iconoSalir = new ImageIcon(imagenSalir);

        // Crear el JLabel con la imagen
        JLabel lblImagenSalir = new JLabel(iconoSalir);
        lblImagenSalir.setBounds(getWidth() - 40, 10, 30, 30);
        lblImagenSalir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Acción al hacer clic en la imagen
                SwingUtilities.getWindowAncestor(lblImagenSalir).dispose();
                Window window = new Window(controler);
                window.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lblImagenSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblImagenSalir.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        barraSuperior.add(lblNombreUsuario);
        barraSuperior.add(lblImagenUsuario);
        barraSuperior.add(lblImagenSalir);



        // Proporciones y márgenes
        int margenX = 20;
        int margenY = 70;
        int anchoLabel = 200;
        int altoLabel = 30;
        int anchoCampoTexto = 300;
        int altoCampoTexto = 30;
        int separacionVertical = 40;
        int anchoBoton = 200;
        int altoBoton = 30;
        int separacionHorizontal = 20;


        JLabel lblPlaca = new JLabel("Placa:");
        lblPlaca.setBounds(margenX, margenY, anchoLabel, altoLabel);
        lblPlaca.setForeground(Color.BLACK);
        lblPlaca.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblPlaca);
        txtPlaca = new JTextField(15);
        txtPlaca.setBounds(margenX + anchoLabel + separacionHorizontal, margenY, anchoCampoTexto, altoCampoTexto);
        txtPlaca.setFont(new Font("Arial", Font.BOLD, 18));
        add(txtPlaca);

        JLabel lblCedulaRUC = new JLabel("Cédula/RUC:");
        lblCedulaRUC.setBounds(margenX, margenY + separacionVertical, anchoLabel, altoLabel);
        lblCedulaRUC.setForeground(Color.BLACK);
        lblCedulaRUC.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblCedulaRUC);
        txtCedulaRUC = new JTextField(15);
        txtCedulaRUC.setBounds(margenX + anchoLabel + separacionHorizontal, margenY + separacionVertical, anchoCampoTexto, altoCampoTexto);
        txtCedulaRUC.setFont(new Font("Arial", Font.BOLD, 16));
        add(txtCedulaRUC);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(margenX, margenY + 2 * separacionVertical, anchoLabel, altoLabel);
        lblNombre.setForeground(Color.BLACK);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblNombre);
        txtNombre = new JTextField(15);
        txtNombre.setBounds(margenX + anchoLabel + separacionHorizontal, margenY + 2 * separacionVertical, anchoCampoTexto, altoCampoTexto);
        txtNombre.setFont(new Font("Arial", Font.BOLD, 16));
        add(txtNombre);

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(margenX, margenY + 3 * separacionVertical, anchoLabel, altoLabel);
        lblApellido.setForeground(Color.BLACK);
        lblApellido.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblApellido);
        txtApellido = new JTextField(15);
        txtApellido.setBounds(margenX + anchoLabel + separacionHorizontal, margenY + 3 * separacionVertical, anchoCampoTexto, altoCampoTexto);
        txtApellido.setFont(new Font("Arial", Font.BOLD, 16));
        add(txtApellido);

        JLabel lblCorreo = new JLabel("Correo Electrónico:");
        lblCorreo.setBounds(margenX, margenY + 4 * separacionVertical, anchoLabel, altoLabel);
        lblCorreo.setForeground(Color.BLACK);
        lblCorreo.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblCorreo);
        txtCorreo = new JTextField(15);
        txtCorreo.setBounds(margenX + anchoLabel + separacionHorizontal, margenY + 4 * separacionVertical, anchoCampoTexto, altoCampoTexto);
        txtCorreo.setFont(new Font("Arial", Font.BOLD, 16));
        add(txtCorreo);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(margenX, margenY + 5 * separacionVertical, anchoLabel, altoLabel);
        lblTelefono.setForeground(Color.BLACK);
        lblTelefono.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblTelefono);
        txtTelefono = new JTextField(15);
        txtTelefono.setBounds(margenX + anchoLabel + separacionHorizontal, margenY + 5 * separacionVertical, anchoCampoTexto, altoCampoTexto);
        txtTelefono.setFont(new Font("Arial", Font.BOLD, 16));
        add(txtTelefono);

        JButton btnBuscarCliente = new JButton("Buscar Cliente");
        btnBuscarCliente.setFont(new Font("Arial", Font.BOLD, 18));
        btnBuscarCliente.setBounds(margenX, margenY + 6 * separacionVertical, anchoBoton, altoBoton);
        btnBuscarCliente.addActionListener(e -> buscarCliente());
        add(btnBuscarCliente);

        btnGuardarCliente = new JButton("Guardar Cliente");
        btnGuardarCliente.setFont(new Font("Arial", Font.BOLD, 18));
        btnGuardarCliente.setBounds(margenX + anchoBoton + separacionHorizontal, margenY + 6 * separacionVertical, anchoBoton, altoBoton);
        btnGuardarCliente.addActionListener(e -> guardarCliente());
        add(btnGuardarCliente);

        //factura
        int anchoBotonesCombinado = 2 * anchoBoton + separacionHorizontal + 42;
        int alturaDisponible = getHeight() - (margenY + 6 * separacionVertical + altoBoton + 40) - 114; // Ajusta 40 según el margen adicional que desees
        int margenAdicional = 40;

        int margenXa = 20; // Margen izquierdo
        int margenYa = 20; // Margen superior
        int separacionVerticala = 10; // Separación entre componentes
        int altoBotona = 30; // Alto del botón
        int margenAdicionala = 10; // Margen adicional
        int anchoBotonesCombinadoa = 510; // Ancho del JTextArea
        int alturaDisponiblea = 475; // Altura del JTextArea

        txtFactura = new JTextArea();
        int yPosicion = margenYa + 6 * separacionVerticala + altoBotona + 10 + margenAdicionala + 225;
        txtFactura.setBounds(margenXa, yPosicion, anchoBotonesCombinadoa, alturaDisponiblea);
        txtFactura.setEditable(false);
        txtFactura.setForeground(Color.BLACK);
        txtFactura.setBackground(Color.WHITE);
        txtFactura.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // Ajustar el tamaño del JTextArea si es necesario
        txtFactura.setPreferredSize(new Dimension(anchoBotonesCombinado, alturaDisponible));

        // Agregar el JTextArea al panel
        add(txtFactura);


        setPreferredSize(new Dimension(anchoBotonesCombinado + 2 * margenX, alturaDisponible + 2 * margenY + altoBoton + 10 + margenAdicional));

        txtAreaNotificaciones = new JLabel();
        txtAreaNotificaciones.setBounds(margenX, margenY + 7 * separacionVertical, getWidth() - 2 * margenX, 50);
        txtAreaNotificaciones.setForeground(Color.BLACK);
        Font font = new Font("Arial", Font.BOLD, 18);
        txtAreaNotificaciones.setFont(font);
        add(txtAreaNotificaciones);
        timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtAreaNotificaciones.setText("");
                timer.stop();
            }
        });

        int desplazamientoX = 56;
        int desplazamientoY = 56;

        int columnaProductosX = margenX + anchoLabel + anchoCampoTexto + 3 * separacionHorizontal + desplazamientoX;

// Panel de Selección de Productos
        // Panel de Selección de Productos

// Gasolina Super
        lblGasolinaSuper = new JLabel("Gasolina Super:");
        lblGasolinaSuper.setBounds(columnaProductosX, margenY + desplazamientoY, anchoLabel, altoLabel);
        lblGasolinaSuper.setForeground(Color.BLACK);
        lblGasolinaSuper.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblGasolinaSuper);

        txtSuperCantidad = new JTextField(5);
        txtSuperCantidad.setBounds(columnaProductosX + anchoLabel + separacionHorizontal, margenY + desplazamientoY, anchoCampoTexto / 3, altoCampoTexto);
        txtSuperCantidad.setFont(new Font("Arial", Font.BOLD, 18));
        add(txtSuperCantidad);

// Agregar JLabel para "GL"
        int posicionXlblGL1 = columnaProductosX + anchoLabel + separacionHorizontal + anchoCampoTexto / 3 + separacionHorizontal;
        JLabel lblGL1 = new JLabel("gl");
        lblGL1.setFont(new Font("Arial", Font.BOLD, 18));
        lblGL1.setBounds(posicionXlblGL1, margenY + desplazamientoY, 20, altoLabel);
        add(lblGL1);

         txtSuperCantidadDinero = new JTextField(5);
        int espacioEntreCampos = 60; // Espacio deseado entre los campos de texto
        int posicionXtxtSuperCantidadDinero = columnaProductosX + anchoLabel + separacionHorizontal + anchoCampoTexto / 3 + espacioEntreCampos;
        txtSuperCantidadDinero.setBounds(posicionXtxtSuperCantidadDinero, margenY + desplazamientoY, anchoCampoTexto / 3, altoCampoTexto);
        txtSuperCantidadDinero.setFont(new Font("Arial", Font.BOLD, 18));
        add(txtSuperCantidadDinero);

        int espaciodin1 = 15; // Espacio pequeño entre txtSuperCantidadDinero y lblDr1
        int posicionXlblDr1 = posicionXtxtSuperCantidadDinero + (anchoCampoTexto / 3) + espaciodin1;
        JLabel lblDr1 = new JLabel("$");
        lblDr1.setFont(new Font("Arial", Font.BOLD, 18));
        lblDr1.setBounds(posicionXlblDr1, margenY + desplazamientoY, 20, altoLabel);
        add(lblDr1);

        JButton btnAgregarSuper = new JButton("Agregar");
        btnAgregarSuper.setFont(new Font("Arial", Font.BOLD, 18));
        btnAgregarSuper.setBounds(columnaProductosX + anchoLabel + anchoCampoTexto + 3 * separacionHorizontal, margenY + desplazamientoY, anchoBoton, altoBoton);
        btnAgregarSuper.addActionListener(e -> agregarProducto("Gasolina Super", txtSuperCantidad.getText(), txtSuperCantidadDinero.getText()));
        add(btnAgregarSuper);

        int desplazamientoYExtra = desplazamientoY + separacionVertical;

// Gasolina Extra
         lblGasolinaExtra = new JLabel("Gasolina Extra:");
        lblGasolinaExtra.setBounds(columnaProductosX, margenY + desplazamientoYExtra, anchoLabel, altoLabel);
        lblGasolinaExtra.setForeground(Color.BLACK);
        lblGasolinaExtra.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblGasolinaExtra);

         txtExtraCantidad = new JTextField(5);
        txtExtraCantidad.setBounds(columnaProductosX + anchoLabel + separacionHorizontal, margenY + desplazamientoYExtra, anchoCampoTexto / 3, altoCampoTexto);
        txtExtraCantidad.setFont(new Font("Arial", Font.BOLD, 18));
        add(txtExtraCantidad);

// Agregar JLabel para "GL" de Gasolina Extra
        int posicionXlblGL2 = columnaProductosX + anchoLabel + separacionHorizontal + anchoCampoTexto / 3 + separacionHorizontal;
        JLabel lblGL2 = new JLabel("gl");
        lblGL2.setFont(new Font("Arial", Font.BOLD, 18));
        lblGL2.setBounds(posicionXlblGL2, margenY + desplazamientoYExtra, 20, altoLabel);
        add(lblGL2);

         txtExtraCantidadDinero = new JTextField(5);
        int posicionXtxtExtraCantidadDinero = columnaProductosX + anchoLabel + separacionHorizontal + anchoCampoTexto / 3 + espacioEntreCampos;
        txtExtraCantidadDinero.setBounds(posicionXtxtExtraCantidadDinero, margenY + desplazamientoYExtra, anchoCampoTexto / 3, altoCampoTexto);
        txtExtraCantidadDinero.setFont(new Font("Arial", Font.BOLD, 18));
        add(txtExtraCantidadDinero);

// Agregar JLabel para "$" de Gasolina Extra
        int posicionXlblDr2 = posicionXtxtExtraCantidadDinero + (anchoCampoTexto / 3) + espaciodin1;
        JLabel lblDr2 = new JLabel("$");
        lblDr2.setFont(new Font("Arial", Font.BOLD, 18));
        lblDr2.setBounds(posicionXlblDr2, margenY + desplazamientoYExtra, 20, altoLabel);
        add(lblDr2);

        JButton btnAgregarExtra = new JButton("Agregar");
        btnAgregarExtra.setFont(new Font("Arial", Font.BOLD, 18));
        btnAgregarExtra.setBounds(columnaProductosX + anchoLabel + anchoCampoTexto + 3 * separacionHorizontal, margenY + desplazamientoYExtra, anchoBoton, altoBoton);
        btnAgregarExtra.addActionListener(e -> agregarProducto("Gasolina Extra", txtExtraCantidad.getText(), txtExtraCantidadDinero.getText()));
        add(btnAgregarExtra);

        int desplazamientoYExtra2 = desplazamientoYExtra + separacionVertical;

        // Diesel
        lblDiesel = new JLabel("Diesel:");
        lblDiesel.setBounds(columnaProductosX, margenY + desplazamientoYExtra2, anchoLabel, altoLabel);
        lblDiesel.setForeground(Color.BLACK);
        lblDiesel.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblDiesel);

         txtDieselCantidad = new JTextField(5);
        txtDieselCantidad.setBounds(columnaProductosX + anchoLabel + separacionHorizontal, margenY + desplazamientoYExtra2, anchoCampoTexto / 3, altoCampoTexto);
        txtDieselCantidad.setFont(new Font("Arial", Font.BOLD, 18));
        add(txtDieselCantidad);

// Agregar JLabel para "GL" de Diesel
        int posicionXlblGL3 = columnaProductosX + anchoLabel + separacionHorizontal + anchoCampoTexto / 3 + separacionHorizontal;
        JLabel lblGL3 = new JLabel("gl");
        lblGL3.setFont(new Font("Arial", Font.BOLD, 18));
        lblGL3.setBounds(posicionXlblGL3, margenY + desplazamientoYExtra2, 20, altoLabel);
        add(lblGL3);

         txtDieselCantidadDinero = new JTextField(5);
        int posicionXtxtDieselCantidadDinero = columnaProductosX + anchoLabel + separacionHorizontal + anchoCampoTexto / 3 + espacioEntreCampos;
        txtDieselCantidadDinero.setBounds(posicionXtxtDieselCantidadDinero, margenY + desplazamientoYExtra2, anchoCampoTexto / 3, altoCampoTexto);
        txtDieselCantidadDinero.setFont(new Font("Arial", Font.BOLD, 18));
        add(txtDieselCantidadDinero);

// Agregar JLabel para "$" de Diesel
        int posicionXlblDr3 = posicionXtxtDieselCantidadDinero + (anchoCampoTexto / 3) + espaciodin1;
        JLabel lblDr3 = new JLabel("$");
        lblDr3.setFont(new Font("Arial", Font.BOLD, 18));
        lblDr3.setBounds(posicionXlblDr3, margenY + desplazamientoYExtra2, 20, altoLabel);
        add(lblDr3);

        JButton btnAgregarDiesel = new JButton("Agregar");
        btnAgregarDiesel.setFont(new Font("Arial", Font.BOLD, 18));
        btnAgregarDiesel.setBounds(columnaProductosX + anchoLabel + anchoCampoTexto + 3 * separacionHorizontal, margenY + desplazamientoYExtra2, anchoBoton, altoBoton);
        btnAgregarDiesel.addActionListener(e -> agregarProducto("Diesel", txtDieselCantidad.getText(), txtDieselCantidadDinero.getText()));
        add(btnAgregarDiesel);

// Nuevo Producto
        int desplazamientoYProducto = desplazamientoYExtra2 + separacionVertical;

         lblProducto = new JLabel("Producto:");
        lblProducto.setBounds(columnaProductosX, margenY + desplazamientoYProducto, anchoLabel, altoLabel);
        lblProducto.setForeground(Color.BLACK);
        lblProducto.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblProducto);

         txtProducto = new JTextField(5);
        txtProducto.setBounds(columnaProductosX + anchoLabel + separacionHorizontal, margenY + desplazamientoYProducto, anchoCampoTexto , altoCampoTexto);
        txtProducto.setFont(new Font("Arial", Font.BOLD, 16));
        add(txtProducto);

         lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setBounds(columnaProductosX, margenY + desplazamientoYProducto + separacionVertical, anchoLabel, altoLabel);
        lblCantidad.setForeground(Color.BLACK);
        lblCantidad.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblCantidad);

         txtCantidad = new JTextField(5);
        txtCantidad.setBounds(columnaProductosX + anchoLabel + separacionHorizontal, margenY + desplazamientoYProducto + separacionVertical, anchoCampoTexto / 3, altoCampoTexto);
        txtCantidad.setFont(new Font("Arial", Font.BOLD, 18));
        add(txtCantidad);

        // Agregar JLabel para "lt"
        int uposicionXlblGL4 = columnaProductosX + anchoLabel + separacionHorizontal + anchoCampoTexto / 3 + separacionHorizontal;
        JLabel lblGL4 = new JLabel("lt");
        lblGL4.setFont(new Font("Arial", Font.BOLD, 18));
        lblGL4.setBounds(uposicionXlblGL4, margenY + desplazamientoYProducto + separacionVertical, 20, altoLabel);
        add(lblGL4);

         txtProductoCantidadDinero = new JTextField(5);
        int posicionXtxtProductoCantidadDinero = columnaProductosX + anchoLabel + separacionHorizontal + anchoCampoTexto / 3 + espacioEntreCampos;
        txtProductoCantidadDinero.setBounds(posicionXtxtProductoCantidadDinero, margenY + desplazamientoYProducto + separacionVertical, anchoCampoTexto / 3, altoCampoTexto);
        txtProductoCantidadDinero.setFont(new Font("Arial", Font.BOLD, 18));
        add(txtProductoCantidadDinero);

        // Agregar JLabel para "$" del nuevo Producto
        int posicionXlblDr4 = posicionXtxtProductoCantidadDinero + (anchoCampoTexto / 3) + espaciodin1;
        JLabel lblDr4 = new JLabel("$");
        lblDr4.setFont(new Font("Arial", Font.BOLD, 18));
        lblDr4.setBounds(posicionXlblDr4, margenY + desplazamientoYProducto + separacionVertical, 20, altoLabel);
        add(lblDr4);

        JButton btnAgregarProducto = new JButton("Agregar");
        btnAgregarProducto.setFont(new Font("Arial", Font.BOLD, 18));
        btnAgregarProducto.setBounds(columnaProductosX + anchoLabel + anchoCampoTexto + 3 * separacionHorizontal, margenY + desplazamientoYProducto + separacionVertical, anchoBoton, altoBoton);
        btnAgregarProducto.addActionListener(e -> agregarProducto(txtProducto.getText(), txtCantidad.getText(), txtProductoCantidadDinero.getText()));
        add(btnAgregarProducto);
        // Otros Productos
        modeloListaProductos = new DefaultListModel<>();
        listaProductos = new JList<>(modeloListaProductos);
        JScrollPane scrollProductos = new JScrollPane(listaProductos);
        scrollProductos.setBounds(columnaProductosX, margenY + desplazamientoY + 5 * separacionVertical, anchoLabel + anchoCampoTexto + anchoBoton + 3 * separacionHorizontal, 200);
        add(scrollProductos);
        //// Botón Eliminar
        JButton btnEliminarProducto = new JButton("Eliminar Producto");
        btnEliminarProducto.setFont(new Font("Arial", Font.BOLD, 18));
        int anchoEliminar = anchoBoton;
        int altoEliminar = altoBoton;
        int centroX = columnaProductosX + (anchoLabel + anchoCampoTexto + anchoBoton + 3 * separacionHorizontal - anchoEliminar) / 2;
        int posYEliminar = margenY + desplazamientoY + 5 * separacionVertical + 200 + 30;
        btnEliminarProducto.setBounds(centroX, posYEliminar, anchoEliminar, altoEliminar);
        btnEliminarProducto.addActionListener(e -> eliminarProducto());
        add(btnEliminarProducto);

        int desplazamientoYMetodos = posYEliminar + altoEliminar + 20;

        JLabel lblMetodoPago = new JLabel("Método de Pago:");
        lblMetodoPago.setBounds(columnaProductosX, desplazamientoYMetodos, anchoLabel, altoLabel);
        lblMetodoPago.setForeground(Color.BLACK);
        lblMetodoPago.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblMetodoPago);

        cmbMetodoPago = new JComboBox<>(new String[]{"Efectivo", "Tarjeta"});
        cmbMetodoPago.setFont(new Font("Arial", Font.BOLD, 18));
        cmbMetodoPago.setBounds(columnaProductosX + anchoLabel + separacionHorizontal, desplazamientoYMetodos, anchoCampoTexto, altoCampoTexto);
        add(cmbMetodoPago);

        JButton btnRealizarVenta = new JButton("Realizar Venta");
        btnRealizarVenta.setFont(new Font("Arial", Font.BOLD, 18));
        btnRealizarVenta.setBounds(columnaProductosX + anchoLabel + anchoCampoTexto + 2 * separacionHorizontal, desplazamientoYMetodos, anchoBoton, altoBoton);
        btnRealizarVenta.addActionListener(e -> gestionarVenta());
        add(btnRealizarVenta);

        // lblTotal
        lblTotal = new JLabel("Total: $0.00");
        int anchoTotal = lblTotal.getPreferredSize().width; // Obtener el ancho preferido del texto
// Calcular el centro horizontal en relación con el contenedor
        int anchoContenedor = anchoLabel + anchoCampoTexto + anchoBoton + 3 * separacionHorizontal;
        int centroXTotal = columnaProductosX + (anchoContenedor - anchoTotal) / 2;
        int posYTotal = desplazamientoYMetodos + altoCampoTexto + 20; // 20 es el espaciado adicional debajo del JComboBox
        lblTotal.setBounds(centroXTotal, posYTotal, anchoTotal, altoLabel);
        lblTotal.setForeground(Color.BLACK);
        lblTotal.setFont(lblTotal.getFont().deriveFont(20f)); // Ajusta el tamaño de la fuente aquí
        add(lblTotal);

        JLabel lblTituloFactura = new JLabel();
        lblTituloFactura.setBounds(margenX, margenY + 7 * separacionVertical + 220, getWidth() - 2 * margenX, 30);
        lblTituloFactura.setFont(new Font("Arial", Font.BOLD, 18));
        lblTituloFactura.setHorizontalAlignment(JLabel.CENTER);
        lblTituloFactura.setForeground(Color.BLACK);
        add(lblTituloFactura);

        setTitle("Gasolinera");
        setVisible(true);
    }


    private void cargarDatosProductos() {
        List<Producto> productos = controler.cargarDatosProductos();
        for (Producto producto : productos) {
            switch (producto.getNombre()) {
                case "Gasolina Super":
                case "Gasolina Extra":
                case "Diesel":
                    break;
                default:
                    modeloListaProductos.addElement(producto.getNombre() + " - " + producto.getStock());
                    break;
            }
        }
    }
    private void guardarCliente() {
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setPlaca(txtPlaca.getText());
        nuevoCliente.setRucCedula(txtCedulaRUC.getText());
        nuevoCliente.setNombre(txtNombre.getText());
        nuevoCliente.setApellido(txtApellido.getText());
        nuevoCliente.setCorreoElectronico(txtCorreo.getText());
        nuevoCliente.setTelefono(txtTelefono.getText());

        Cliente clienteGuardado = controler.guardar(nuevoCliente);


        // Actualizar los campos de la GUI con el nuevo cliente guardado
        txtCedulaRUC.setText(clienteGuardado.getRucCedula());
        txtNombre.setText(clienteGuardado.getNombre());
        txtApellido.setText(clienteGuardado.getApellido());
        txtCorreo.setText(clienteGuardado.getCorreoElectronico());
        txtTelefono.setText(clienteGuardado.getTelefono());
       // mostrarNotificacion("Cliente guardado");

        JOptionPane.showMessageDialog(this, "Cliente guardado");
        //this.setEnabled(true);
        this.toFront();  // Asegúrate de que la ventana esté en frente
        this.requestFocus();  // Solicitar el enfoque en la ventana
        this.setAlwaysOnTop(false);
        clienteEncontrado = clienteGuardado;
    }

    private void buscarCliente() {
        Optional<Cliente> cliente = controler.buscarCliente(txtPlaca.getText());
        if (cliente.isPresent()) {
            txtCedulaRUC.setText(cliente.get().getRucCedula());
            txtNombre.setText(cliente.get().getNombre());
            txtApellido.setText(cliente.get().getApellido());
            txtCorreo.setText(cliente.get().getCorreoElectronico());
            txtTelefono.setText(cliente.get().getTelefono());

            clienteEncontrado = cliente.get();
        } else {
            JOptionPane.showMessageDialog(this, "Cliente no encontrado", "Información", JOptionPane.INFORMATION_MESSAGE);
            this.toFront();
            this.requestFocus();
            this.setAlwaysOnTop(false);
            btnGuardarCliente.setEnabled(true);
        }
    }
    private void agregarProducto(String producto, String cantidadGalones, String cantidadDinero) {
        try {
            double cantidadNum;
            if (!cantidadGalones.isEmpty()) {
                cantidadNum = Double.parseDouble(cantidadGalones);
            } else if (!cantidadDinero.isEmpty()) {
                if (producto.equals("Gasolina Super") || producto.equals("Gasolina Extra") || producto.equals("Diesel")
                        || producto.equals("Refrigerante")
                        || producto.equals("Refrigerante Verde")
                        || producto.equals("Refrigerante Rojo")
                        || producto.equals("Mejorador de Octanaje")) {
                    Producto p = controler.findProductoByName(producto);
                    double precioPorGalon = p.getPrecio();
                    cantidadNum = Double.parseDouble(cantidadDinero) / precioPorGalon;
                } else {
                    throw new NumberFormatException();
                }
            } else {
                throw new NumberFormatException();
            }
            modeloListaProductos.addElement(producto + " - " + cantidadNum);
            actualizarTotal();
        } catch (NumberFormatException e) {
            JOptionPane optionPane = new JOptionPane("La cantidad debe ser un número válido", JOptionPane.ERROR_MESSAGE);
            JDialog dialog = optionPane.createDialog(this, "Error de entrada");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);

            // Asegurarse de que la ventana principal está en el frente y tiene el foco
            this.toFront();
            this.requestFocus();
        }
    }
    private void eliminarProducto() {
        int selectedIndex = listaProductos.getSelectedIndex();
        if (selectedIndex != -1) {
            modeloListaProductos.remove(selectedIndex);
            actualizarTotal();
        }
    }

    private void actualizarTotal() {
        double total = 0.0;
        for (int i = 0; i < modeloListaProductos.size(); i++) {
            String item = modeloListaProductos.getElementAt(i);
            String[] parts = item.split(" - ");
            String nombreProducto = parts[0];
            double cantidad = Double.parseDouble(parts[1]);

            Optional<Producto> optionalProducto = Optional.ofNullable(controler.findProductoByName(nombreProducto));
            if (optionalProducto.isPresent()) {
                Producto producto = optionalProducto.get();
                total += producto.getPrecio() * cantidad;
            } else {
                JOptionPane.showMessageDialog(this, "Producto no encontrado: " + nombreProducto);
                this.toFront();
                this.requestFocus();
                this.setAlwaysOnTop(false);
            }
        }
        lblTotal.setText(String.format("%.2f", total));
    }

    private void gestionarVenta() {
        String metodoPago = (String) cmbMetodoPago.getSelectedItem();

        if (clienteEncontrado == null) {
            JOptionPane.showMessageDialog(this, "Debe buscar y seleccionar un cliente antes de realizar la venta");
            this.toFront();
            this.requestFocus();
            this.setAlwaysOnTop(false);
            return;
        }
        if (Objects.equals(metodoPago, "Tarjeta")) {
            mostrarDialogoPago();
        }
        // Crear lista para almacenar productos vendidos y cantidades
        List<String> productosVendidos = new ArrayList<>();

        // Construir la factura
        StringBuilder factura = new StringBuilder();
        factura.append("-------------------------------------------------------------------------\n");
        factura.append("                         GASOLINERA ANETA - FACTURA                      \n");
        factura.append("-------------------------------------------------------------------------\n");
        factura.append("  Fecha: ").append(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())).append("\n");
       // factura.append("Número de Factura: ").append("12345").append("\n"); // Ejemplo de número de factura
        factura.append("\n  Cliente:\n");
        factura.append("  Nombre: ").append(clienteEncontrado.getNombre()).append(" ").append(clienteEncontrado.getApellido()).append("\n");
        factura.append("  Cédula/RUC: ").append(clienteEncontrado.getRucCedula()).append("\n");
        factura.append("  Correo Electrónico: ").append(clienteEncontrado.getCorreoElectronico()).append("\n");
        factura.append("  Teléfono: ").append(clienteEncontrado.getTelefono()).append("\n");

        // Iniciar construcción de la factura
        factura.append("\n  Productos:\n");
        factura.append("-------------------------------------------------------------------------\n");
        factura.append(String.format("%-30s %-12s %-15s %-15s\n", "Producto", "Precio Unitario", "Cantidad", "Total"));
        factura.append("-------------------------------------------------------------------------\n");

// Iterar sobre los productos en la lista
        for (int i = 0; i < modeloListaProductos.size(); i++) {
            String item = modeloListaProductos.getElementAt(i);
            String[] parts = item.split(" - ");
            String nombreProducto = parts[0];
            double cantidadComprada = Double.parseDouble(parts[1]);

            productosVendidos.add(item); // Agregar producto vendido a la lista

            Optional<Producto> optionalProducto = Optional.ofNullable(controler.findProductoByName(nombreProducto));
            if (optionalProducto.isPresent()) {
                Producto producto = optionalProducto.get();

                // Calcular el precio unitario y el total en dólares
                double precioUnitario = producto.getPrecio();
                double totalDolares = cantidadComprada * precioUnitario;

                // Imprimir en la factura
                factura.append(String.format(" %-30s %-15.2f %-15.2f %-15.2f\n", nombreProducto, precioUnitario, cantidadComprada, totalDolares));

                // Actualizar stock en la base de datos
                int nuevoStock = producto.getStock() - (int) cantidadComprada;
                controler.actualizarStock1(producto, nuevoStock);
            } else {
                JOptionPane.showMessageDialog(this, "Producto no encontrado: " + nombreProducto);
                this.toFront();
                this.requestFocus();
                this.setAlwaysOnTop(false);
            }
        }

        double total = calcularTotal();
        double totalIVA=total + total*0.15;
        factura.append("-------------------------------------------------------------------------\n");
        factura.append(String.format("  Subtotal: %s\n", df.format(total)));
        factura.append(String.format("  IVA (15%%): %s\n", df.format(total*0.15)));
        factura.append(String.format("  Total: %s\n", df.format(totalIVA)));
        factura.append("-------------------------------------------------------------------------\n");
        factura.append("                  Gracias por su compra!\n");
        factura.append("-------------------------------------------------------------------------\n");

        txtFactura.setText(factura.toString());



        // Guardar la venta en la base de datos
        boolean ventaExitosa = controler.realizarVenta(clienteEncontrado, productosVendidos, metodoPago);

        // Limpiar formulario después de la venta
        limpiarFormulario();
    }
    private void mostrarDialogoPago() {
        JPanel panel = new JPanel(new GridLayout(5, 2));

        JTextField txtNumeroTarjeta = new JTextField();
        JTextField txtFechaCaducidad = new JTextField();
        JTextField txtCodigoSeguridad = new JTextField();
        JComboBox<String> cmbTipoTarjeta = new JComboBox<>(new String[]{"Crédito", "Débito"});
        JComboBox<String> cmbPlazos = new JComboBox<>(new String[]{"3 meses", "6 meses", "9 meses"});
        cmbPlazos.setEnabled(false);

        cmbTipoTarjeta.addActionListener(e -> cmbPlazos.setEnabled(cmbTipoTarjeta.getSelectedItem().equals("Crédito")));

        panel.add(new JLabel("Número de Tarjeta:"));
        panel.add(txtNumeroTarjeta);
        panel.add(new JLabel("Fecha de Caducidad (MM/AA):"));
        panel.add(txtFechaCaducidad);
        panel.add(new JLabel("Código de Seguridad:"));
        panel.add(txtCodigoSeguridad);
        panel.add(new JLabel("Tipo de Tarjeta:"));
        panel.add(cmbTipoTarjeta);
        panel.add(new JLabel("Plazos (solo crédito):"));
        panel.add(cmbPlazos);

        int option = JOptionPane.showConfirmDialog(this, panel, "Pago con Tarjeta", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String numeroTarjeta = txtNumeroTarjeta.getText();
            String fechaCaducidad = txtFechaCaducidad.getText();
            String codigoSeguridad = txtCodigoSeguridad.getText();

            // Validación simple
           /* if (numeroTarjeta.length() != 16 || !numeroTarjeta.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "El número de tarjeta debe tener 16 dígitos.");
                return;
            }

            if (codigoSeguridad.length() != 3 || !codigoSeguridad.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "El código de seguridad debe tener 3 dígitos.");
                return;
            }*/

            if (cmbTipoTarjeta.getSelectedItem().equals("Crédito") && cmbPlazos.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Seleccione un plazo para el pago a crédito.");
                return;
            }

            // Aquí podrías agregar la lógica para procesar el pago
            JOptionPane.showMessageDialog(this, "Pago procesado exitosamente.");
        }
        this.toFront();
        this.requestFocus();
        this.setAlwaysOnTop(false);
    }


    private double calcularTotal() {
        double total = 0.0;
        for (int i = 0; i < modeloListaProductos.size(); i++) {
            String item = modeloListaProductos.getElementAt(i);
            String[] parts = item.split(" - ");
            String nombreProducto = parts[0];
            double cantidad = Double.parseDouble(parts[1]);

            Producto producto = controler.findProductoByName(nombreProducto);
            if (producto != null) {
                total += producto.getPrecio() * cantidad;
            }
        }
        return total;
    }


    private void limpiarFormulario() {
        txtCedulaRUC.setText("");
        txtPlaca.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtCorreo.setText("");
        txtTelefono.setText("");
         txtDieselCantidad.setText("");
         txtExtraCantidad.setText("");
         txtSuperCantidad.setText("");
         txtSuperCantidadDinero.setText("");
         txtExtraCantidadDinero.setText("");
         txtDieselCantidadDinero.setText("");
         txtProducto.setText("");
         txtCantidad.setText("");
        modeloListaProductos.clear();
        lblTotal.setText("0.00");

        clienteEncontrado = null;
    }

}
