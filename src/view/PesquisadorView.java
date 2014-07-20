package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JCheckBox;

import dao.*;
import auxiliares.*;
import net.java.dev.designgridlayout.DesignGridLayout;
import modelo.*;
import control.*;
import view.*;
import modelo.Pesquisador;

public class PesquisadorView extends JFrame
{
	
	JButton salvar;
	public JTextInt id;
	public JTextField nome;
	public JTextField nome_cientifico;
	public JTextField email;
	public ListRadio sexo;
	public ListRadio classe;
	public ListRadio titulacao;
	public Autocompletee curso_vinculado;
	public Autocompletee areaformacao;
	public JTextField curriculoLattes;

	public PesquisadorView(PesquisadorTabela t) throws Exception
	{
		super("Pesquisador");
		inicio(new Pesquisador());
		salvar.addActionListener(new PesquisadorControl(this, t));
	}

	public PesquisadorView(Autocompletee t) throws Exception
	{
		super("Pesquisador");
		inicio(new Pesquisador());
		salvar.addActionListener(new PesquisadorControl(this, t));
	}

	public PesquisadorView(PesquisadorTabela t, Pesquisador pesquisador)
			throws Exception
	{
		super("Pesquisador");
		inicio(pesquisador);
		salvar.addActionListener(new PesquisadorControl(this, t, pesquisador));
	}
	
	public void inicio(Pesquisador pesquisador) throws Exception
	{
		salvar = new JButton("Salvar");
		id = new JTextInt(pesquisador.getId());
		id.setEditable(false);
		id.setEnabled(false);
		nome = new JTextField(pesquisador.getNome() == null ? ""
				: pesquisador.getNome());
		nome_cientifico = new JTextField(
				pesquisador.getNome_cientifico() == null ? ""
						: pesquisador.getNome_cientifico());
		email = new JTextField(pesquisador.getEmail() == null ? ""
				: pesquisador.getEmail());
		sexo = new ListRadio("Masculino,Feminino".split(","),
				pesquisador.getSexo() == null ? "" : pesquisador.getSexo());
		classe = new ListRadio("Docente, Técnico, Discente".split(","),
				pesquisador.getClasse() == null ? "" : pesquisador.getClasse());
		
		curriculoLattes = new JTextField(pesquisador.getCurriculoLattes() == null? "" : pesquisador.getCurriculoLattes());
		
		titulacao = new ListRadio(
				"Graduando,Graduado, Especialista,Mestre,Doutor".split(","),
				pesquisador.getTitulacao() == null ? ""
						: pesquisador.getTitulacao());
		Curso xcurso_vinculado = null;
		if (pesquisador.getCursoVinculado() != null)
			xcurso_vinculado = (new CursoDAO().listar(" WHERE id="
					+ pesquisador.getCursoVinculado()).get(0));
		curso_vinculado = new Autocompletee(new CursoDAO().listar(""),
				xcurso_vinculado);
		AreaFormacao xareaformacao = null;
		if (pesquisador.getAreaformacao() != null)
			xareaformacao = (new AreaFormacaoDAO().listar(" WHERE id="
					+ pesquisador.getAreaformacao()).get(0));
		areaformacao = new Autocompletee(new AreaFormacaoDAO().listar(""),
				xareaformacao);

		JPanel pp = new JPanel();
		DesignGridLayout layout = new DesignGridLayout(pp);
		// layout.row().grid(new JLabel("Id :")).add(id);
		layout.row().grid(new JLabel("Nome:")).add(nome);
		layout.row().grid(new JLabel("Nome científico:")).add(nome_cientifico)
				.grid(new JLabel("E-mail:")).add(email);
		layout.row().grid(new JLabel("Sexo:")).add(sexo);
		layout.row().grid(new JLabel("Classe:")).add(classe);
		layout.row().grid(new JLabel("Currículo Lattes:")).add(curriculoLattes);
		layout.row().grid(new JLabel("Titulação:")).add(titulacao);
		// layout.row().grid(new JLabel("Classe :")).add(classe);;
		JButton dcurso_vinculado = new JButton("Novo");
		dcurso_vinculado.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					new CursoView(curso_vinculado);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		layout.row().grid(new JLabel("Curso vinculado:")).add(curso_vinculado)
				.add(dcurso_vinculado);
		JButton dareaformacao = new JButton("Novo");
		dareaformacao.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					new AreaFormacaoView(areaformacao);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		layout.row().grid(new JLabel("Área de formação:")).add(areaformacao)
				.add(dareaformacao);

		layout.row().grid().add(salvar, 1);
		getContentPane().add(new JScrollPane(pp));
		pack();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	/*
	 * public static void main(String[] args) throws Exception { new
	 * PesquisadorView(new PesquisadorTabela()); }
	 */
}
