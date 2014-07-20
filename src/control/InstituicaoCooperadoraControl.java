package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.InstituicaoCooperadora;
import view.InstituicaoCooperadoraView;
import auxiliares.Autocompletee;
import dao.InstituicaoCooperadoraDAO;

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
						"Os dados foram inseridos com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				if (tabela != null)
					tabela.adc(objt);
				else
					autocp.adicionar(objt);
			}
			else
			{
				new InstituicaoCooperadoraDAO().atualizar(objt);
				JOptionPane.showMessageDialog(null,
						"Os dados foram atualizados com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
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
							"Alerta", JOptionPane.WARNING_MESSAGE);
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
