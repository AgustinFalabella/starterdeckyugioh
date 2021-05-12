package starterdeck;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;
import javax.swing.SwingConstants;


public class Deck {

	private JFrame frame;
	private JTextField textidCarta;
	private JTextField textTipoCarta;
	private JTextField textNombre;
	private JTextField textAtaque;
	private JTextField textDefensa;
	private JTextField textEfecto;
	

	/**
	 * Launch the application.
	 */
	Connection conexion=null;
	PreparedStatement preparedStatement=null;
	ResultSet resultSet = null;

	private void limpiarcuadrosdetexto() {
		textidCarta.setText(null);
		textTipoCarta.setText(null);
		textNombre.setText(null);
		textAtaque.setText(null);
		textDefensa.setText(null);
		textEfecto.setText(null);
		}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Deck window = new Deck();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Deck() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 586, 610);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JLabel lblidCarta = new JLabel("ID de la Carta");
		lblidCarta.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		lblidCarta.setBounds(10, 106, 89, 14);
		frame.getContentPane().add(lblidCarta);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		lblNombre.setBounds(10, 42, 71, 14);
		frame.getContentPane().add(lblNombre);
		
		textidCarta = new JTextField();
		textidCarta.setText("0");
		textidCarta.setBorder(null);
		textidCarta.setOpaque(false);
		textidCarta.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		textidCarta.setBounds(231, 103, 71, 20);
		frame.getContentPane().add(textidCarta);
		textidCarta.setColumns(10);
		
		textTipoCarta = new JTextField();
		textTipoCarta.setBorder(null);
		textTipoCarta.setOpaque(false);
		textTipoCarta.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		textTipoCarta.setBounds(358, 103, 150, 20);
		frame.getContentPane().add(textTipoCarta);
		textTipoCarta.setColumns(10);
		
		textNombre = new JTextField();
		textNombre.setHorizontalAlignment(SwingConstants.CENTER);
		textNombre.setBorder(null);
		textNombre.setOpaque(false);
		textNombre.setBackground(new Color(255, 255, 255));
		textNombre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		textNombre.setBounds(218, 58, 274, 34);
		frame.getContentPane().add(textNombre);
		textNombre.setColumns(10);
		
		textAtaque = new JTextField();
		textAtaque.setBorder(null);
		textAtaque.setOpaque(false);
		textAtaque.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 13));
		textAtaque.setBounds(392, 484, 33, 14);
		frame.getContentPane().add(textAtaque);
		textAtaque.setColumns(10);
		
		textDefensa = new JTextField();
		textDefensa.setBorder(null);
		textDefensa.setOpaque(false);
		textDefensa.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 13));
		textDefensa.setBounds(462, 484, 33, 14);
		frame.getContentPane().add(textDefensa);
		textDefensa.setColumns(10);
		
		textEfecto = new JTextField();
		textEfecto.setBorder(null);
		textEfecto.setOpaque(false);
		textEfecto.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 10));
		textEfecto.setBounds(217, 408, 275, 65);
		frame.getContentPane().add(textEfecto);
		textEfecto.setColumns(10);
		

		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conexion= Conexion.conectar();
				try {
					preparedStatement=conexion
							.prepareStatement("Insert into deck (idCarta, TipoCarta, Nombre, Ataque, Defensa, Efecto) values (?,?,?,?,?,?)");
					preparedStatement.setString(1, textidCarta.getText());
					preparedStatement.setString(2, textTipoCarta.getText());
					preparedStatement.setString(3, textNombre.getText());
					preparedStatement.setString(4, textAtaque.getText());
					preparedStatement.setString(5, textDefensa.getText());
					preparedStatement.setString(6, textEfecto.getText());
					
					int resultado= preparedStatement.executeUpdate();
					if (resultado>0) {
						JOptionPane.showMessageDialog(null, "Carta agregada correctamente");
						limpiarcuadrosdetexto();
						conexion.close();
					}else {
						JOptionPane.showMessageDialog(null, "No se pudo agregar al deck");
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Ocurrió un error con el acceso a la base de datos");
				}
				}
			});
		btnAgregar.setBounds(10, 241, 104, 23);
		frame.getContentPane().add(btnAgregar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conexion= Conexion.conectar();
				try {
					preparedStatement=conexion
							.prepareStatement("UPDATE deck SET TipoCarta =?, Nombre =?, Ataque =?, Defensa =?, Efecto =? where idCarta =? ");
					preparedStatement.setString(1, textTipoCarta.getText());
					preparedStatement.setString(2, textNombre.getText());
					preparedStatement.setString(3, textAtaque.getText());
					preparedStatement.setString(4, textDefensa.getText());
					preparedStatement.setString(5, textEfecto.getText());
					preparedStatement.setString(6, textidCarta.getText());
					int resultado= preparedStatement.executeUpdate();
					if (resultado>0) {
						JOptionPane.showMessageDialog(null, "Carta agregada correctamente");
						limpiarcuadrosdetexto();
						conexion.close();
					}else {
						JOptionPane.showMessageDialog(null, "No se pudo agregar al deck");
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Ocurrió un error con el acceso a la base de datos");
				}
				
				
				}
			});
		btnModificar.setBounds(10, 290, 104, 23);
		frame.getContentPane().add(btnModificar);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conexion= Conexion.conectar();
				try {
					preparedStatement=conexion
							.prepareStatement("DELETE FROM deck where Nombre=?");
					preparedStatement.setString(1, textNombre.getText());

															
					int resultado= preparedStatement.executeUpdate();
					if (resultado>0) {
						JOptionPane.showMessageDialog(null, "Carta eliminada correctamente");
						limpiarcuadrosdetexto();
						conexion.close();
					}else {
						JOptionPane.showMessageDialog(null, "No se pudo eliminar del deck");
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Ocurrió un error con el acceso a la base de datos");
				}
				
				
				}
			});
		btnBorrar.setBounds(10, 335, 104, 23);
		frame.getContentPane().add(btnBorrar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			System.exit(0);
			}
		});
		btnSalir.setBounds(449, 531, 104, 34);
		frame.getContentPane().add(btnSalir);
		
		JButton btnBuscarNombre = new JButton("Buscar");
		btnBuscarNombre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		btnBuscarNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conexion= Conexion.conectar();
				try {
					preparedStatement= conexion
							.prepareStatement("Select idCarta, TipoCarta, Ataque, Defensa, Efecto from deck where Nombre =?");
					preparedStatement.setString(1, textNombre.getText());
					resultSet=preparedStatement.executeQuery();
					
					if(resultSet.next()) {
						textidCarta.setText(resultSet.getString("idCarta"));
						textTipoCarta.setText(resultSet.getString("TipoCarta"));
						textAtaque.setText(resultSet.getString("Ataque"));
						textDefensa.setText(resultSet.getString("Defensa"));
						textEfecto.setText(resultSet.getString("Efecto"));
						
											
					}else {
						JOptionPane.showMessageDialog(null, "Carta no encontrada, ingrese una nueva búsqueda.");
					}
					
					conexion.close();
					
				}catch (SQLException e1) {
					JOptionPane.showMessageDialog(null,"Ocurrio un error en la conexion con la base de datos.");
				}
			}
			});
		btnBuscarNombre.setBounds(10, 72, 89, 23);
		frame.getContentPane().add(btnBuscarNombre);
		
		JButton btnBuscarID = new JButton("Buscar");
		btnBuscarID.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		btnBuscarID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conexion= Conexion.conectar();
				try {
					preparedStatement= conexion
							.prepareStatement("Select Nombre, TipoCarta, Ataque, Defensa, Efecto from deck where idCarta =?");
					preparedStatement.setString(1, textidCarta.getText());
					resultSet=preparedStatement.executeQuery();
					
					if(resultSet.next()) {
						textNombre.setText(resultSet.getString("Nombre"));
						textTipoCarta.setText(resultSet.getString("TipoCarta"));
						textAtaque.setText(resultSet.getString("Ataque"));
						textDefensa.setText(resultSet.getString("Defensa"));
						textEfecto.setText(resultSet.getString("Efecto"));
											
					}else {
						JOptionPane.showMessageDialog(null, "Código de carta no encontrado");
					}
					conexion.close();
					}catch (SQLException e1) {
					JOptionPane.showMessageDialog(null,"Ocurrio un error en la conexion con la base de datos");
				}
			}
			});
		btnBuscarID.setBounds(10, 131, 89, 23);
		frame.getContentPane().add(btnBuscarID);
		
		ImageIcon image1=new ImageIcon("C:/Users/Agustin/Downloads/fondo.png");
		JLabel label= new JLabel (new ImageIcon(Deck.class.getResource("/imagenes/fondo.png")), JLabel.CENTER);
		label.setBounds(188, 32, 333, 499);
		frame.getContentPane().add(label);
		label.setVerticalAlignment(JLabel.TOP);
		label.setHorizontalAlignment(JLabel.LEFT);
		label.setVerticalTextPosition(JLabel.BOTTOM);
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setForeground(new Color (125,70,50));
	}
}
