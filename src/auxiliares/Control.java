package auxiliares;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public abstract class Control implements ActionListener
{
	protected View view;

	public View getView()
	{
		return view;
	}

	public void setView(View view)
	{
		this.view = view;
	}

	public Control()
	{
		view = new View();
		view.getBuscar().addActionListener(this);
		view.getAdicionar().addActionListener(this);
		view.getEditar().addActionListener(this);
		view.getRemover().addActionListener(this);
		view.getImprimir().addActionListener(this);
		view.getPesquisar().addActionListener(this);
	}

	// public abstract void preencherTabela();

	public abstract void adicionar();

	public abstract boolean remover(int i);

	public abstract void editar(int i);

	public abstract void pesquisar(String x) throws Exception;

	public void actionPerformed(ActionEvent evt)
	{
		try
		{
			if (evt.getSource().equals(view.getPesquisar()))
			{
				if (view.getPesquisa().getText().isEmpty())
					pesquisar("");
				else
				{
					String conteudo = view.getPesquisa().getText();
					String pesquisa = " WHERE "
							+ view.getOpcoes().getSelectedItem().toString()
							+ " ";
					if (conteudo.contains("/"))
					{
						System.out.println("contem");
						String data[] = conteudo.split("/");
						if (data.length == 3)
							conteudo = data[2] + "-" + data[1] + "-" + data[0];
						else
							conteudo = data[1] + "-" + data[0];
					}

					if (view.getTipo().isSelected())
					{
						pesquisa = pesquisa + "= '" + conteudo + "'";
					}
					else
					{
						System.err.println(pesquisa);
						switch (pesquisa)
						{
							case " WHERE orientador ":
								pesquisa = "WHERE id IN (SELECT Pesquisa.id FROM Pesquisa INNER JOIN Pesquisador ON Pesquisa.orientador = Pesquisador.id "
										+ "WHERE (Pesquisa.orientador = Pesquisador.id)"
										+ " AND (Pesquisador.nome LIKE '%"
										+ conteudo + "%'))";
								break;
							case " WHERE pesquisadorResponsavel ":
								pesquisa = "WHERE pesquisador_responsavel = (SELECT id FROM Pesquisador WHERE "
										+ "(Pesquisador.nome LIKE '%" + conteudo + "%'))";
								break;
							case " WHERE colaboradores ":
								pesquisa = " WHERE id in ("
										+ "SELECT Pesquisacolaboradores.id1 FROM Pesquisacolaboradores "
										+ "INNER JOIN Pesquisador "
										+ "WHERE (Pesquisacolaboradores.id2 = Pesquisador.id) "
										+ "AND (Pesquisador.nome LIKE '%"
										+ conteudo + "%'))";
								break;
							case " WHERE palavrasChave ":
								pesquisa = " WHERE id in ("
										+ "SELECT Pesquisapalavras_chave.id1 FROM  Pesquisapalavras_chave "
										+ "INNER JOIN palavrachave "
										+ "WHERE (Pesquisapalavras_chave.id2 = palavrachave.id) "
										+ "AND (palavrachave.palavra LIKE '%"
										+ conteudo + "%'))";
								break;
							case " WHERE fonteFinanciamento ":
								pesquisa = "WHERE fonte_financiamento = ("
										+ "SELECT id FROM  fonteFinanciamento "
										+ "WHERE nome LIKE '%" + conteudo
										+ "%')";
								break;
							case " WHERE instituicoesCooperadoras ":
								pesquisa = " WHERE id in ("
										+ "SELECT Pesquisainstituicoes_cooperadoras.id1 FROM  Pesquisainstituicoes_cooperadoras "
										+ "INNER JOIN instituicaoCooperadora "
										+ "WHERE (Pesquisainstituicoes_cooperadoras.id2 = instituicaoCooperadora.id) "
										+ "AND (instituicaoCooperadora.nome LIKE '%"
										+ conteudo + "%'))";
								break;
							case " WHERE areaConhecimentoCNPq ":
								pesquisa = "WHERE area_conhecimento_CNPq = ("
										+ "SELECT id FROM  areaConhecimento "
										+ "WHERE nome LIKE '%" + conteudo
										+ "%')";
								break;
							case " WHERE instituicaoSubmissao ":
								pesquisa = "WHERE instituicao_submissao = ("
										+ "SELECT id FROM  instituicaoSubmissao "
										+ "WHERE nome LIKE '%" + conteudo
										+ "%')";
								break;
								
							case " WHERE anoSubmissao ":
								pesquisa = "WHERE ano_submissao = " + conteudo;
								break;
								
							case " WHERE tempoDuracao ":
								pesquisa = "WHERE tempo_duracao = " + conteudo;
								break;
								
							default:
								pesquisa = pesquisa + "LIKE '%" + conteudo
										+ "%'";
								break;
						}
					}

					pesquisar(pesquisa);
				}
			}

			else if (evt.getSource().equals(view.getBuscar()))
			{
				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
						view.getTabela().getModel());
				view.getTabela().setRowSorter(sorter);
				sorter.setRowFilter(RowFilter.regexFilter(view.getFiltro()
						.getText()));
			}

			else if (evt.getSource().equals(view.getAdicionar()))
			{
				adicionar();
			}
			else if (evt.getSource().equals(view.getImprimir()))
			{
				view.getTabela().print();
			}

			else if (!(view.getTabela().getSelectedRow() < 0))
			{
				int i = view.getTabela().convertRowIndexToModel(
						view.getTabela().getSelectedRow());
				if (evt.getSource().equals(view.getRemover()))
				{
					if (JOptionPane.showConfirmDialog(null,
							"Deseja mesmo remover o item selecionado?",
							"Remover Item", 0) == 0)
					{
						String aux = "Não";
						if (remover(i))
						{
							((DefaultTableModel) view.getTabela().getModel())
									.removeRow(i);
							aux = "";
						}
						JOptionPane.showMessageDialog(null, "O produto " + aux
								+ " foi removido com sucesso",
								"Remoção de Produto", 1);
					}
				}
				else if (evt.getSource().equals(view.getEditar()))
				{
					editar(i);
				}
			}
			else
				JOptionPane.showMessageDialog(null,
						"Nenhum item da tabela foi selecionado",
						"Remoção de Produto", 0);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
