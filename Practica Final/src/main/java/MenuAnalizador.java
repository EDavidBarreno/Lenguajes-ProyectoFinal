import Analzador.AnalisisLexico;
import Analzador.Tokens;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

public class MenuAnalizador extends JFrame {
    Tokens analisisLexico;
    AnalisisLexico modelo = null;

    private JPanel PanelAnalizador;
    private JButton regresarButton;
    private JButton copiarButton;
    private JButton abirButton;
    private JButton deshacerButton;
    private JButton nuevoButton;
    private JButton guardarButton;
    private JButton guardarComoButton;
    private JButton pegarButton;
    private JButton rehacerButton;
    private JTextArea textAreaTexto;
    private JTable tableTokens;
    private JTextArea textAreaErrores;
    private JLabel contadorErrores;
    private JButton analizarButton;


    JFileChooser seleccionar = new JFileChooser();
    File archivo;
    FileInputStream entrada;
    FileOutputStream salida;

    public String AbirArchivo(File archivo){
        String documento="";
        try {
            entrada = new FileInputStream(archivo);
            int ascci;
            while((ascci = entrada.read()) !=-1){
                char caracter = (char)ascci;
                documento+=caracter;
            }
        }catch (Exception e){
        }
        return documento;
    }
    public String GuardarArchivo(File archivo, String documento){
        String mensaje=null;
        try {
            salida=new FileOutputStream(archivo);
            byte[] bytxt=documento.getBytes();
            salida.write(bytxt);
            mensaje="Archivo Guardado";
        }catch (Exception e){
        }
        return mensaje;
    }



    public MenuAnalizador(){
        analisisLexico = new Tokens();
        modelo = new AnalisisLexico(analisisLexico.getToken1(), analisisLexico.getLexema1());

        add(PanelAnalizador);
        setTitle("Analizador");
        setExtendedState(MAXIMIZED_BOTH);

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        textAreaErrores.disable();

        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu();
                menu.show();
                dispose();
            }
        });
        abirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(seleccionar.showDialog(null, "Abrir") == JFileChooser.APPROVE_OPTION){
                    archivo=seleccionar.getSelectedFile();
                    if(archivo.canRead()){
                        if(archivo.getName().endsWith("txt")){
                            String documento=AbirArchivo(archivo);
                            textAreaTexto.setText(documento);
                            JOptionPane.showMessageDialog(null,"Se cargo el archivo correctamente."
                                    , "Carga correcta", JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            JOptionPane.showMessageDialog(null, "Archivo no compatible.", "Carga incorrecta", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(seleccionar.showDialog(null, "Guardar")==JFileChooser.APPROVE_OPTION){
                    archivo=seleccionar.getSelectedFile();
                    if(archivo.getName().endsWith(".txt")){
                        String Documento=textAreaTexto.getText();
                        String mensaje=GuardarArchivo(archivo, Documento);
                        if(mensaje !=null){
                            JOptionPane.showMessageDialog(null, mensaje);
                        }else {
                            JOptionPane.showMessageDialog(null, "Archivo no compatible");
                        }
                    }else JOptionPane.showMessageDialog(null, "Guardar documento de Texto");
                }
            }
        });
        guardarComoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(seleccionar.showDialog(null, "Guardar")==JFileChooser.APPROVE_OPTION){
                    archivo=seleccionar.getSelectedFile();
                    if(archivo.getName().endsWith(".txt")){
                        String Documento=textAreaTexto.getText();
                        String mensaje=GuardarArchivo(archivo, Documento);
                        if(mensaje !=null){
                            JOptionPane.showMessageDialog(null, mensaje);
                        }else {
                            JOptionPane.showMessageDialog(null, "Archivo no compatible");
                        }
                    }else JOptionPane.showMessageDialog(null, "Guardar como");
                }
            }
        });
        nuevoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textAreaTexto.setText("");
                textAreaErrores.setText("");
                JOptionPane.showMessageDialog(null, "Se limpio correctamente el área de texto.", "Limpiar",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        tableTokens.addComponentListener(new ComponentAdapter() {
        });
        analizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                analisisLexico.Inicia();
                analisisLexico.AnalisisAutomata(textAreaTexto.getText());
                modelo = new AnalisisLexico(analisisLexico.getToken1(), analisisLexico.getLexema1());
                tableTokens.setModel(modelo);
            }
        });
    }
}
