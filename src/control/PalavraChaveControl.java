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
import modelo.PalavraChave;

public class PalavraChaveControl implements ActionListener
{
	PalavraChaveView view;
	PalavraChaveTabela tabela;
	Autocompletee autocp;
	PalavraChave obj;

	public PalavraChaveControl(PalavraChaveView v, PalavraChaveTabela t)
			throws Exception
	{
		this.view = v;
		this.tabela = t;
	}

	public PalavraChaveControl(PalavraChaveView v, Autocompletee t)
			throws Exception
	{
		this.view = v;
		this.autocp = t;
	}

	public PalavraChaveControl(PalavraChaveView v, PalavraChaveTabela t,
			PalavraChave o) throws Exception
	{
		this.view = v;
		this.tabela = t;
		this.obj = o;
	}

	public void actionPerformed(ActionEvent ev)
	{
		try
		{
			PalavraChave objt = classeView();
			if (obj == null)
			{
				long x = new PalavraChaveDAO().inserir(objt);
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
				new PalavraChaveDAO().atualizar(objt);
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

	public PalavraChave classeView()
	{
		PalavraChave x = new PalavraChave();
		x.setId(view.id.getValor());
		x.setPalavra(view.palavra.getText());
		return x;
	}

	public Object[] formatoTabela(PalavraChave palavrachave)
	{
		return new Object[] {

		palavrachave.getId(), palavrachave.getPalavra() };
	}
}
