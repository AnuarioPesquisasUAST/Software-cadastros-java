package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
import auxiliares.Autocompletee;
import dao.*;
import view.*;
import modelo.*;
import modelo.Local;

public class LocalControl implements ActionListener
{
	LocalView view;
	LocalTabela tabela;
	Autocompletee autocp;
	Local obj;

	public LocalControl(LocalView v, LocalTabela t) throws Exception
	{
		this.view = v;
		this.tabela = t;
	}

	public LocalControl(LocalView v, Autocompletee t) throws Exception
	{
		this.view = v;
		this.autocp = t;
	}

	public LocalControl(LocalView v, LocalTabela t, Local o) throws Exception
	{
		this.view = v;
		this.tabela = t;
		this.obj = o;
	}

	public void actionPerformed(ActionEvent ev)
	{
		try
		{
			Local objt = classeView();
			if (obj == null)
			{
				long x = new LocalDAO().inserir(objt);
				objt.setId(x);
				JOptionPane.showMessageDialog(null,
						"Os dados foram inseridos com sucesso", "Sucesso", 0);
				if (tabela != null)
					tabela.adc(objt);
				else
					autocp.adicionar(objt);
			}
			else
			{
				new LocalDAO().atualizar(objt);
				JOptionPane.showMessageDialog(null,
						"Os dados foram inseridos com sucesso", "Sucesso", 1);
				tabela.edt(objt);
			}
			view.dispose();
		}
		catch (Exception e)
		{
			JOptionPane
					.showMessageDialog(
							null,
							"Verifique se os campos estão preenchidos corretamente ou se estão repetidos",
							"Erro", 0);
		}
	}

	public Local classeView()
	{
		Local x = new Local();
		x.setId(view.id.getValor());
		x.setCidade(view.cidade.getText());
		x.setEstado(view.estado.getText());
		x.setDescricao(view.descricao.getText());
		return x;
	}

	public Object[] formatoTabela(Local local)
	{
		return new Object[] {

		local.getId(), local.getCidade(), local.getEstado(),
				local.getDescricao() };
	}
}
