package fr.duminy.examples.codjo.releasetest.swing;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JTable.AUTO_RESIZE_OFF;

/**
 *
 */
public class SwingApplication extends JPanel {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Swing application");
        frame.setContentPane(new SwingApplication());
        frame.pack();
        frame.setVisible(true);
        frame.setSize(250, 120);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private final JTable table;
    private final AppTableModel model;

    public SwingApplication() {
        super(new BorderLayout());

        model = new AppTableModel();
        table = createTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton button = new JButton("Change color");
        button.setName("changeColorButton");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setRed(!model.isRed());
            }
        });
        add(button, BorderLayout.SOUTH);
    }


    private static JTable createTable(final AppTableModel model) {
        JTable table = new JTable(model);
        table.setName("colorTable");

        table.setPreferredScrollableViewportSize(new Dimension(250, 120));
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table,
                                                           Object value,
                                                           boolean isSelected,
                                                           boolean hasFocus,
                                                           int row,
                                                           int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(model.isRed() ? Color.RED : Color.GREEN);
                return c;
            }
        });
        setSize(table, 0);
        setSize(table, 1);
        table.setAutoResizeMode(AUTO_RESIZE_OFF);
        return table;
    }


    private static void setSize(JTable table, int column) {
        TableColumn tableColumn = table.getColumnModel().getColumn(column);
        int size = 100;
        tableColumn.setMinWidth(size);
        tableColumn.setMaxWidth(size);
        tableColumn.setPreferredWidth(size);
    }


    private static class AppTableModel extends AbstractTableModel {
        private static final String[] COLUMNS = {"English", "Fran\u00e7ais"};
        private static final String[] RED = {"red", "rouge"};
        private static final String[] GREEN = {"green", "vert"};

        private boolean red = true;

        public int getRowCount() {
            return 1;
        }


        @Override
        public String getColumnName(int column) {
            return COLUMNS[column];
        }


        public int getColumnCount() {
            return COLUMNS.length;
        }


        public Object getValueAt(int rowIndex, int columnIndex) {
            return (red ? RED : GREEN)[columnIndex];
        }


        public void setRed(boolean red) {
            this.red = red;
            fireTableRowsUpdated(0, 0);
        }


        public boolean isRed() {
            return red;
        }
    }
}
