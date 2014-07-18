package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.AreaConhecimento;
import view.AreaConhecimentoView;
import auxiliares.Autocompletee;
import dao.AreaConhecimentoDAO;

public class AreaConhecimentoControl implements ActionListener
{
	AreaConhecimentoView view;
	AreaConhecimentoTabela tabela;
	Autocompletee autocp;
	AreaConhecimento obj;

	public AreaConhecimentoControl(AreaConhecimentoView v,
			AreaConhecimentoTabela t) throws Exception
	{
		this.view = v;
		this.tabela = t;
	}

	public AreaConhecimentoControl(AreaConhecimentoView v, Autocompletee t)
			throws Exception
	{
		this.view = v;
		this.autocp = t;
	}

	public AreaConhecimentoControl(AreaConhecimentoView v,
			AreaConhecimentoTabela t, AreaConhecimento o) throws Exception
	{
		this.view = v;
		this.tabela = t;
		this.obj = o;
	}

	public void actionPerformed(ActionEvent ev)
	{
		try
		{
			AreaConhecimento objt = classeView();
			if (obj == null)
			{
				long x = new AreaConhecimentoDAO().inserir(objt);
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
				new AreaConhecimentoDAO().atualizar(objt);
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
			e.printStackTrace();
		}
	}

	public AreaConhecimento classeView()
	{
		AreaConhecimento x = new AreaConhecimento();
		x.setId(view.id.getValor());
		x.setNome(view.nome.getText());
		x.setCiencia(view.ciencia.getText());
		x.setAreaAvaliacao(view.areaAvaliacao.getText());
		return x;
	}

	public Object[] formatoTabela(AreaConhecimento areaconhecimento)
	{
		return new Object[] {

		areaconhecimento.getId(), areaconhecimento.getNome() };
	}
}
