package com.example.demo.View;

import com.example.demo.Controller.Controler;
import com.example.demo.Model.Producto;
import com.example.demo.Model.Usuario;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jdatepicker.impl.JDatePanelImpl;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import static java.lang.String.format;

public class VistaJefe extends JFrame {
    private JTextArea txtInventario;
    private JTextArea txtEmpleados;
    private JDatePickerImpl datePicker;
    private Controler controler;
    private JTable tableEmpleados;

    public VistaJefe(Controler controler) {
        this.controler = controler;
        setUndecorated(true);
        setLayout(new BorderLayout());

        // Configuración de pantalla completa
        GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = graphics.getDefaultScreenDevice();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);

        ImageIcon iconoUsuario = new ImageIcon("src/main/resources/fondo1.png");

        // Ajustar tamaño de la imagen (opcional)
        Image imagenUsuario = iconoUsuario.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        iconoUsuario = new ImageIcon(imagenUsuario);

        // Crear el JLabel con la imagen
        JLabel lblImagenUsuario = new JLabel(iconoUsuario);
        lblImagenUsuario.setBounds(10, 5, 60, 50);

        JPanel barraSuperior = new JPanel();
        barraSuperior.setLayout(null);
        barraSuperior.setBackground(new Color(0, 0, 0, 150));
        barraSuperior.setPreferredSize(new Dimension(getWidth(), 55));

        JLabel lblNombreUsuario = new JLabel("Administrador: " + controler.nameUser());
        lblNombreUsuario.setFont(new Font("Arial", Font.BOLD, 16));
        lblNombreUsuario.setForeground(Color.WHITE);
        lblNombreUsuario.setBounds(75, 10, 300, 30);


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

        add(barraSuperior, BorderLayout.NORTH);
        barraSuperior.add(lblNombreUsuario);
        barraSuperior.add(lblImagenUsuario);
        barraSuperior.add(lblImagenSalir);


        JTabbedPane tabbedPane = new JTabbedPane();

        // Panel para la pestaña de Estadísticas
        JPanel panelEstadisticas = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("src/main/resources/fondoEstadisticas.jpg");
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };


        JPanel datePanel = new JPanel();
        panelEstadisticas.add(datePanel, BorderLayout.NORTH);
        panelEstadisticas.add(createChartPanel(), BorderLayout.CENTER);

        tabbedPane.addTab("Ventas por Producto", panelEstadisticas);

        JPanel panelVentaPorEmpleado = createPanelVentaPorEmpleado();
        tabbedPane.addTab("Venta por Empleado", panelVentaPorEmpleado);



        // Panel para la pestaña de Inventario
        JPanel panelInventario = new JPanel(new BorderLayout());
        tabbedPane.addTab("Inventario", panelInventario);

       // Panel para agregar producto nuevo
        JPanel panelAgregarProducto = new JPanel(new GridBagLayout());
        panelAgregarProducto.setBorder(BorderFactory.createTitledBorder("Agregar Nuevo Producto"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        panelAgregarProducto.add(new JLabel("Nombre del Producto:"), gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JTextField txtNombreProducto = new JTextField(20);
        panelAgregarProducto.add(txtNombreProducto, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        panelAgregarProducto.add(new JLabel("Precio:"), gbc);

        gbc.gridx = 1;
        JTextField txtPrecioProducto = new JTextField(10);
        panelAgregarProducto.add(txtPrecioProducto, gbc);

        gbc.gridx = 2;
        panelAgregarProducto.add(new JLabel("Stock:"), gbc);

        gbc.gridx = 3;
        JTextField txtStockProducto = new JTextField(10);
        panelAgregarProducto.add(txtStockProducto, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 10, 10, 10);
        JButton btnAgregarProducto = new JButton("Agregar Producto");
        panelAgregarProducto.add(btnAgregarProducto, gbc);


        btnAgregarProducto.addActionListener(e -> {
            String nombre = txtNombreProducto.getText();
            String precioStr = txtPrecioProducto.getText();
            String stockStr = txtStockProducto.getText();

            if (nombre.isEmpty() || precioStr.isEmpty() || stockStr.isEmpty()) {
                JOptionPane.showMessageDialog(panelInventario, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {


                double precio = Double.parseDouble(precioStr);
                double stock = Integer.parseInt(stockStr);


                Producto producto = new Producto();
                producto.setNombre(nombre);
                producto.setPrecio(precio);
                producto.setStock(stock);
                controler.agregarProductos(producto);

                // Actualizar el área de inventario
                txtInventario.append("Producto agregado: " + nombre + ", Precio: " + precio + ", Stock: " + stock + "\n");

                // Limpiar campos de texto
                txtNombreProducto.setText("");
                txtPrecioProducto.setText("");
                txtStockProducto.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panelInventario, "Ingrese un precio y stock válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panelInventario.add(panelAgregarProducto, BorderLayout.NORTH);
       // Panel para actualizar el stock de un producto existente
        JPanel panelActualizarStock = new JPanel(new GridBagLayout());
        panelActualizarStock.setBorder(BorderFactory.createTitledBorder("Actualizar Stock de Producto"));

        GridBagConstraints gbcUpdate = new GridBagConstraints();
        gbcUpdate.gridx = 0;
        gbcUpdate.gridy = 0;
        gbcUpdate.anchor = GridBagConstraints.WEST;
        gbcUpdate.insets = new Insets(10, 10, 10, 10);

        panelActualizarStock.add(new JLabel("Nombre del Producto:"), gbcUpdate);

        gbcUpdate.gridx = 1;
        gbcUpdate.gridwidth = 2;
        gbcUpdate.fill = GridBagConstraints.HORIZONTAL;
        JTextField txtNombreProductoUpdate = new JTextField(20);
        panelActualizarStock.add(txtNombreProductoUpdate, gbcUpdate);

        gbcUpdate.gridx = 0;
        gbcUpdate.gridy = 1;
        gbcUpdate.gridwidth = 1;
        gbcUpdate.fill = GridBagConstraints.NONE;
        panelActualizarStock.add(new JLabel("Nuevo Stock:"), gbcUpdate);

        gbcUpdate.gridx = 1;
        JTextField txtNuevoStock = new JTextField(10);
        panelActualizarStock.add(txtNuevoStock, gbcUpdate);

        gbcUpdate.gridx = 2;
        gbcUpdate.gridwidth = 1;
        gbcUpdate.fill = GridBagConstraints.HORIZONTAL;
        gbcUpdate.insets = new Insets(20, 10, 10, 10);
        JButton btnActualizarStock = new JButton("Actualizar Stock");
        panelActualizarStock.add(btnActualizarStock, gbcUpdate);


        btnActualizarStock.addActionListener(e -> {
            String nombreProducto = txtNombreProductoUpdate.getText();
            String nuevoStockStr = txtNuevoStock.getText();

            if (nombreProducto.isEmpty() || nuevoStockStr.isEmpty()) {
                JOptionPane.showMessageDialog(panelInventario, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Optional<Producto> optionalProducto = Optional.ofNullable(controler.findProductoByName(nombreProducto));
            try {
                int nuevoStock = Integer.parseInt(nuevoStockStr);
                Producto producto = optionalProducto.get();
                double StockNuevo = producto.getStock() + (int) nuevoStock;
                // Llamar al controlador para actualizar el stock del producto
                boolean  actualizado =controler.actualizarStock(producto, StockNuevo);


                if (actualizado) {
                    txtInventario.append("Stock actualizado para: " + nombreProducto + ", Nuevo Stock: " + StockNuevo + "\n");
                    // Aquí podrías añadir lógica adicional si deseas refrescar la tabla de productos o algún otro componente
                } else {
                    JOptionPane.showMessageDialog(panelInventario, "No se encontró un producto con el nombre especificado.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                // Limpiar campos de texto
                txtNombreProductoUpdate.setText("");
                txtNuevoStock.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panelInventario, "Ingrese un nuevo stock válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        panelInventario.add(panelActualizarStock, BorderLayout.SOUTH);

        JPanel panelListaProductos = new JPanel(new BorderLayout());
        panelListaProductos.setBorder(BorderFactory.createTitledBorder("Lista de Productos"));

        txtInventario = new JTextArea(10, 30);
        txtInventario.setEditable(false);
        JScrollPane scrollPaneInventario = new JScrollPane(txtInventario);
        panelListaProductos.add(scrollPaneInventario, BorderLayout.CENTER);
        // Cargar los datos iniciales del inventario
        cargarDatosInventario();

        panelInventario.add(panelListaProductos, BorderLayout.CENTER);

        // Panel para la pestaña de Empleados
        JPanel panelEmpleados = new JPanel(new BorderLayout());
        txtEmpleados = new JTextArea();
        txtEmpleados.setEditable(false);
        JScrollPane scrollPaneEmpleados = new JScrollPane(txtEmpleados);
        panelEmpleados.add(scrollPaneEmpleados, BorderLayout.CENTER);

        JPanel panelEmpleados1 = new JPanel(new BorderLayout());
        panelEmpleados1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espacio alrededor del borde

        // Panel para agregar empleado
        JPanel panelAgregarEmpleado = crearPanelAgregarEmpleado();
        panelEmpleados1.add(panelAgregarEmpleado, BorderLayout.NORTH);

        // Panel para eliminar empleado
        JPanel panelEliminarEmpleado = crearPanelEliminarEmpleado();
        panelEmpleados1.add(panelEliminarEmpleado, BorderLayout.CENTER);

        // Panel para mostrar todos los empleados en una tabla
        JPanel panelListaEmpleados = crearPanelListaEmpleados();
        panelEmpleados1.add(panelListaEmpleados, BorderLayout.SOUTH);

        panelEmpleados.add(panelEmpleados1, BorderLayout.CENTER);
        tabbedPane.addTab("Empleados", panelEmpleados);

        add(tabbedPane, BorderLayout.CENTER);



        device.setFullScreenWindow(this);
        setVisible(true);
    }

    public void cargarDatosInventario() {

        List<Producto> productos = controler.cargarDatosProductos(); // Asumiendo que tienes un método en el controlador para obtener los productos


        txtInventario.setText("");


        for (Producto producto : productos) {
            String infoProducto = format("Nombre: %s, Precio: %.2f, Stock: %.2f\n", producto.getNombre(), producto.getPrecio(), producto.getStock());
            txtInventario.append(infoProducto);
        }
    }
    private JPanel createPanelVentaPorEmpleado() {
        JPanel panelVentaPorEmpleado = new JPanel(new BorderLayout());


        panelVentaPorEmpleado.add(createPeriodComboBox(), BorderLayout.NORTH);
        panelVentaPorEmpleado.add(createChartPanel2(), BorderLayout.CENTER);

        return panelVentaPorEmpleado;
    }

    private JComboBox<String> createPeriodComboBox() {
        String[] periods = {"Día", "Semana", "Mes", "Año"};
        JComboBox<String> comboBox = new JComboBox<>(periods);
        comboBox.addActionListener(e -> {
            java.util.Date selectedDate = (java.util.Date) datePicker.getModel().getValue();

            if (selectedDate != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(selectedDate);

                switch ((String) comboBox.getSelectedItem()) {
                    case "Día":
                        System.out.println("Período: " + selectedDate + " a " + selectedDate);
                        break;
                    case "Semana":
                        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                        Date startOfWeek = calendar.getTime();
                        calendar.add(Calendar.DAY_OF_WEEK, 6);
                        Date endOfWeek = calendar.getTime();
                        System.out.println("Período: " + startOfWeek + " a " + endOfWeek);
                        break;
                    case "Mes":
                        calendar.set(Calendar.DAY_OF_MONTH, 1);
                        Date startOfMonth = calendar.getTime();
                        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                        Date endOfMonth = calendar.getTime();
                        System.out.println("Período: " + startOfMonth + " a " + endOfMonth);
                        break;
                    case "Año":
                        int year = calendar.get(Calendar.YEAR); // Obtener el año de la fecha seleccionada
                        calendar.set(Calendar.MONTH, Calendar.JANUARY);
                        calendar.set(Calendar.DAY_OF_MONTH, 1);
                        Date startOfYear = calendar.getTime();
                        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
                        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                        Date endOfYear = calendar.getTime();
                        System.out.println("Período: " + startOfYear + " a " + endOfYear);
                        break;
                }
            } else {
                System.out.println("Por favor, selecciona una fecha.");
            }
        });
        return comboBox;
    }

    private ChartPanel createChartPanel() {
        DefaultCategoryDataset dataset = createDataset();

        String chartTitle = "Productos más Vendidos";
        String categoryAxisLabel = "Producto";
        String valueAxisLabel = "Cantidad Vendida";

        JFreeChart chart = ChartFactory.createBarChart(chartTitle, categoryAxisLabel, valueAxisLabel, dataset);

        return new ChartPanel(chart);
    }


    private ChartPanel createChartPanel2() {
        DefaultCategoryDataset dataset = createDataset2();

        // Verificar si el dataset tiene datos
        if (dataset.getColumnCount() == 0) {

            return new ChartPanel(ChartFactory.createBarChart(
                    "No hay datos disponibles", "Empleado", "Dinero Vendido", null));
        }

        String chartTitle = "Dinero Vendido por Empleado";
        String categoryAxisLabel = "Empleado";
        String valueAxisLabel = "Dinero Vendido";

        JFreeChart chart = ChartFactory.createBarChart(chartTitle, categoryAxisLabel, valueAxisLabel, dataset);

        return new ChartPanel(chart);
    }


    private DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        List<Producto> productos = controler.cargarDatosProductos();
        for (Producto producto : productos) {
            double cantidadVendida = controler.someBusinessMethod(producto.getId());
            dataset.addValue(cantidadVendida, "Productos", producto.getNombre());
        }

        return dataset;
    }
    private DefaultCategoryDataset createDataset2() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        List<Usuario> empleados = controler.buscarUsuariosPorRol("EMPLEADO");
        for (Usuario empleado : empleados) {
            double totalVentas = controler.getTotalVentasByUsuarioId(empleado.getId());
            dataset.addValue(totalVentas, "Ventas", empleado.getUsername());
        }

        return dataset;
    }
    private JPanel crearPanelAgregarEmpleado() {
        JPanel panelAgregarEmpleado = new JPanel(new BorderLayout());
        panelAgregarEmpleado.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 1), "Agregar Empleado",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK));

        JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 10, 5)); // Espacio entre los componentes
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espacio dentro del borde

        panelFormulario.add(new JLabel("Nombre:"));
        JTextField txtNombreEmpleado = new JTextField();
        panelFormulario.add(txtNombreEmpleado);

        panelFormulario.add(new JLabel("Apellido:"));
        JTextField txtApellidoEmpleado = new JTextField();
        panelFormulario.add(txtApellidoEmpleado);

        panelFormulario.add(new JLabel("Username:"));
        JTextField txtUsernameEmpleado = new JTextField();
        panelFormulario.add(txtUsernameEmpleado);

        panelFormulario.add(new JLabel("Password:"));
        JPasswordField txtPasswordEmpleado = new JPasswordField();
        panelFormulario.add(txtPasswordEmpleado);

        panelAgregarEmpleado.add(panelFormulario, BorderLayout.CENTER);

        JButton btnAgregarEmpleado = new JButton("Agregar Empleado");
        btnAgregarEmpleado.setFont(new Font("Arial", Font.PLAIN, 14)); // Tamaño de la fuente
        panelAgregarEmpleado.add(btnAgregarEmpleado, BorderLayout.SOUTH);

        btnAgregarEmpleado.addActionListener(e -> {
            String nombre = txtNombreEmpleado.getText();
            String apellido = txtApellidoEmpleado.getText();
            String username = txtUsernameEmpleado.getText();
            String password = new String(txtPasswordEmpleado.getPassword());
            txtEmpleados.append("Empleado: " + nombre + " " + apellido + ", Username: " + username + "\n");
            txtNombreEmpleado.setText("");
            txtApellidoEmpleado.setText("");
            txtUsernameEmpleado.setText("");
            txtPasswordEmpleado.setText("");
            Usuario user = new Usuario();
            user.setNombre(nombre);
            user.setApellido(apellido);
            user.setUsername(username);
            user.setPassword(password);
            user.setRol("EMPLEADO");
            controler.saveEmpleado(user);
        });

        return panelAgregarEmpleado;
    }

    // Método para crear el panel de eliminar empleado
    private JPanel crearPanelEliminarEmpleado() {
        JPanel panelEliminarEmpleado = new JPanel(new BorderLayout());
        panelEliminarEmpleado.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 1), "Eliminar Empleado",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK));

        JPanel panelEliminarFormulario = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5)); // Alineación y espacio entre componentes
        panelEliminarFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espacio dentro del borde

        panelEliminarFormulario.add(new JLabel("Username a Eliminar:"));
        JTextField txtUsernameEliminar = new JTextField(15);
        panelEliminarFormulario.add(txtUsernameEliminar);

        JButton btnEliminarEmpleado = new JButton("Eliminar");
        btnEliminarEmpleado.setFont(new Font("Arial", Font.PLAIN, 14)); // Tamaño de la fuente
        panelEliminarFormulario.add(btnEliminarEmpleado);

        btnEliminarEmpleado.addActionListener(e -> {
            String usernameEliminar = txtUsernameEliminar.getText();
            controler.deleteUser(usernameEliminar);
            txtEmpleados.append("Empleado eliminado: " + usernameEliminar + "\n");
            txtUsernameEliminar.setText("");
        });

        panelEliminarEmpleado.add(panelEliminarFormulario, BorderLayout.NORTH);

        return panelEliminarEmpleado;
    }

    // Método para crear el panel de lista de empleados
    private JPanel crearPanelListaEmpleados() {
        JPanel panelListaEmpleados = new JPanel(new BorderLayout());
        panelListaEmpleados.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 1), "Todos los Empleados",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK));

        JScrollPane scrollPaneEmpleados = new JScrollPane();
        tableEmpleados = new JTable();
        scrollPaneEmpleados.setViewportView(tableEmpleados);

        panelListaEmpleados.add(scrollPaneEmpleados, BorderLayout.CENTER);

        actualizarTablaEmpleados();

        return panelListaEmpleados;
    }

    // Método para actualizar la tabla de empleados
    private void actualizarTablaEmpleados() {
        // Dummy data de empleados (puedes reemplazarlo con datos reales)
        List<Usuario> empleados = controler.buscarUsuariosPorRol("EMPLEADO");

        // Crear modelo de tabla y asignar datos
        String[] columnas = {"Nombre", "Apellido", "Username"};
        Object[][] datos = new Object[empleados.size()][3];
        for (int i = 0; i < empleados.size(); i++) {
            Usuario empleado = empleados.get(i);
            datos[i][0] = empleado.getNombre();
            datos[i][1] = empleado.getApellido();
            datos[i][2] = empleado.getUsername();
        }

        DefaultTableModel modelo = new DefaultTableModel(datos, columnas);
        tableEmpleados.setModel(modelo);
    }



}
