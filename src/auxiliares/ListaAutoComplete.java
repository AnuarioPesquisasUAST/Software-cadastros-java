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

public class ListaAutoComplete extends JPanel
{
	Autocompletee pesquisa;
	ArrayList list;
	JButton adicionar, remover, novo;
	JList jlist;

	public void inserir()
	{

		list.add(pesquisa.getSelected());
		((DefaultListModel) jlist.getModel())
				.addElement(pesquisa.getSelected());
	}

	public ListaAutoComplete(ArrayList opcoes, ArrayList lista, JButton button)
	{
		pesquisa = new Autocompletee(opcoes);
		novo = button;
		preencher();
		if (lista != null)
		{
			list = lista;
			for (Object obj : lista)
				((DefaultListModel<Object>) jlist.getModel()).addElement(obj);
		}
	}

	public ListaAutoComplete(ArrayList opcoes, ArrayList lista)
	{
		pesquisa = new Autocompletee(opcoes);

		preencher();
		if (lista != null)
		{
			list = lista;
			for (Object obj : lista)
				((DefaultListModel<Object>) jlist.getModel()).addElement(obj);
		}
	}

	public ListaAutoComplete(ArrayList opcoes)
	{
		pesquisa = new Autocompletee(opcoes);

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
				if (pesquisa.getSelectedIndex() >= 0)
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
		if (novo != null)
			layout.row().grid().add(pesquisa, adicionar, remover, novo);
		else
			layout.row().grid().add(pesquisa, adicionar, remover);
		layout.row().grid().add(new JScrollPane(jlist));
		setMinimumSize(getPreferredSize());
	}

	public ArrayList getList()
	{
		return list;
	}

	public Autocompletee getAutocompletee()
	{
		return pesquisa;
	}
}