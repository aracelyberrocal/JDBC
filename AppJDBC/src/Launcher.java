
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Launcher extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JTable table;

	private JTextField txtUsuario;

	private JTextField txtContrasena;

	private DefaultTableModel modelo;
	private JButton btnFin;
	private JButton btnAlta;
	private JButton btnCambiar;
	private JButton btnEliminar;
	private JScrollPane scrollPane;

	public static void main(String[] args) {

		Launcher frame = new Launcher();

		frame.setVisible(true);

	}

	public Launcher() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 450, 347);

		setTitle("Tablas");

		contentPane = new JPanel();
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				table.getSelectionModel().clearSelection();
				LimpiarCampos();
			}
		});

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		contentPane.setLayout(null);

		scrollPane = new JScrollPane();

		scrollPane.setBounds(21, 11, 403, 146);

		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int fila = table.getSelectedRow();
				txtUsuario.setText((String) modelo.getValueAt(fila, 0));
				txtContrasena.setText((String) modelo.getValueAt(fila, 1));
				UpdateBaja();
			}
		});

		modelo = new DefaultTableModel(

				new Object[][] {

						{ "Pedro", "1234" },

						{ "Luis", "admin" },

				},

				new String[] {

						"Usuario", "Contraseña"

				}

		);

		table.setModel(modelo);

		scrollPane.setViewportView(table);

		txtUsuario = new JTextField();
		txtUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				UpdateAlta();
				UpdateModificar();
			}

		});

		txtUsuario.setBounds(21, 168, 183, 20);

		contentPane.add(txtUsuario);

		txtUsuario.setColumns(10);

		txtContrasena = new JTextField();
		txtContrasena.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				UpdateAlta();
				UpdateModificar();
			}
		});

		txtContrasena.setBounds(227, 168, 197, 20);

		contentPane.add(txtContrasena);

		txtContrasena.setColumns(10);

		btnAlta = new JButton("");
		btnAlta.setIcon(new ImageIcon(Launcher.class.getResource("/icons/insert.png")));
		btnAlta.setEnabled(false);
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modelo.addRow(new String[] { txtUsuario.getText(), txtContrasena.getText() });
				LimpiarCampos();
			}
		});

		btnAlta.setBounds(54, 216, 56, 40);

		contentPane.add(btnAlta);

		btnCambiar = new JButton("");
		btnCambiar.setIcon(new ImageIcon(Launcher.class.getResource("/icons/modify.png")));
		btnCambiar.setEnabled(false);

		btnCambiar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				modelo.setValueAt(txtUsuario.getText(), fila, 0);
				modelo.setValueAt(txtContrasena.getText(), fila, 1);
				LimpiarCampos();
			}

		});

		btnCambiar.setBounds(224, 216, 47, 40);

		contentPane.add(btnCambiar);

		btnEliminar = new JButton("");
		btnEliminar.setIcon(new ImageIcon(Launcher.class.getResource("/icons/modify.png")));
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modelo.removeRow(table.getSelectedRow());
				LimpiarCampos();
			}
		});

		btnEliminar.setBounds(371, 216, 41, 40);

		contentPane.add(btnEliminar);

		btnFin = new JButton("Fin");
		btnFin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnFin.setBounds(335, 276, 89, 23);
		contentPane.add(btnFin);

	}

	protected void UpdateModificar() {

		if (txtUsuario.getText().length() == 0 || txtContrasena.getText().length() == 0
				|| table.getSelectedRow() == -1) {
			btnCambiar.setEnabled(false);
		} else {
			btnCambiar.setEnabled(true);
		}
	}

	protected void UpdateBaja() {
		if (table.getSelectedRow() == -1) {
			btnEliminar.setEnabled(false);
		} else {
			btnEliminar.setEnabled(true);
		}
	}

	protected void UpdateAlta() {

		if (txtUsuario.getText().length() == 0 || txtContrasena.getText().length() == 0) {
			btnAlta.setEnabled(false);
		} else {
			btnAlta.setEnabled(true);
		}
	}

	protected void LimpiarCampos() {
		txtUsuario.setText(null);
		txtContrasena.setText(null);
		UpdateAlta();
		UpdateBaja();
		UpdateModificar();
	}

}