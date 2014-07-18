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
import modelo.Pesquisador;

public class PesquisadorControl implements ActionListener
{
	PesquisadorView view;
	PesquisadorTabela tabela;
	Autocompletee autocp;
	Pesquisador obj;

	public PesquisadorControl(PesquisadorView v, PesquisadorTabela t)
			throws Exception
	{
		this.view = v;
		this.tabela = t;
	}

	public PesquisadorControl(PesquisadorView v, Autocompletee t)
			throws Exception
	{
		this.view = v;
		this.autocp = t;
	}

	public PesquisadorControl(PesquisadorView v, PesquisadorTabela t,
			Pesquisador o) throws Exception
	{
		this.view = v;
		this.tabela = t;
		this.obj = o;
	}

	public void actionPerformed(ActionEvent ev)
	{
		try
		{
			Pesquisador objt = classeView();
			if (obj == null)
			{
				long x = new PesquisadorDAO().inserir(objt);
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
				new PesquisadorDAO().atualizar(objt);
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

	public Pesquisador classeView()
	{
		Pesquisador x = new Pesquisador();
		x.setId(view.id.getValor());
		x.setNome(view.nome.getText());
		x.setNome_cientifico(view.nome_cientifico.getText());
		x.setEmail(view.email.getText());
		x.setSexo(view.sexo.getText());
		x.setClasse(view.classe.getText());
		x.setTitulacao(view.titulacao.getText());
		x.setCurso_vinculado((Curso) view.curso_vinculado.getSelected());
		x.setAreaformacao((AreaFormacao) view.areaformacao.getSelected());
		return x;
	}

	public Object[] formatoTabela(Pesquisador pesquisador)
	{
		return new Object[] {

		pesquisador.getId(), pesquisador.getNome(),
				pesquisador.getNome_cientifico(), pesquisador.getEmail(),
				pesquisador.getSexo(), pesquisador.getClasse(),
				pesquisador.getTitulacao(),
				pesquisador.getCurso_vinculado().getId(),
				pesquisador.getAreaformacao().getId() };
	}
}
