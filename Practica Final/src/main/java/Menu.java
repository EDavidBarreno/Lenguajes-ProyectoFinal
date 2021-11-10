import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame{
    private JPanel panelFondo;
    private JButton analizadorButton;
    private JButton acercaDeButton;
    private JButton salirButton;

    public Menu(){
        add(panelFondo);
        setTitle("Analizador");
        setExtendedState(MAXIMIZED_BOTH);

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);


        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"De parte de nuestros Administradores\nle deseamos un lindo día."
                        , "Saliendo del sistema.", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        });
        acercaDeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Hecho por:\nEstuardo David Barreno Nimatuj\n201830233"
                        , "Soporte técnico", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        analizadorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuAnalizador menuAnalizador = new MenuAnalizador();
                menuAnalizador.show();
                dispose();
            }
        });
    }
}
