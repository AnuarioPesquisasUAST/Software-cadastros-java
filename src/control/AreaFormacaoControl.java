package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.AreaFormacao;
import view.AreaFormacaoView;
import auxiliares.Autocompletee;
import dao.AreaFormacaoDAO;

public class AreaFormacaoControl implements ActionListener
{
	AreaFormacaoView view;
	AreaFormacaoTabela tabela;
	Autocompletee autocp;
	AreaFormacao obj;

	public AreaFormacaoControl(AreaFormacaoView v, AreaFormacaoTabela t)
			throws Exception
	{
		this.view = v;
		this.tabela = t;
	}

	public AreaFormacaoControl(AreaFormacaoView v, Autocompletee t)
			throws Exception
	{
		this.view = v;
		this.autocp = t;
	}

	public AreaFormacaoControl(AreaFormacaoView v, AreaFormacaoTabela t,
			AreaFormacao o) throws Exception
	{
		this.view = v;
		this.tabela = t;
		this.obj = o;
	}

	public void actionPerformed(ActionEvent ev)
	{
		try
		{
			AreaFormacao objt = classeView();
			if (obj == null)
			{
				long x = new AreaFormacaoDAO().inserir(objt);
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
				new AreaFormacaoDAO().atualizar(objt);
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

	public AreaFormacao classeView()
	{
		AreaFormacao x = new AreaFormacao();
		x.setId(view.id.getValor());
		x.setNome(view.nome.getText());
		return x;
	}

	public Object[] formatoTabela(AreaFormacao areaformacao)
	{
		return new Object[] {

		areaformacao.getId(), areaformacao.getNome() };
	}
}
