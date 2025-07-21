package util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.JCheckBox;
import javax.swing.Icon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CompletedColumnCellRenderer extends DefaultTableCellRenderer {
    private static final Icon GREEN_CHECKBOX_ICON = new Icon() {
        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            int size = 20;
            g.setColor(Color.GREEN);
            g.fillRect(x + 2, y + 2, size - 4, size - 4);
            g.setColor(Color.DARK_GRAY);
            g.drawRect(x + 2, y + 2, size - 4, size - 4);
            // Desenha o check em preto
            g.setColor(Color.BLACK);
            g.drawLine(x + 6, y + size / 2, x + size / 2, y + size - 6);
            g.drawLine(x + size / 2, y + size - 6, x + size - 6, y + 6);
        }
        @Override
        public int getIconWidth() { return 20; }
        @Override
        public int getIconHeight() { return 20; }
    };

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JCheckBox checkBox = new JCheckBox();
        checkBox.setSelected(Boolean.TRUE.equals(value));
        checkBox.setHorizontalAlignment(CENTER);
        if (Boolean.TRUE.equals(value)) {
            checkBox.setIcon(GREEN_CHECKBOX_ICON);
        } else {
            checkBox.setIcon(null);
        }
        checkBox.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
        checkBox.setOpaque(true);
        return checkBox;
    }
} 