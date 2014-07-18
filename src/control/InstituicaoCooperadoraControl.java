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
import modelo.InstituicaoCooperadora;

public class InstituicaoCooperadoraControl implements ActionListener
{
	InstituicaoCooperadoraView view;
	InstituicaoCooperadoraTabela tabela;
	Autocompletee autocp;
	InstituicaoCooperadora obj;

	public InstituicaoCooperadoraControl(InstituicaoCooperadoraView v,
			InstituicaoCooperadoraTabela t) throws Exception
	{
		this.view = v;
		this.tabela = t;
	}

	public InstituicaoCooperadoraControl(InstituicaoCooperadoraView v,
			Autocompletee t) throws Exception
	{
		this.view = v;
		this.autocp = t;
	}

	public InstituicaoCooperadoraControl(InstituicaoCooperadoraView v,
			InstituicaoCooperadoraTabela t, InstituicaoCooperadora o)
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
			InstituicaoCooperadora objt = classeView();
			if (obj == null)
			{
				long x = new InstituicaoCooperadoraDAO().inserir(objt);
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
				new InstituicaoCooperadoraDAO().atualizar(objt);
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

	public InstituicaoCooperadora classeView()
	{
		InstituicaoCooperadora x = new InstituicaoCooperadora();
		x.setId(view.id.getValor());
		x.setNome(view.nome.getText());
		return x;
	}

	public Object[] formatoTabela(InstituicaoCooperadora instituicaocooperadora)
	{
		return new Object[] {

		instituicaocooperadora.getId(), instituicaocooperadora.getNome() };
	}
}
