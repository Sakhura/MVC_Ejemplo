
package controlador;

import java.awt.event.ActionListener;
import modelo.Persona;
import modelo.PersonaDAO;
import vista.vista;
import modelo.PersonaDAO;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Sabina
 */
public class Controlador implements ActionListener {
    
    PersonaDAO dao =new PersonaDAO();
    Persona p = new Persona();
    vista vista= new vista();
    DefaultTableModel modelo = new DefaultTableModel();

    public Controlador (vista v) {
       this.vista= v;
       this.vista.btnListar.addActionListener(this);
       this.vista.btnAgregar.addActionListener(this);
       this.vista.btnEditar.addActionListener(this);
       this.vista.btnDelete.addActionListener(this);
       this.vista.btnActualizar.addActionListener(this);
       this.vista.btnNuevo.addActionListener(this);
     
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnListar) {
            limpiarTabla();
            listar(vista.tabla);
            nuevo();
        }
        if (e.getSource() == vista.btnAgregar) {
            add();
            listar(vista.tabla);
            nuevo();
        }
        if (e.getSource() == vista.btnEditar) {
            int fila = vista.tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Debee Seleccionar Una fila..!!");
            } else {
                int id = Integer.parseInt((String) vista.tabla.getValueAt(fila, 0).toString());
                String nom = (String) vista.tabla.getValueAt(fila, 1);
                String correo = (String) vista.tabla.getValueAt(fila, 2);
                String cel = (String) vista.tabla.getValueAt(fila, 3);
                vista.txtId.setText("" + id);
                vista.txtNom.setText(nom);
                vista.txtCorreo.setText(correo);
                vista.txtCel.setText(cel);
            }
        }
        if (e.getSource() == vista.btnActualizar) {
            Actualizar();
            listar(vista.tabla);
            nuevo();

        }
        if (e.getSource() == vista.btnDelete) {
            delete();
            listar(vista.tabla);
            nuevo();
        }
        if (e.getSource() == vista.btnNuevo) {
            nuevo();
        }

    }

    void nuevo() {
        vista.txtId.setText("");
        vista.txtNom.setText("");
        vista.txtCel.setText("");
        vista.txtCorreo.setText("");
        vista.txtNom.requestFocus();
    }

    public void delete() {
        int fila = vista.tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Debe Seleccionar una Fila");
        } else {
            int id = Integer.parseInt((String) vista.tabla.getValueAt(fila, 0).toString());
            dao.Delete(id);
            System.out.println("El Resultado es" + id);
            JOptionPane.showMessageDialog(vista, "Usuario Eliminado");
        }
        limpiarTabla();
    }

    public void add() {
        String nom = vista.txtNom.getText();
        String correo = vista.txtCorreo.getText();
        String cel = vista.txtCel.getText();
        p.setNombre(nom);
        p.setCorreo(correo);
        p.setCelular(cel);
        int r = dao.agregar(p);
        if (r == 1) {
            JOptionPane.showMessageDialog(vista, "Usuario Agregado con Exito.");
        } else {
            JOptionPane.showMessageDialog(vista, "No se agrego usuario");
        }
        limpiarTabla();
    }

    public void Actualizar() {
        if (vista.txtId.getText().equals("")) {
            JOptionPane.showMessageDialog(vista, "No se Identifica el Id debe selecionar la opcion Editar");
        } else {
            int id = Integer.parseInt(vista.txtId.getText());
            String nom = vista.txtNom.getText();
            String correo = vista.txtCorreo.getText();
            String cel = vista.txtCel.getText();
            p.setId(id);
            p.setNombre(nom);
            p.setCorreo(correo);
            p.setCelular(cel);
            int r = dao.Actualizar(p);
            if (r == 1) {
                JOptionPane.showMessageDialog(vista, "Usuario Actualizado con Exito.");
            } else {
                JOptionPane.showMessageDialog(vista, "No se pudo actualizar Usuario");
            }
        }
        limpiarTabla();
    }

    public void listar(JTable tabla) {
        centrarCeldas(tabla);
        modelo = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modelo);
        List<Persona> lista = dao.listar();
        Object[] objeto = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getNombre();
            objeto[2] = lista.get(i).getCorreo();
            objeto[3] = lista.get(i).getCelular();
            modelo.addRow(objeto);
        }
        tabla.setRowHeight(35);
        tabla.setRowMargin(10);

    }

    void centrarCeldas(JTable tabla) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < vista.tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    void limpiarTabla() {
        for (int i = 0; i < vista.tabla.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }
} // fin clase principal Controlador
