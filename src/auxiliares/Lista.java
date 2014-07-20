package auxiliares;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import net.java.dev.designgridlayout.DesignGridLayout;

public class Lista extends JPanel
{
	JTextField pesquisa;
	ArrayList list;
	JButton adicionar, remover;
	JList jlist;

	public void inserir()
	{
		list.add(pesquisa.getText());
		((DefaultListModel) jlist.getModel()).addElement(pesquisa.getText());
	}

	public Lista(ArrayList lista)
	{
		pesquisa = new JTextField();

		preencher();
		if (lista != null)
		{
			list = lista;
			for (Object obj : lista)
				((DefaultListModel<Object>) jlist.getModel()).addElement(obj);
		}
	}

	// public Lista (Autocompletee e){
	//
	// }
	public Lista()
	{
		pesquisa = new JTextDec();
		preencher();
	}

	public void preencher()
	{
		list = new ArrayList();
		jlist = new JList(new DefaultListModel());

		adicionar = new JButton("Adicionar");
		adicionar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				inserir();
			}
		});
		remover = new JButton("Remover");
		remover.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int x = jlist.getSelectedIndex();
				if (x >= 0)
				{
					list.remove(x);
					((DefaultListModel) jlist.getModel()).removeElementAt(x);
				}
				else
					JOptionPane
							.showMessageDialog(null,
									"Nenhum item foi selecionado para remoção",
									"Alerta", JOptionPane.WARNING_MESSAGE);
			}
		});

		DesignGridLayout layout = new DesignGridLayout(this);
		layout.row().grid().add(pesquisa, adicionar, remover);
		layout.row().grid().add(new JScrollPane(jlist));
		setMinimumSize(getPreferredSize());
	}

	public ArrayList getList()
	{
		return list;
	}
}