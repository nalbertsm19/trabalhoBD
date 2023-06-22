package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Agricultor;

public class AgricultorDAO {

    // Métodos para acesso aos dados do Agricultor

	public void cadastrar(Agricultor agricultor) throws SQLException 
	{
	    Connection conexao = ConnectionFactory.getConnection();

	    String sql = "INSERT INTO agricultor (codAgricultor, areaServico, nome, dataNasc, cpf) VALUES (?, ?, ?, ?, ?)";

	    PreparedStatement ps = conexao.prepareStatement(sql);
	    ps.setInt(1, agricultor.getCodAgricultor());
	    ps.setString(2, agricultor.getAreaServico());
	    ps.setString(3, agricultor.getNome());
	    ps.setString(4, agricultor.getDataNasc());
	    ps.setString(5, agricultor.getCpf());

	    ps.executeUpdate();
	}

	public boolean atualizar(Agricultor agricultor) throws SQLException {
	    Connection conexao = ConnectionFactory.getConnection();

	    String sql = "UPDATE agricultor SET areaServico = ?, nome = ?, dataNasc = ?, cpf = ? WHERE codAgricultor = ?";

	    PreparedStatement ps = conexao.prepareStatement(sql);
	    ps.setString(1, agricultor.getAreaServico());
	    ps.setString(2, agricultor.getNome());
	    ps.setString(3, agricultor.getDataNasc());
	    ps.setString(4, agricultor.getCpf());
	    ps.setInt(5, agricultor.getCodAgricultor());

	    int linhasAfetadas = ps.executeUpdate();

	    return linhasAfetadas > 0;
	}

	public boolean excluir(int codAgricultor) throws SQLException {
	    Connection conexao = ConnectionFactory.getConnection();

	    String sql = "DELETE FROM agricultor WHERE codAgricultor = ?";

	    PreparedStatement ps = conexao.prepareStatement(sql);
	    ps.setInt(1, codAgricultor);

	    int linhasAfetadas = ps.executeUpdate();

	    return linhasAfetadas > 0;
	}

	public Agricultor buscar(int codAgricultor) throws SQLException {
	    Connection conexao = ConnectionFactory.getConnection();

	    String sql = "SELECT * FROM Agricultor WHERE codAgricultor = ?";

	    PreparedStatement ps = conexao.prepareStatement(sql);
	    ps.setInt(1, codAgricultor);

	    ResultSet rs = ps.executeQuery();

	    if (rs.next()) {
	        
	        String areaServico = rs.getString("areaServico");
	        String nome = rs.getString("nome");
	        String dataNasc = rs.getString("dataNasc");
	        String cpf = rs.getString("cpf");

	        return new Agricultor(areaServico, nome, dataNasc, cpf);
	    }
		return null;

	}
	public List<Agricultor> listaAgri() throws SQLException {
	    Connection conexao = ConnectionFactory.getConnection();

	    String sql = "SELECT * FROM Agricultor";

	    PreparedStatement ps = conexao.prepareStatement(sql);

	    ResultSet rs = ps.executeQuery();

	    List<Agricultor> agricultores = new ArrayList<>();

	    while (rs.next()) {
	    	int codAgricultor= rs.getInt("codAgricultor");
	        String areaServico = rs.getString("areaServico");
	        String nome = rs.getString("nome");
	        String dataNasc = rs.getString("dataNasc");
	        String cpf = rs.getString("cpf");

	        Agricultor agricultor = new Agricultor( areaServico, nome, dataNasc, cpf);
	        agricultores.add(agricultor);
	    }

	    return agricultores;
}
}
