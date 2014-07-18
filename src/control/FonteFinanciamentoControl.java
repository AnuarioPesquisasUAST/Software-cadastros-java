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
import modelo.FonteFinanciamento;

public class FonteFinanciamentoControl implements ActionListener
{
	FonteFinanciamentoView view;
	FonteFinanciamentoTabela tabela;
	Autocompletee autocp;
	FonteFinanciamento obj;

	public FonteFinanciamentoControl(FonteFinanciamentoView v,
			FonteFinanciamentoTabela t) throws Exception
	{
		this.view = v;
		this.tabela = t;
	}

	public FonteFinanciamentoControl(FonteFinanciamentoView v, Autocompletee t)
			throws Exception
	{
		this.view = v;
		this.autocp = t;
	}

	public FonteFinanciamentoControl(FonteFinanciamentoView v,
			FonteFinanciamentoTabela t, FonteFinanciamento o) throws Exception
	{
		this.view = v;
		this.tabela = t;
		this.obj = o;
	}

	public void actionPerformed(ActionEvent ev)
	{
		try
		{
			FonteFinanciamento objt = classeView();
			if (obj == null)
			{
				long x = new FonteFinanciamentoDAO().inserir(objt);
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
				new FonteFinanciamentoDAO().atualizar(objt);
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

	public FonteFinanciamento classeView()
	{
		FonteFinanciamento x = new FonteFinanciamento();
		x.setId(view.id.getValor());
		x.setNome(view.nome.getText());
		return x;
	}

	public Object[] formatoTabela(FonteFinanciamento fontefinanciamento)
	{
		return new Object[] {

		fontefinanciamento.getId(), fontefinanciamento.getNome() };
	}
}
