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
import modelo.InstituicaoSubmissao;

public class InstituicaoSubmissaoControl implements ActionListener
{
	InstituicaoSubmissaoView view;
	InstituicaoSubmissaoTabela tabela;
	Autocompletee autocp;
	InstituicaoSubmissao obj;

	public InstituicaoSubmissaoControl(InstituicaoSubmissaoView v,
			InstituicaoSubmissaoTabela t) throws Exception
	{
		this.view = v;
		this.tabela = t;
	}

	public InstituicaoSubmissaoControl(InstituicaoSubmissaoView v,
			Autocompletee t) throws Exception
	{
		this.view = v;
		this.autocp = t;
	}

	public InstituicaoSubmissaoControl(InstituicaoSubmissaoView v,
			InstituicaoSubmissaoTabela t, InstituicaoSubmissao o)
			throws Exception
	{
		this.view = v;
		this.tabela = t;
		this.obj = o;
	}

	public void actionPerformed(ActionEvent ev)
	{
		try
		{
			InstituicaoSubmissao objt = classeView();
			if (obj == null)
			{
				long x = new InstituicaoSubmissaoDAO().inserir(objt);
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
				new InstituicaoSubmissaoDAO().atualizar(objt);
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

	public InstituicaoSubmissao classeView()
	{
		InstituicaoSubmissao x = new InstituicaoSubmissao();
		x.setId(view.id.getValor());
		x.setNome(view.nome.getText());
		return x;
	}

	public Object[] formatoTabela(InstituicaoSubmissao instituicaosubmissao)
	{
		return new Object[] {

		instituicaosubmissao.getId(), instituicaosubmissao.getNome() };
	}
}
