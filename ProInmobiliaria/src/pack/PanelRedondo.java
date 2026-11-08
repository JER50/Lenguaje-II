package pack;

import javax.swing.*;
import java.awt.*;
public class PanelRedondo extends JPanel {
    private final int radio;

    public PanelRedondo(int radio) {
        this.radio = radio;
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radio, radio);
        g2.setColor(Color.GRAY);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radio, radio);
        g2.dispose();
        super.paintComponent(g);
    }
}
