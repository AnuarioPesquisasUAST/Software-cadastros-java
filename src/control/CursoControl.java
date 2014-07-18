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
import modelo.Curso;

public class CursoControl implements ActionListener
{
	CursoView view;
	CursoTabela tabela;
	Autocompletee autocp;
	Curso obj;

	public CursoControl(CursoView v, CursoTabela t) throws Exception
	{
		this.view = v;
		this.tabela = t;
	}

	public CursoControl(CursoView v, Autocompletee t) throws Exception
	{
		this.view = v;
		this.autocp = t;
	}

	public CursoControl(CursoView v, CursoTabela t, Curso o) throws Exception
	{
		this.view = v;
		this.tabela = t;
		this.obj = o;
	}

	public void actionPerformed(ActionEvent ev)
	{
		try
		{
			Curso objt = classeView();
			if (obj == null)
			{
				long x = new CursoDAO().inserir(objt);
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
				new CursoDAO().atualizar(objt);
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

	public Curso classeView()
	{
		Curso x = new Curso();
		x.setId(view.id.getValor());
		x.setNome(view.nome.getText());
		return x;
	}

	public Object[] formatoTabela(Curso curso)
	{
		return new Object[] {

		curso.getId(), curso.getNome() };
	}
}
